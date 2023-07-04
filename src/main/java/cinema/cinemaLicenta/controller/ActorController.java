package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.ActorDTO;
import cinema.cinemaLicenta.entity.Actor;
import cinema.cinemaLicenta.services.ActorService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a new actor")
    public ResponseEntity<ActorDTO> addActors(@RequestBody ActorDTO actorDTO) {
        return ResponseEntity.ok(actorService.addActor(actorDTO));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get an actor by id")
    public ResponseEntity<Optional<Actor>> getOneActor(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.getActor(id));
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all actors")
    public ResponseEntity<List<Actor>> getAllActor() {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/getByFirstNameAndLastName/{first:[a-zA-Z ]*}/{last:[a-zA-Z ]*}")
    @ApiOperation(value = "Get an actor by first and last name")
    public ResponseEntity<List<ActorDTO>> getByFirstNameandLastName(@PathVariable String first, @PathVariable String last) {
        return ResponseEntity.ok(actorService.getByFirstAndLast(first, last));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete an actor by id")
    public ResponseEntity<String> deleteActor(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.ok(String.format("Actor with id %d was deleted.", id));
    }
}