package maciejgawlik.modulserwisowaniasprzetu.device.domain.parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceParameterRepository extends JpaRepository<DeviceParameter, Long> {
}
