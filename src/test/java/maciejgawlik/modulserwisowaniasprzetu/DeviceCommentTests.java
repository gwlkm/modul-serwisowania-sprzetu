package maciejgawlik.modulserwisowaniasprzetu;

import com.fasterxml.jackson.databind.ObjectMapper;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceComment;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceCommentDto;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceCommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:comment-tests.sql")
public class DeviceCommentTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeviceCommentRepository deviceCommentRepository;

    @Test
    public void shouldAddComment() throws Exception {
        //when
        DeviceCommentDto commentDto = new DeviceCommentDto(1L,"Comment content",1L);
        MvcResult response = mockMvc.perform(post("/device/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)))
                .andReturn();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
        assertTrue(response.getResponse().getContentAsString().matches("\\d+"));

        long commentId = Long.valueOf(response.getResponse().getContentAsString());
        assertTrue(deviceCommentRepository.findById(commentId).isPresent());
        assertEquals(1L, commentId);
        assertEquals("Comment content", deviceCommentRepository.findById(commentId).get().getContent());
    }

    @Test
    public void shouldNotAddCommentForInvalidDevice() throws Exception {
        //when
        DeviceCommentDto commentDto = new DeviceCommentDto(5L,"Comment content",2L);
        mockMvc.perform(post("/device/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)))

        //then
                .andExpect(status().isUnprocessableEntity());

        assertFalse(deviceCommentRepository.findById(5L).isPresent());
    }

    @Test
    public void shouldModifyComment() throws Exception {
        //given
        deviceCommentRepository.save(new DeviceComment(1L, "fourth comment", new Date(), new Date(),1));

        //when
        DeviceCommentDto commentDto = new DeviceCommentDto(1L, "comment modified", 1L);
        MvcResult response = mockMvc.perform(put("/device/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)))
                .andReturn();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());

        Optional<DeviceComment> modifiedComment = deviceCommentRepository.findById(1L);
        assertTrue(modifiedComment.isPresent());
        assertEquals("comment modified", modifiedComment.get().getContent());
        assertNotEquals(modifiedComment.get().getCreationDate(), modifiedComment.get().getModificationDate());
    }

    @Test
    public void shouldDeleteComment() throws Exception {
        //given
        deviceCommentRepository.save(new DeviceComment(7L, "device comment", new Date(), new Date(),1L));

        //when
        mockMvc.perform(delete("/device/comment/7"))

        //then
                .andExpect(status().isOk());

        assertFalse(deviceCommentRepository.findById(7L).isPresent());

    }

}
