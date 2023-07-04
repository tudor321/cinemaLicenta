package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CinemaDTO;
import cinema.cinemaLicenta.entity.Cinema;
import cinema.cinemaLicenta.services.CinemaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CinemaControllerTest {
    @Mock
    private CinemaService cinemaService;

    @InjectMocks
    private CinemaController cinemaController;

    public CinemaControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCinema() {
        CinemaDTO cinemaDTO = new CinemaDTO();
        CinemaDTO expectedResponse = new CinemaDTO();

        when(cinemaService.addCinema(cinemaDTO)).thenReturn(expectedResponse);

        ResponseEntity<CinemaDTO> response = cinemaController.addCinema(cinemaDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(cinemaService, times(1)).addCinema(cinemaDTO);
    }

    @Test
    public void testGetAllCinemas() {
        List<Cinema> expectedResponse = new ArrayList<>();

        when(cinemaService.getAllCinemas()).thenReturn(expectedResponse);

        ResponseEntity<List<Cinema>> response = cinemaController.getAllCinemas();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(cinemaService, times(1)).getAllCinemas();
    }

    @Test
    public void testGetOneCinema() {
        Long cinemaId = 1L;
        Optional<Cinema> expectedResponse = Optional.of(new Cinema());

        when(cinemaService.getCinema(cinemaId)).thenReturn(expectedResponse);

        ResponseEntity<Optional<Cinema>> response = cinemaController.getOneCinema(cinemaId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(cinemaService, times(1)).getCinema(cinemaId);
    }

    @Test
    public void testGetByName() {
        String name = "Cinema";
        List<CinemaDTO> expectedResponse = new ArrayList<>();

        when(cinemaService.getCinemaByName(name)).thenReturn(expectedResponse);

        ResponseEntity<List<CinemaDTO>> response = cinemaController.getByName(name);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(cinemaService, times(1)).getCinemaByName(name);
    }

    @Test
    public void testDeleteCinema() {
        Long cinemaId = 1L;
        String expectedResponse = String.format("Cinema %d was deleted", cinemaId);

        ResponseEntity<String> response = cinemaController.deleteCinema(cinemaId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(cinemaService, times(1)).delete(cinemaId);
    }

    // Other test cases...
}
