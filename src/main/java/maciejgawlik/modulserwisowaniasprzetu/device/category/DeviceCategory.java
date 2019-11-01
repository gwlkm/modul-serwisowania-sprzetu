package maciejgawlik.modulserwisowaniasprzetu.device.category;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DeviceCategory {

    @Id
    private String name;

}