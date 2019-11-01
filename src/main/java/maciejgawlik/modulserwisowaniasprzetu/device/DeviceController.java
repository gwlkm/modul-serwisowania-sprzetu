package maciejgawlik.modulserwisowaniasprzetu.device;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.DeviceDto;
import maciejgawlik.modulserwisowaniasprzetu.device.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Long> createDevice(@RequestBody DeviceDto deviceDto){
        return deviceService.add(deviceDto);
    }

}