package maciejgawlik.modulserwisowaniasprzetu.devicecategory;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DeviceCategory {

    @Id
    private String name;

}