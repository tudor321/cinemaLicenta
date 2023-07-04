package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.RoomDTO;
import cinema.cinemaLicenta.entity.Room;
import cinema.cinemaLicenta.services.RoomService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.ROOM_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a room")

    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.addRoom(roomDTO));
    }

    @GetMapping("/getRoom/{id}")
    @ApiOperation(value = "Get room by id")

    public ResponseEntity<Optional<Room>> getOneRoom(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoom(id));
    }

    @GetMapping("/getByCapacity/{number:[0-9]+}")
    @ApiOperation(value = "Get room by capacity")

    public ResponseEntity<List<RoomDTO>> getByCapacity(@PathVariable Long number) {
        return ResponseEntity.ok(roomService.getRoomByCapacity(number));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a room")

    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.ok(String.format(ROOM_WAS_DELETED, id));
    }
}
