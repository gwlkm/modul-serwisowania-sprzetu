package maciejgawlik.modulserwisowaniasprzetu.device.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.devicecategory.DeviceCategory;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeviceDto {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private DeviceCategory category;

}
