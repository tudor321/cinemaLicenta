package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.RoomDTO;
import cinema.cinemaLicenta.entity.Cinema;
import cinema.cinemaLicenta.entity.Room;
import cinema.cinemaLicenta.exception.ConditionNotFoundException;
import cinema.cinemaLicenta.exception.RoomNotFoundException;
import cinema.cinemaLicenta.mapper.RoomMapper;
import cinema.cinemaLicenta.repository.CinemaRepository;
import cinema.cinemaLicenta.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class RoomService implements RoomServiceImpl {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public RoomDTO addRoom(RoomDTO roomDTO) {
        Room room = roomMapper.mapToRoom(roomDTO);
        if ((roomDTO.getCinemaDTO() != null)) {
            Optional<Cinema> cinema = cinemaRepository.findById(roomDTO.getCinemaDTO().getId());

            if (cinema.isEmpty()) {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND, room.getCinema()));

            }
            room.setCinema(cinema.get());
        }
        return roomMapper.mapToRoomDTO(roomRepository.save(room));
    }

    @Override
    public Optional<Room> getRoom(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<RoomDTO> getRoomByCapacity(Long capacity) {
        List<RoomDTO> roomDTOS = roomRepository.findRoomByCapacity(capacity).stream()
                .map(name -> roomMapper.mapToRoomDTO(name)).collect(Collectors.toList());
        if (roomDTOS.isEmpty()) {
            throw new RoomNotFoundException(String.format(ROOM_NOT_FOUND, capacity));
        }
        return roomDTOS;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Room> roomFound = roomRepository.findById(id);
        if (roomFound.isPresent()) {
            roomRepository.delete(roomFound.get());
        } else {
            throw new RoomNotFoundException(String.format(ROOM_NOT_FOUND, id));
        }
        return true;
    }
}
