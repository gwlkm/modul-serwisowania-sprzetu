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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        deviceRepository.save(new Device(1L, "first device", false));
        deviceRepository.save(new Device(2L, "second device", false));

        //when
        DeviceDto deviceDto = new DeviceDto(null,"new device");
        MvcResult response = mockMvc.perform(post("/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deviceDto)))
                .andReturn();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
        assertTrue(response.getResponse().getContentAsString().matches("\\d+"));

        long commentId = Long.valueOf(response.getResponse().getContentAsString());
        assertTrue(deviceRepository.findById(commentId).isPresent());
        assertEquals(3L, commentId);
        assertEquals("new device", deviceRepository.findById(commentId).get().getName());
    }

    @Test
    public void shouldMarkAsBroken() throws Exception {
        //given
        deviceRepository.save(new Device(1L, "first device", false));

        //when
        mockMvc.perform(put("/device/mark-as-broken/1"))

        //then
                .andExpect(status().isOk());

        assertTrue(deviceRepository.findById(1L).isPresent());
        assertTrue(deviceRepository.findById(1L).get().isBroken());
    }

    @Test
    public void shouldNotFoundWhenMarkAsBroken() throws Exception {
        //when
        mockMvc.perform(put("/device/mark-as-broken/11"))

        //then
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldMarkAsFixed() throws Exception {
        //given
        deviceRepository.save(new Device(1L, "first device", true));

        //when
        mockMvc.perform(put("/device/mark-as-fixed/1"))

        //then
                .andExpect(status().isOk());

        assertTrue(deviceRepository.findById(1L).isPresent());
        assertFalse(deviceRepository.findById(1L).get().isBroken());
    }

    @Test
    public void shouldNotFoundWhenMarkAsFixed() throws Exception {
        //when
        mockMvc.perform(put("/device/mark-as-fixed/11"))

        //then
                .andExpect(status().isNotFound());
    }

}
