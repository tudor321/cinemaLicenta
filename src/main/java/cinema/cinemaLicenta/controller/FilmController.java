package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.FilmDTO;
import cinema.cinemaLicenta.entity.Film;
import cinema.cinemaLicenta.services.FilmService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/film")
public class FilmController {

    public static final String FILM_WAS_DELETED = "Film %s was deleted";
    @Autowired
    private FilmService filmService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a cinema")

    public ResponseEntity<FilmDTO> addFilm(@RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(filmService.addFilm(filmDTO));
    }

    @GetMapping("/getFilm/{id}")
    @ApiOperation(value = "Get cinema by id")

    public ResponseEntity<Optional<Film>> getOneFilm(@PathVariable Long id) {
        return ResponseEntity.ok(filmService.getFilm(id));
    }

    @GetMapping("/getByName/{name}")
    @ApiOperation(value = "Get cinema by name")

    public ResponseEntity<List<FilmDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(filmService.getFilmByTitle(name));
    }

    @PutMapping("/update/{id}/{title}/{description}/{release_year}/{duration}")
    @ApiOperation(value = "Update a cinema")

    public ResponseEntity<FilmDTO> updateAFilm(@PathVariable Long id, @PathVariable String title, @PathVariable String description, @PathVariable String release_year, @PathVariable String duration) {
        return ResponseEntity.ok(filmService.updateFilm(id, title, description, release_year, duration));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a cinema")

    public ResponseEntity<String> deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
        return ResponseEntity.ok(String.format(FILM_WAS_DELETED, id));
    }

}
