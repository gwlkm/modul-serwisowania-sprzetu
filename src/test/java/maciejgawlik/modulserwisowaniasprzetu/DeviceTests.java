package maciejgawlik.modulserwisowaniasprzetu;

import com.fasterxml.jackson.databind.ObjectMapper;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.Device;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:sql/device-tests.sql")
public class DeviceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeviceRepository deviceRepository;

//    @Test
//    public void shouldAddDevice() throws Exception {
//        //given
//        deviceRepository.save(new Device(1L, "first device", false, "Telewizor"));
//        deviceRepository.save(new Device(2L, "second device", false, "Telewizor"));
//
//        //when
//        DeviceDto deviceDto = new DeviceDto(null,"new device", new DeviceCategory("Telewizor"));
//        MvcResult response = mockMvc.perform(post("/device")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(deviceDto)))
//                .andReturn();
//
//        //then
//        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
//        assertTrue(response.getResponse().getContentAsString().matches("\\d+"));
//
//        long commentId = Long.valueOf(response.getResponse().getContentAsString());
//        assertTrue(deviceRepository.findById(commentId).isPresent());
//        assertEquals(3L, commentId);
//        assertEquals("new device", deviceRepository.findById(commentId).get().getName());
//    }
//
//    @Test
//    public void shouldNotAddDeviceIfCategoryIsInvalid() throws Exception {
//        //when
//        DeviceDto deviceDto = new DeviceDto(5L,"new device", new DeviceCategory("Pralka"));
//        mockMvc.perform(post("/device")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(deviceDto)));
//
//        //then
////                .andExpect(status().isUnprocessableEntity());
//        assertFalse(deviceRepository.findById(5L).isPresent());
//    }

    @Test
    public void shouldMarkAsBroken() throws Exception {
        //given
        deviceRepository.save(new Device(1L, "first device", false, "Telewizor"));

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
        deviceRepository.save(new Device(1L, "first device", true, "Telewizor"));

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
