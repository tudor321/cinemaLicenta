package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.ScreeningDTO;
import cinema.cinemaLicenta.entity.Film;
import cinema.cinemaLicenta.entity.Room;
import cinema.cinemaLicenta.entity.Screening;
import cinema.cinemaLicenta.exception.ConditionNotFoundException;
import cinema.cinemaLicenta.exception.ScreeningNotFoundException;
import cinema.cinemaLicenta.mapper.ScreeningMapper;
import cinema.cinemaLicenta.repository.FilmRepository;
import cinema.cinemaLicenta.repository.RoomRepository;
import cinema.cinemaLicenta.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class ScreeningService implements ScreeningServiceImpl {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ScreeningMapper screeningMapper;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public ScreeningDTO addScreening(ScreeningDTO screeningDTO) {
        Screening screening = screeningMapper.mapToScreening(screeningDTO);
        if ((screeningDTO.getRoomDTO() != null) && (screeningDTO.getFilmDTO() != null)) {
            Optional<Film> film = filmRepository.findById(screeningDTO.getFilmDTO().getId());
            Optional<Room> room = roomRepository.findById(screeningDTO.getRoomDTO().getId());

            if (room.isEmpty() && film.isEmpty()) {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND, screening.getFilm(), screening.getRoom()));
            }
            screening.setFilm(film.get());
            screening.setRoom(room.get());
        }
        return screeningMapper.mapToScreeningDTO(screeningRepository.save(screening));
    }

    @Override
    public Optional<Screening> getScreening(Long id) {
        return screeningRepository.findById(id);
    }

    @Override
    public List<ScreeningDTO> getScreeningByStart(String start_time) {
        List<ScreeningDTO> screeningDTOS = screeningRepository.findScreeningByStart_time(start_time).stream()
                .map(name -> screeningMapper.mapToScreeningDTO(name)).collect(Collectors.toList());
        if (screeningDTOS.isEmpty()) {
            throw new ScreeningNotFoundException(String.format(SCREENING_START_NOT_FOUND, start_time));
        }
        return screeningDTOS;
    }

    @Override
    public ScreeningDTO updateScreening(Long id, String newStartTime, String newEndTime) {
        Screening screening = screeningRepository.getReferenceById(id);
        if (screening == null) {
            throw new ScreeningNotFoundException(String.format(SCREENING_ID_NOT_FOUND, id));
        }
        screening.setStart_time(newStartTime);
        screening.setEnd_time(newEndTime);
        return screeningMapper.mapToScreeningDTO(screeningRepository.save(screening));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Screening> screeningFound = screeningRepository.findById(id);
        if (screeningFound.isPresent()) {
            screeningRepository.delete(screeningFound.get());
        } else {
            throw new ScreeningNotFoundException(String.format(SCREENING_ID_NOT_FOUND, id));
        }
        return true;
    }
}
