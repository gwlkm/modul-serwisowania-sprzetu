package maciejgawlik.modulserwisowaniasprzetu.device.domain.parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.Device;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceParameter {

    @Id
    private Long id;

    private String name;

    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEVICE_ID", insertable = false, updatable = false)
    @JsonIgnore
    private Device device;

    @Column(name = "DEVICE_ID")
    private Long deviceId;

}
