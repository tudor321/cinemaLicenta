package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.RoomDTO;
import cinema.cinemaLicenta.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    @Autowired
    private CinemaMapper cinemaMapper;

    public Room mapToRoom(RoomDTO roomDTO) {
        if ((roomDTO.getCinemaDTO() != null)) {
            return Room.builder()
                    .room_number(roomDTO.getRoom_number())
                    .capacity(roomDTO.getCapacity())
                    .cinema(cinemaMapper.mapToCinema(roomDTO.getCinemaDTO()))
                    .build();
        } else {
            return Room.builder()
                    .room_number(roomDTO.getRoom_number())
                    .capacity(roomDTO.getCapacity())
                    .build();
        }
    }

    public RoomDTO mapToRoomDTO(Room room) {
        if ((room.getCinema() != null)) {
            return RoomDTO.builder()
                    .room_number(room.getRoom_number())
                    .capacity(room.getCapacity())
                    .cinemaDTO(cinemaMapper.mapToCinemaDTO(room.getCinema()))
                    .build();
        } else {
            return RoomDTO.builder()
                    .room_number(room.getRoom_number())
                    .capacity(room.getCapacity())
                    .build();
        }
    }
}
