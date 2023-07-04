package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CinemaDTO;
import cinema.cinemaLicenta.entity.Cinema;
import cinema.cinemaLicenta.services.CinemaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.CINEMA_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a cinema")

    public ResponseEntity<CinemaDTO> addCinema(@RequestBody CinemaDTO cinemaDTO) {
        return ResponseEntity.ok(cinemaService.addCinema(cinemaDTO));
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all cinemas")

    public ResponseEntity<List<Cinema>> getAllCinemas() {
        return ResponseEntity.ok(cinemaService.getAllCinemas());
    }

    @GetMapping("/getCinema/{id}")
    @ApiOperation(value = "Get cinema by id")

    public ResponseEntity<Optional<Cinema>> getOneCinema(@PathVariable Long id) {
        return ResponseEntity.ok(cinemaService.getCinema(id));
    }

    @GetMapping("/getByName/{name:[a-zA-Z ]*}")
    @ApiOperation(value = "Get cinema by name")

    public ResponseEntity<List<CinemaDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(cinemaService.getCinemaByName(name));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a cinema")

    public ResponseEntity<String> deleteCinema(@PathVariable Long id) {
        cinemaService.delete(id);
        return ResponseEntity.ok(String.format(CINEMA_WAS_DELETED, id));
    }
}
