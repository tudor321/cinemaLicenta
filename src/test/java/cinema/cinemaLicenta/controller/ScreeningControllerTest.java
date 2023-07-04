package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.ScreeningDTO;
import cinema.cinemaLicenta.entity.Screening;
import cinema.cinemaLicenta.services.ScreeningService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ScreeningControllerTest {

    @Mock
    private ScreeningService screeningService;

    @InjectMocks
    private ScreeningController screeningController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddScreening() {
        ScreeningDTO screeningDTO = ScreeningDTO.builder()
                .start_time("10:00")
                .end_time("12:00")
                .build();

        ScreeningDTO expectedResponse = ScreeningDTO.builder()
                .id(1L)
                .start_time("10:00")
                .end_time("12:00")
                .build();

        when(screeningService.addScreening(any(ScreeningDTO.class))).thenReturn(expectedResponse);

        ResponseEntity<ScreeningDTO> response = screeningController.addScreening(screeningDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(screeningService, times(1)).addScreening(any(ScreeningDTO.class));
    }

    @Test
    void testGetOneScreening() {
        Long screeningId = 1L;
        Screening screening = new Screening();
        screening.setId(screeningId);

        when(screeningService.getScreening(screeningId)).thenReturn(Optional.of(screening));

        ResponseEntity<Optional<Screening>> response = screeningController.getOneScreening(screeningId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get().getId()).isEqualTo(screeningId);

        verify(screeningService, times(1)).getScreening(screeningId);
    }

    @Test
    void testGetScreeningByStart() {
        String start_time = "10:00";

        when(screeningService.getScreeningByStart(start_time)).thenReturn(List.of());

        ResponseEntity<List<ScreeningDTO>> response = screeningController.getByName(start_time);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        verify(screeningService, times(1)).getScreeningByStart(start_time);
    }

    @Test
    void testUpdateScreening() {
        Long screeningId = 1L;
        String startTime = "10:00";
        String endTime = "12:00";

        ScreeningDTO updatedScreeningDTO = ScreeningDTO.builder()
                .id(screeningId)
                .start_time(startTime)
                .end_time(endTime)
                .build();

        when(screeningService.updateScreening(anyLong(), anyString(), anyString())).thenReturn(updatedScreeningDTO);

        ResponseEntity<ScreeningDTO> response = screeningController.updateACustomer(screeningId, startTime, endTime);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedScreeningDTO);

        verify(screeningService, times(1)).updateScreening(screeningId, startTime, endTime);
    }

    @Test
    void testDeleteScreening() {
        Long screeningId = 1L;
        String expectedResponse = String.format("Screening %d was deleted", screeningId);

        ResponseEntity<String> response = screeningController.deleteScreening(screeningId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(screeningService, times(1)).delete(screeningId);
    }
}
