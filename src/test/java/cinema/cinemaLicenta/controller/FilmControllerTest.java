package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.FilmDTO;
import cinema.cinemaLicenta.entity.Film;
import cinema.cinemaLicenta.services.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FilmControllerTest {
    @Mock
    private FilmService filmService;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFilm() {
        FilmDTO filmDTO = new FilmDTO(); // Initialize with required data

        when(filmService.addFilm(filmDTO)).thenReturn(filmDTO);

        ResponseEntity<FilmDTO> response = filmController.addFilm(filmDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(filmDTO);

        verify(filmService, times(1)).addFilm(filmDTO);
    }

    @Test
    public void testGetOneFilm() {
        Long filmId = 1L;
        Optional<Film> film = Optional.of(new Film()); // Initialize with required data

        when(filmService.getFilm(filmId)).thenReturn(film);

        ResponseEntity<Optional<Film>> response = filmController.getOneFilm(filmId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(film);

        verify(filmService, times(1)).getFilm(filmId);
    }

    @Test
    public void testGetByName() {
        String filmName = "Film Name";
        List<FilmDTO> films = List.of(new FilmDTO(), new FilmDTO()); // Initialize with required data

        when(filmService.getFilmByTitle(filmName)).thenReturn(films);

        ResponseEntity<List<FilmDTO>> response = filmController.getByName(filmName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(films);

        verify(filmService, times(1)).getFilmByTitle(filmName);
    }

    @Test
    public void testUpdateAFilm() {
        Long filmId = 1L;
        String title = "Updated Title";
        String description = "Updated Description";
        String releaseYear = "2023";
        String duration = "120 minutes";
        FilmDTO updatedFilm = new FilmDTO(); // Initialize with updated film data

        when(filmService.updateFilm(filmId, title, description, releaseYear, duration)).thenReturn(updatedFilm);

        ResponseEntity<FilmDTO> response = filmController.updateAFilm(filmId, title, description, releaseYear, duration);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedFilm);

        verify(filmService, times(1)).updateFilm(filmId, title, description, releaseYear, duration);
    }

    @Test
    public void testDeleteFilm() {
        Long filmId = 1L;

        ResponseEntity<String> response = filmController.deleteFilm(filmId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(String.format(FilmController.FILM_WAS_DELETED, filmId));

        verify(filmService, times(1)).delete(filmId);
    }

    // Other test cases...
}
