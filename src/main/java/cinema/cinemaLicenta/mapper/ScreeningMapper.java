package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.ScreeningDTO;
import cinema.cinemaLicenta.entity.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreeningMapper {

    @Autowired
    private FilmMapper filmMapper;

    @Autowired
    private RoomMapper roomMapper;

    public Screening mapToScreening(ScreeningDTO screeningDTO) {
        if ((screeningDTO.getFilmDTO() != null) && (screeningDTO.getRoomDTO() != null)) {
            return Screening.builder()
                    .start_time(screeningDTO.getStart_time())
                    .end_time(screeningDTO.getEnd_time())
                    .film(filmMapper.mapToFilm(screeningDTO.getFilmDTO()))
                    .room(roomMapper.mapToRoom(screeningDTO.getRoomDTO()))
                    .build();
        } else {
            return Screening.builder()
                    .start_time(screeningDTO.getStart_time())
                    .end_time(screeningDTO.getEnd_time())
                    .build();
        }
    }

    public ScreeningDTO mapToScreeningDTO(Screening screening) {
        if ((screening.getFilm() != null) && (screening.getRoom() != null)) {
            return ScreeningDTO.builder()
                    .start_time(screening.getStart_time())
                    .end_time(screening.getEnd_time())
                    .filmDTO(filmMapper.mapToFilmDTO(screening.getFilm()))
                    .roomDTO(roomMapper.mapToRoomDTO(screening.getRoom()))
                    .build();
        } else {
            return ScreeningDTO.builder()
                    .start_time(screening.getStart_time())
                    .end_time(screening.getEnd_time())
                    .build();
        }
    }
}
