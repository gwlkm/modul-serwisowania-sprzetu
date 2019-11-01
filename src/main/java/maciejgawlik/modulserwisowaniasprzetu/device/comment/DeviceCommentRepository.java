package maciejgawlik.modulserwisowaniasprzetu.device.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceCommentRepository extends JpaRepository<DeviceComment, Long> {
}
