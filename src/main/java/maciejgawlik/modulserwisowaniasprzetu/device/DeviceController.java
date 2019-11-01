package maciejgawlik.modulserwisowaniasprzetu.device;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.category.DeviceCategory;
import maciejgawlik.modulserwisowaniasprzetu.device.category.DeviceCategoryService;
import maciejgawlik.modulserwisowaniasprzetu.device.comment.DeviceCommentDto;
import maciejgawlik.modulserwisowaniasprzetu.device.comment.DeviceCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private DeviceCategoryService deviceCateoryService;
    private DeviceCommentService deviceCommentService;

    @GetMapping("/category/all")
    public List<DeviceCategory> addDevice(){
        return deviceCateoryService.extractAll();
    }

    @PostMapping("/comment")
    public ResponseEntity<Long> addComment(@RequestBody DeviceCommentDto commentDto){
        return deviceCommentService.add(commentDto);
    }

    @PutMapping("/comment")
    public ResponseEntity modifyComment(@RequestBody DeviceCommentDto commentDto){
        return deviceCommentService.update(commentDto);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") long id){
        deviceCommentService.delete(id);
    }

}