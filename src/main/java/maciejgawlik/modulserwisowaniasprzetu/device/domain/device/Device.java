package maciejgawlik.modulserwisowaniasprzetu.device.domain.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.category.DeviceCategory;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceComment;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    private Long id;

    private String name;

    @Setter
    private boolean isBroken;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_NAME", insertable = false, updatable = false)
    private DeviceCategory category;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @OneToMany(mappedBy = "device")
    List<DeviceComment> comments;

    public Device(Long id, String name, boolean isBroken, String categoryName) {
        this.id = id;
        this.name = name;
        this.isBroken = isBroken;
        this.categoryName = categoryName;
    }

    public Device(DeviceDto deviceDto){
        name = deviceDto.getName();
        isBroken = false;
        category = deviceDto.getCategory();
    }
}
