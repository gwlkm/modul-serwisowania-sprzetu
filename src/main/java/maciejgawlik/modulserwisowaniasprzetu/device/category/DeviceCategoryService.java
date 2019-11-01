package maciejgawlik.modulserwisowaniasprzetu.device.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceCategoryService {

    private DeviceCategoryRepository deviceCategoryRepository;

    public List<DeviceCategory> extractAll(){
        return deviceCategoryRepository.findAll();
    }

}