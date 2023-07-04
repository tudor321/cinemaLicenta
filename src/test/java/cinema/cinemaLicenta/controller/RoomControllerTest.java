package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.RoomDTO;
import cinema.cinemaLicenta.entity.Room;
import cinema.cinemaLicenta.services.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Test
    void testAddRoom() {
        RoomDTO roomDTO = RoomDTO.builder()
                .room_number(1L)
                .capacity(50L)
                .build();

        Room room = Room.builder()
                .room_number(roomDTO.getRoom_number())
                .capacity(roomDTO.getCapacity())
                .build();

        when(roomService.addRoom(any(RoomDTO.class))).thenReturn(roomDTO);

        ResponseEntity<RoomDTO> response = roomController.addRoom(roomDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(roomDTO);

        verify(roomService, times(1)).addRoom(any(RoomDTO.class));
    }

    @Test
    void testGetOneRoom() {
        Long roomId = 1L;
        Room room = Room.builder()
                .id(roomId)
                .room_number(1L)
                .capacity(50L)
                .build();

        when(roomService.getRoom(roomId)).thenReturn(Optional.of(room));

        ResponseEntity<Optional<Room>> response = roomController.getOneRoom(roomId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get()).isEqualTo(room);

        verify(roomService, times(1)).getRoom(roomId);
    }


    @Test
    void testGetByCapacity() {
        Long capacity = 50L;
        List<RoomDTO> roomList = List.of(
                RoomDTO.builder()
                        .room_number(1L)
                        .capacity(capacity)
                        .build()
        );

        when(roomService.getRoomByCapacity(capacity)).thenReturn(roomList);

        ResponseEntity<List<RoomDTO>> response = roomController.getByCapacity(capacity);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(roomList);

        verify(roomService, times(1)).getRoomByCapacity(capacity);
    }

    @Test
    void testDeleteRoom() {
        Long roomId = 1L;
        String expectedResponse = String.format("Room %d was deleted", roomId);

        ResponseEntity<String> response = roomController.deleteRoom(roomId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(roomService, times(1)).delete(roomId);
    }

}
