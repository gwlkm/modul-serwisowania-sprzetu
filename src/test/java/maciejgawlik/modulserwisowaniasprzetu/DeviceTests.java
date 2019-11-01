package maciejgawlik.modulserwisowaniasprzetu;

import com.fasterxml.jackson.databind.ObjectMapper;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.Device;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.DeviceDto;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class DeviceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void shouldAddDevice() throws Exception {
        //given
        deviceRepository.save(new Device(1L, "first device"));
        deviceRepository.save(new Device(2L, "second device"));

        //when
        DeviceDto commentDto = new DeviceDto(null,"new device");
        MvcResult response = mockMvc.perform(post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto)))
                .andReturn();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
        assertTrue(response.getResponse().getContentAsString().matches("\\d+"));

        long commentId = Long.valueOf(response.getResponse().getContentAsString());
        assertTrue(deviceRepository.findById(commentId).isPresent());
        assertEquals(3L, commentId);
        assertEquals("new device", deviceRepository.findById(commentId).get().getName());
    }

}
