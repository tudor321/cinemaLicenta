package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.FilmDTO;
import cinema.cinemaLicenta.entity.Actor;
import cinema.cinemaLicenta.entity.Category;
import cinema.cinemaLicenta.entity.Film;
import cinema.cinemaLicenta.entity.Languages;
import cinema.cinemaLicenta.exception.ConditionNotFoundException;
import cinema.cinemaLicenta.exception.FilmNotFoundException;
import cinema.cinemaLicenta.mapper.FilmMapper;
import cinema.cinemaLicenta.repository.ActorRepository;
import cinema.cinemaLicenta.repository.CategoryRepository;
import cinema.cinemaLicenta.repository.FilmRepository;
import cinema.cinemaLicenta.repository.LanguagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.CONDITION_NOT_FOUND;
import static cinema.cinemaLicenta.constants.ProjectConstants.FILM_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FilmService implements FilmServiceImpl {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private LanguagesRepository languagesRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public FilmDTO addFilm(FilmDTO filmDTO) {
        Film film = filmMapper.mapToFilm(filmDTO);
        if ((filmDTO.getActorDTO() != null) && (filmDTO.getLanguagesDTO() != null) && (filmDTO.getCategoryDTO() != null)) {
            Optional<Actor> actor = actorRepository.findById(filmDTO.getActorDTO().getId());
            Optional<Category> category = categoryRepository.findById(filmDTO.getCategoryDTO().getId());
            Optional<Languages> languages = languagesRepository.findById(filmDTO.getLanguagesDTO().getId());

            if (actor.isEmpty() && languages.isEmpty() && category.isEmpty()) {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND, film.getActor(), film.getCategory(), film.getLanguages()));

            }
            film.setActor(actor.get());
            film.setCategory(category.get());
            film.setLanguages(languages.get());
        }
        return filmMapper.mapToFilmDTO(filmRepository.save(film));
    }

    @Override
    public Optional<Film> getFilm(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    public List<FilmDTO> getFilmByTitle(String title) {
        List<FilmDTO> filmDTOS = filmRepository.findFilmByTitle(title).stream()
                .map(name -> filmMapper.mapToFilmDTO(name)).collect(Collectors.toList());
        if (filmDTOS.isEmpty()) {
            throw new FilmNotFoundException(String.format(FILM_NOT_FOUND, title));
        }
        return filmDTOS;
    }

    @Override
    public FilmDTO updateFilm(Long id, String newTitle, String newDescription, String newReleaseYear, String newDuration) {
        Film film = filmRepository.getReferenceById(id);
        if (film == null) {
            throw new FilmNotFoundException(String.format(FILM_NOT_FOUND, id));
        }
        film.setTitle(newTitle);
        film.setDescription(newDescription);
        film.setRelease_year(newReleaseYear);
        film.setDuration(newDuration);
        return filmMapper.mapToFilmDTO(filmRepository.save(film));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Film> filmFound = filmRepository.findById(id);
        if (filmFound.isPresent()) {
            filmRepository.delete(filmFound.get());
        } else {
            throw new FilmNotFoundException(String.format(FILM_NOT_FOUND, id));
        }
        return true;
    }
}
