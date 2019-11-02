package maciejgawlik.modulserwisowaniasprzetu.device.domain.device;

import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
