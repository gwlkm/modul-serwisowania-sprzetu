package maciejgawlik.modulserwisowaniasprzetu.device;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.category.DeviceCategory;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.Device;
import maciejgawlik.modulserwisowaniasprzetu.device.service.DeviceCategoryService;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceCommentDto;
import maciejgawlik.modulserwisowaniasprzetu.device.service.DeviceCommentService;
import maciejgawlik.modulserwisowaniasprzetu.device.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/device")
public class DeviceController {


    private DeviceService deviceService;
    private DeviceCommentService deviceCommentService;
    private DeviceCategoryService deviceCategoryService;


    @GetMapping("/all")
    public List<Device> extractAllDevices() {
        return deviceService.extractAll();
    }

    @PutMapping("/mark-as-broken/{id}")
    public ResponseEntity<String> markAsBroken(@PathVariable("id") long id) {
        return deviceService.markAsBroken(id);
    }

    @PutMapping("/mark-as-fixed/{id}")
    public ResponseEntity<String> markAsFixed(@PathVariable("id") long id) {
        return deviceService.markAsFixed(id);
    }


    @PostMapping("/comment")
    public ResponseEntity<Long> addComment(@RequestBody DeviceCommentDto commentDto) {
        return deviceCommentService.add(commentDto);
    }

    @PutMapping("/comment")
    public ResponseEntity modifyComment(@RequestBody DeviceCommentDto commentDto) {
        return deviceCommentService.update(commentDto);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") long id) {
        deviceCommentService.delete(id);
    }


    @GetMapping("/category/all")
    public List<DeviceCategory> extractAllCategories() {
        return deviceCategoryService.extractAll();
    }

}