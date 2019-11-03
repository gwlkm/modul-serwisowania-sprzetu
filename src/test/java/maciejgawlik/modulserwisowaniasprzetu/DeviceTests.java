package maciejgawlik.modulserwisowaniasprzetu;

import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.Device;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:sql/device-tests.sql")
@WithMockUser(roles = {"USER","ADMIN"})
public class DeviceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceRepository deviceRepository;

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
