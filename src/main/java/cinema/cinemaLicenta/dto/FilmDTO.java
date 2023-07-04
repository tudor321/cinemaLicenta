package cinema.cinemaLicenta.dto;

import cinema.cinemaLicenta.validation.OnlyLetters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {

    private Long id;

    @NotEmpty
    @OnlyLetters
    private String title;

    @NotEmpty
    @OnlyLetters
    private String description;

    @NotEmpty
    @OnlyLetters
    private String release_year;

    @NotEmpty
    @OnlyLetters
    private String duration;

    private LanguagesDTO languagesDTO;
    private ActorDTO actorDTO;
    private CategoryDTO categoryDTO;

    public void setReleaseYear(String release_year) {
        this.release_year=release_year;
    }
}
