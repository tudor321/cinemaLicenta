package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.FilmDTO;
import cinema.cinemaLicenta.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private LanguagesMapper languagesMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    public Film mapToFilm(FilmDTO filmDTO) {
        if ((filmDTO.getActorDTO() != null) && (filmDTO.getCategoryDTO() != null) && (filmDTO.getLanguagesDTO() != null)) {
            return Film.builder()
                    .title(filmDTO.getTitle())
                    .description(filmDTO.getDescription())
                    .release_year(filmDTO.getRelease_year())
                    .duration(filmDTO.getDuration())
                    .actor(actorMapper.mapToActor(filmDTO.getActorDTO()))
                    .languages(languagesMapper.mapToLanguages(filmDTO.getLanguagesDTO()))
                    .category(categoryMapper.mapToCategory(filmDTO.getCategoryDTO()))
                    .build();
        } else {
            return Film.builder()
                    .title(filmDTO.getTitle())
                    .description(filmDTO.getDescription())
                    .release_year(filmDTO.getRelease_year())
                    .duration(filmDTO.getDuration())
                    .build();
        }
    }

    public FilmDTO mapToFilmDTO(Film film) {
        if ((film.getActor() != null) && (film.getCategory() != null) && (film.getLanguages() != null)) {
            return FilmDTO.builder()
                    .title(film.getTitle())
                    .description(film.getDescription())
                    .release_year(film.getRelease_year())
                    .duration(film.getDuration())
                    .actorDTO(actorMapper.mapToActorDTO(film.getActor()))
                    .categoryDTO(categoryMapper.mapToCategoryDTO(film.getCategory()))
                    .languagesDTO(languagesMapper.mapToLanguagesDTO(film.getLanguages()))
                    .build();
        } else {
            return FilmDTO.builder()
                    .title(film.getTitle())
                    .description(film.getDescription())
                    .release_year(film.getRelease_year())
                    .duration(film.getDuration())
                    .build();
        }
    }
}
