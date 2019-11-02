package maciejgawlik.modulserwisowaniasprzetu.device.domain.comment;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCommentDto implements Serializable {
    private Long id;
    @NotNull
    private String content;
    @NotNull
    private Long deviceId;
}
