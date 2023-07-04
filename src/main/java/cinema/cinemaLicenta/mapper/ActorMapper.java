package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.ActorDTO;
import cinema.cinemaLicenta.entity.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {

    public Actor mapToActor(ActorDTO actorDTO) {
        return Actor.builder()
                .first_name(actorDTO.getFirst_name())
                .last_name(actorDTO.getLast_name())
                .build();
    }

    public ActorDTO mapToActorDTO(Actor actor) {
        return ActorDTO.builder()
                .first_name(actor.getFirst_name())
                .last_name(actor.getLast_name())
                .build();
    }
}
