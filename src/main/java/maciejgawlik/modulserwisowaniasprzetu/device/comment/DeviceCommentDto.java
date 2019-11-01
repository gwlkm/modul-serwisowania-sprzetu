package maciejgawlik.modulserwisowaniasprzetu.device.comment;

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
}
