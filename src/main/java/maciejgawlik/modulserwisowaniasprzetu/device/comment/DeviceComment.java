package maciejgawlik.modulserwisowaniasprzetu.device.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DeviceComment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String content;

    private Date creationDate;

    private Date modificationDate;

    public DeviceComment(DeviceCommentDto commentDto) {
        id = commentDto.getId();
        content = commentDto.getContent();
        creationDate = new Date();
        modificationDate = new Date();
    }

    public DeviceComment update(DeviceCommentDto commentDto){
        content = commentDto.getContent();
        modificationDate = new Date();
        return this;
    }
}