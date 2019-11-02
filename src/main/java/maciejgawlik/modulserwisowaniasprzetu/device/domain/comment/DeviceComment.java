package maciejgawlik.modulserwisowaniasprzetu.device.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.Device;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DeviceComment {

    @Id
    private Long id;

    @NotNull
    private String content;

    private Date creationDate;

    private Date modificationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEVICE_ID", insertable = false, updatable = false)
    @JsonIgnore
    private Device device;

    @Column(name = "DEVICE_ID")
    private long deviceId;

    public DeviceComment(DeviceCommentDto commentDto) {
        id = commentDto.getId();
        content = commentDto.getContent();
        creationDate = new Date();
        modificationDate = new Date();
        deviceId = commentDto.getDeviceId();
    }

    public DeviceComment(Long id, @NotNull String content, Date creationDate, Date modificationDate, long deviceId) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.deviceId = deviceId;
    }

    public DeviceComment update(DeviceCommentDto commentDto){
        content = commentDto.getContent();
        modificationDate = new Date();
        return this;
    }
}
