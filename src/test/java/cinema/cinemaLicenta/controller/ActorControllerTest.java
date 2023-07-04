package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.ActorDTO;
import cinema.cinemaLicenta.entity.Actor;
import cinema.cinemaLicenta.services.ActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ActorControllerTest {

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorController actorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddActors() {
        ActorDTO actorDTO = ActorDTO.builder()
                .first_name("John")
                .last_name("Doe")
                .build();

        ActorDTO savedActorDTO = ActorDTO.builder()
                .id(1L)
                .first_name("John")
                .last_name("Doe")
                .build();

        when(actorService.addActor(any(ActorDTO.class))).thenReturn(savedActorDTO);

        ResponseEntity<ActorDTO> response = actorController.addActors(actorDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(savedActorDTO);

        verify(actorService, times(1)).addActor(any(ActorDTO.class));
    }

    @Test
    public void testGetOneActor() {
        Long actorId = 1L;
        Actor actor = Actor.builder()
                .id(actorId)
                .first_name("John")
                .last_name("Doe")
                .build();

        when(actorService.getActor(anyLong())).thenReturn(Optional.of(actor));

        ResponseEntity<Optional<Actor>> response = actorController.getOneActor(actorId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(Optional.of(actor));

        verify(actorService, times(1)).getActor(anyLong());
    }

    @Test
    public void testGetAllActor() {
        Actor actor1 = Actor.builder()
                .id(1L)
                .first_name("John")
                .last_name("Doe")
                .build();
        Actor actor2 = Actor.builder()
                .id(2L)
                .first_name("Jane")
                .last_name("Smith")
                .build();
        List<Actor> actors = Arrays.asList(actor1, actor2);

        when(actorService.getAllActors()).thenReturn(actors);

        ResponseEntity<List<Actor>> response = actorController.getAllActor();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(actors);

        verify(actorService, times(1)).getAllActors();
    }

    @Test
    public void testGetByFirstNameAndLastName() {
        String firstName = "John";
        String lastName = "Doe";
        ActorDTO actorDTO = ActorDTO.builder()
                .id(1L)
                .first_name(firstName)
                .last_name(lastName)
                .build();
        List<ActorDTO> actors = Arrays.asList(actorDTO);

        when(actorService.getByFirstAndLast(anyString(), anyString())).thenReturn(actors);

        ResponseEntity<List<ActorDTO>> response = actorController.getByFirstNameandLastName(firstName, lastName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(actors);

        verify(actorService, times(1)).getByFirstAndLast(anyString(), anyString());
    }

    @Test
    public void testDeleteActor() {
        Long actorId = 1L;
        String expectedResponse = String.format("Actor with id %d was deleted.", actorId);

        ResponseEntity<String> response = actorController.deleteActor(actorId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(actorService, times(1)).delete(actorId);
    }
}
