package cinema.cinemaLicenta.services;


import cinema.cinemaLicenta.dto.RoomDTO;
import cinema.cinemaLicenta.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomServiceImpl {
    RoomDTO addRoom(RoomDTO roomDTO);

    Optional<Room> getRoom(Long id);

    List<RoomDTO> getRoomByCapacity(Long capacity);

    boolean delete(Long id);
}
