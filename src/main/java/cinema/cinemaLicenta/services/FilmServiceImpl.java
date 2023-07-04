package cinema.cinemaLicenta.services;


import cinema.cinemaLicenta.dto.FilmDTO;
import cinema.cinemaLicenta.entity.Film;

import java.util.List;
import java.util.Optional;

public interface FilmServiceImpl {
    FilmDTO addFilm(FilmDTO filmDTO);

    Optional<Film> getFilm(Long id);

    List<FilmDTO> getFilmByTitle(String title);

    FilmDTO updateFilm(Long id, String newTitle, String newDescription, String newReleaseYear, String newDuration);

    boolean delete(Long id);
}
