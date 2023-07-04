package cinema.cinemaLicenta.service;

import cinema.cinemaLicenta.dto.ActorDTO;
import cinema.cinemaLicenta.entity.Actor;
import cinema.cinemaLicenta.exception.ActorNotFoundException;
import cinema.cinemaLicenta.mapper.ActorMapper;
import cinema.cinemaLicenta.repository.ActorRepository;
import cinema.cinemaLicenta.services.ActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ActorServiceTest {

    @Mock
    private ActorMapper actorMapper;

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddActor() {
        ActorDTO actorDTO = ActorDTO.builder()
                .id(1L)
                .first_name("John")
                .first_name("Malkovich")
                .build();

        Actor actor = Actor.builder()
                .id(1L)
                .first_name("John")
                .last_name("Malkovich")
                .build();

        when(actorMapper.mapToActor(any(ActorDTO.class))).thenReturn(actor);
        when(actorMapper.mapToActorDTO(any(Actor.class))).thenReturn(actorDTO);
        when(actorRepository.save(any(Actor.class))).thenReturn(actor);

        ActorDTO response = actorService.addActor(actorDTO);

        assertThat(response).isEqualTo(actorDTO);
        verify(actorMapper, times(1)).mapToActor(actorDTO);
        verify(actorMapper, times(1)).mapToActorDTO(actor);
        verify(actorRepository, times(1)).save(actor);
    }

    @Test
    void testGetActor() {
        Long actorId = 1L;
        Actor actor = Actor.builder()
                .id(actorId)
                .first_name("John")
                .last_name("Malkovich")
                .build();

        when(actorRepository.findById(actorId)).thenReturn(Optional.of(actor));

        Optional<Actor> response = actorService.getActor(actorId);

        assertThat(response.isPresent()).isTrue();
        assertThat(response.get()).isEqualTo(actor);
        verify(actorRepository, times(1)).findById(actorId);
    }

    @Test
    public void testGetAllActors() {
        // Create a list of actors
        List<Actor> actors = new ArrayList<>();
        actors.add(new Actor(1L, "John", "Malkovich"));
        actors.add(new Actor(2L, "Jane", "Smith"));

        // Mock the behavior of the actorRepository.findAll() method
        when(actorRepository.findAll()).thenReturn(actors);

        // Call the getAllActors() method
        List<Actor> result = actorService.getAllActors();

        // Assert the result
        assertThat(result).isEqualTo(actors);
    }

    @Test
    void testGetByFirstAndLast() {
        String firstName = "John";
        String lastName = "Malkovich";

        List<Actor> actors = new ArrayList<>();
        actors.add(Actor.builder().id(1L).first_name(firstName).last_name(lastName).build());
        actors.add(Actor.builder().id(2L).last_name(firstName).last_name(lastName).build());

        when(actorRepository.findByFirst_nameAndLast_name(firstName, lastName)).thenReturn(actors);
        when(actorMapper.mapToActorDTO(any(Actor.class))).thenReturn(ActorDTO.builder().build());

        List<ActorDTO> response = actorService.getByFirstAndLast(firstName, lastName);

        assertThat(response).isNotEmpty();
        verify(actorRepository, times(1)).findByFirst_nameAndLast_name(firstName, lastName);
        verify(actorMapper, times(actors.size())).mapToActorDTO(any(Actor.class));
    }

    @Test
    void testGetByFirstAndLast_NotFound() {
        String firstName = "John";
        String lastName = "Malkovich";

        when(actorRepository.findByFirst_nameAndLast_name(firstName, lastName)).thenReturn(new ArrayList<>());

        assertThrows(ActorNotFoundException.class, () -> actorService.getByFirstAndLast(firstName, lastName));
        verify(actorRepository, times(1)).findByFirst_nameAndLast_name(firstName, lastName);
        verify(actorMapper, never()).mapToActorDTO(any(Actor.class));
    }

    @Test
    void testDelete() {
        Long actorId = 1L;
        Actor actor = Actor.builder().id(actorId).build();

        when(actorRepository.findById(actorId)).thenReturn(Optional.of(actor));

        boolean response = actorService.delete(actorId);

        assertThat(response).isTrue();
        verify(actorRepository, times(1)).findById(actorId);
        verify(actorRepository, times(1)).delete(actor);
    }

    @Test
    void testDelete_NotFound() {
        Long actorId = 1L;

        when(actorRepository.findById(actorId)).thenReturn(Optional.empty());

        assertThrows(ActorNotFoundException.class, () -> actorService.delete(actorId));
        verify(actorRepository, times(1)).findById(actorId);
        verify(actorRepository, never()).delete(any(Actor.class));
    }
}
