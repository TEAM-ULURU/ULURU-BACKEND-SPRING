package uluru.uluruspringbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uluru.uluruspringbackend.data.domain.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
