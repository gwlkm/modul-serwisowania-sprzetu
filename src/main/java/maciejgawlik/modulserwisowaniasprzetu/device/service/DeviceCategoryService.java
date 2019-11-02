package maciejgawlik.modulserwisowaniasprzetu.device.service;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.category.DeviceCategory;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.category.DeviceCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceCategoryService {

    private DeviceCategoryRepository deviceCategoryRepository;

    public List<DeviceCategory> extractAll() {
        return deviceCategoryRepository.findAll();
    }

}