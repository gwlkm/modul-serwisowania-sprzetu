package maciejgawlik.modulserwisowaniasprzetu.device.service;

import lombok.AllArgsConstructor;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceComment;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceCommentDto;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.comment.DeviceCommentRepository;
import maciejgawlik.modulserwisowaniasprzetu.device.domain.device.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceCommentService {

    private DeviceCommentRepository deviceCommentRepository;
    private DeviceRepository deviceRepository;

    public ResponseEntity<Long> add(DeviceCommentDto commentDto) {
        DeviceComment comment = new DeviceComment(commentDto);
        if (!deviceRepository.findById(commentDto.getDeviceId()).isPresent()) {
            return new ResponseEntity<>(-1L, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Long id = deviceCommentRepository.save(comment).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    public ResponseEntity update(DeviceCommentDto commentDto) {
        Optional<DeviceComment> commentOptional = deviceCommentRepository.findById(commentDto.getId());
        if (!commentOptional.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        DeviceComment comment = commentOptional.get();
        deviceCommentRepository.save(comment.update(commentDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public void delete(long id) {
        deviceCommentRepository.deleteById(id);
    }
}
