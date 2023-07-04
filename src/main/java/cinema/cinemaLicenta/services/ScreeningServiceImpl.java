package cinema.cinemaLicenta.services;


import cinema.cinemaLicenta.dto.ScreeningDTO;
import cinema.cinemaLicenta.entity.Screening;

import java.util.List;
import java.util.Optional;

public interface ScreeningServiceImpl {
    ScreeningDTO addScreening(ScreeningDTO screeningDTO);

    Optional<Screening> getScreening(Long id);

    List<ScreeningDTO> getScreeningByStart(String start_time);

    ScreeningDTO updateScreening(Long id, String newStartTime, String newEndTime);

    boolean delete(Long id);
}
