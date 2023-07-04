package cinema.cinemaLicenta.services;


import cinema.cinemaLicenta.dto.CinemaDTO;
import cinema.cinemaLicenta.entity.Cinema;

import java.util.List;
import java.util.Optional;

public interface CinemaServiceImpl {
    CinemaDTO addCinema(CinemaDTO cinemaDTO);

    Optional<Cinema> getCinema(Long id);

    List<Cinema> getAllCinemas();

    List<CinemaDTO> getCinemaByName(String cinema_name);

    boolean delete(Long id);
}
