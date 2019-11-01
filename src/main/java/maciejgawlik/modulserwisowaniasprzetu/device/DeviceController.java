package maciejgawlik.modulserwisowaniasprzetu.device;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.category.DeviceCategory;
import maciejgawlik.modulserwisowaniasprzetu.device.category.DeviceCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private DeviceCategoryService deviceCateoryService;

    @GetMapping("/category/all")
    public List<DeviceCategory> addDevice(){
        return deviceCateoryService.extractAll();
    }

}