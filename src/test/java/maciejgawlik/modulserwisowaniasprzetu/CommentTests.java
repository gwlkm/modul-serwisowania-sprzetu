package maciejgawlik.modulserwisowaniasprzetu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import maciejgawlik.modulserwisowaniasprzetu.device.comment.DeviceComment;
import maciejgawlik.modulserwisowaniasprzetu.device.comment.DeviceCommentDto;
import maciejgawlik.modulserwisowaniasprzetu.device.comment.DeviceCommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeviceCommentRepository deviceCommentRepository;

    @Test
    public void shouldAddComment() throws Exception {
        //given
        deviceCommentRepository.save(new DeviceComment(1L, "first comment", new Date(), new Date()));
        deviceCommentRepository.save(new DeviceComment(2L, "second comment", new Date(), new Date()));

        //when
        DeviceCommentDto commentDto = new DeviceCommentDto(null,"Comment content");
        MvcResult response = mockMvc.perform(post("/device/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)))
                .andReturn();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
        assertTrue(response.getResponse().getContentAsString().matches("\\d+"));

        long commentId = Long.valueOf(response.getResponse().getContentAsString());
        assertTrue(deviceCommentRepository.findById(commentId).isPresent());
        assertEquals(3L, commentId);
        assertEquals("Comment content", deviceCommentRepository.findById(commentId).get().getContent());
    }

    @Test
    public void shouldModifyComment() throws Exception {
        //given
        deviceCommentRepository.save(new DeviceComment(1L, "fourth comment", new Date(), new Date()));

        //when
        DeviceCommentDto commentDto = new DeviceCommentDto(1L, "fourth comment modified");
        MvcResult response = mockMvc.perform(put("/device/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)))
                .andReturn();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());

        Optional<DeviceComment> modifiedComment = deviceCommentRepository.findById(1L);
        assertTrue(modifiedComment.isPresent());
        assertEquals("fourth comment modified", modifiedComment.get().getContent());
        assertNotEquals(modifiedComment.get().getCreationDate(), modifiedComment.get().getModificationDate());
    }

    @Test
    public void shouldDeleteComment() throws Exception {
        //given
        deviceCommentRepository.save(new DeviceComment(1L, "comment", new Date(), new Date()));

        //when
        mockMvc.perform(delete("/device/comment/1"))

        //then
                .andExpect(status().isOk());

        assertFalse(deviceCommentRepository.findById(1L).isPresent());

    }

}
