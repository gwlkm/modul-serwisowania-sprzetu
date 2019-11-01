package maciejgawlik.modulserwisowaniasprzetu.devicecategory;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeviceCategoryController {

    private DeviceCategoryService deviceCategoryService;

    @GetMapping("/category/all")
    public List<DeviceCategory> addDevice(){
        return deviceCategoryService.extractAll();
    }

}
