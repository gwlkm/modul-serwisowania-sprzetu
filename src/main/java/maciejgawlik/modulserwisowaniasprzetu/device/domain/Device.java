package maciejgawlik.modulserwisowaniasprzetu.device.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Device(DeviceDto deviceDto){
        name = deviceDto.getName();
    }
}
