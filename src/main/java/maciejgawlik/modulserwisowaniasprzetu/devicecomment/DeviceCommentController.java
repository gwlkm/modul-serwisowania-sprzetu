package maciejgawlik.modulserwisowaniasprzetu.devicecomment;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/device-comment")
public class DeviceCommentController {

    private DeviceCommentService deviceCommentService;

    @PostMapping
    public ResponseEntity<Long> addComment(@RequestBody DeviceCommentDto commentDto){
        return deviceCommentService.add(commentDto);
    }

    @PutMapping
    public ResponseEntity modifyComment(@RequestBody DeviceCommentDto commentDto){
        return deviceCommentService.update(commentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") long id){
        deviceCommentService.delete(id);
    }

}
