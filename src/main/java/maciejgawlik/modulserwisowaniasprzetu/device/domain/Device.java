package maciejgawlik.modulserwisowaniasprzetu.device.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    private boolean isBroken;

    public Device(DeviceDto deviceDto){
        name = deviceDto.getName();
        isBroken = false;
    }
}
