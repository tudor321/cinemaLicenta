package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Override
    Optional<Room> findById(Long id);

    @Query("SELECT R FROM Room R WHERE R.capacity = :capacity")
    List<Room> findRoomByCapacity(Long capacity);

    @Query("SELECT R FROM Room R WHERE R.room_number = :room_number")
    List<Room> findRoomByRoom_number(Long room_number);
}
