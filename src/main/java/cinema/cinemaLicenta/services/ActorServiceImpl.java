package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.ActorDTO;
import cinema.cinemaLicenta.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorServiceImpl {
    ActorDTO addActor(ActorDTO actorDTO);

    Optional<Actor> getActor(Long id);

    List<Actor> getAllActors();

    List<ActorDTO> getByFirstAndLast(String first_name, String last_name);

    boolean delete(Long id);
}
