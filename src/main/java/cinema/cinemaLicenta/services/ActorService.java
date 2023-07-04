package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.ActorDTO;
import cinema.cinemaLicenta.entity.Actor;
import cinema.cinemaLicenta.exception.ActorNotFoundException;
import cinema.cinemaLicenta.mapper.ActorMapper;
import cinema.cinemaLicenta.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.ACTOR_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ActorService implements ActorServiceImpl {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public ActorDTO addActor(ActorDTO actorDTO) {
        return actorMapper.mapToActorDTO(actorRepository.save(actorMapper.mapToActor(actorDTO)));
    }

    @Override
    public Optional<Actor> getActor(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public List<Actor> getAllActors() {
        List<Actor> actors = new ArrayList<>();
        for (int i = 0; i < actorRepository.findAll().size(); i++) {
            actors.add(actorRepository.findAll().get(i));
        }
        return actors;
    }

    @Override
    public List<ActorDTO> getByFirstAndLast(String first_name, String last_name) {
        List<ActorDTO> actorDTOS = actorRepository.findByFirst_nameAndLast_name(first_name, last_name).stream()
                .map(name -> actorMapper.mapToActorDTO(name)).collect(Collectors.toList());
        if (actorDTOS.isEmpty()) {
            throw new ActorNotFoundException(String.format(ACTOR_NOT_FOUND, first_name, last_name));
        }
        return actorDTOS;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Actor> actorFound = actorRepository.findById(id);
        if (actorFound.isPresent()) {
            actorRepository.delete(actorFound.get());
        } else {
            throw new ActorNotFoundException(String.format(ACTOR_NOT_FOUND, id));
        }
        return true;
    }
}

