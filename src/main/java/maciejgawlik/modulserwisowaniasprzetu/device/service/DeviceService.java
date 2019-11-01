package maciejgawlik.modulserwisowaniasprzetu.device.service;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.Device;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.DeviceDto;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeviceService {

    private DeviceRepository deviceRepository;

    public ResponseEntity<Long> add(DeviceDto deviceDto) {
        long id = deviceRepository.save(new Device(deviceDto)).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
