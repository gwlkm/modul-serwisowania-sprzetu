package maciejgawlik.modulserwisowaniasprzetu.device.service;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.Device;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.DeviceDto;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceService {

    private DeviceRepository deviceRepository;

    public ResponseEntity<Long> add(DeviceDto deviceDto) {
        long id = deviceRepository.save(new Device(deviceDto)).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    public ResponseEntity<String> markAsBroken(long id) {
        return setDeviceIsBrokenParameter(id,true);
    }

    public ResponseEntity<String> markAsFixed(long id){
        return setDeviceIsBrokenParameter(id, false);
    }

    private ResponseEntity<String> setDeviceIsBrokenParameter(long id, boolean markAsBroken){
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if(!deviceOptional.isPresent()){
            return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
        }
        Device device = deviceOptional.get();
        device.setBroken(markAsBroken);
        deviceRepository.save(device);
        return new ResponseEntity<>("Device added", HttpStatus.OK);
    }
}
