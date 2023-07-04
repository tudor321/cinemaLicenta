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
public class LanguagesDTO {
    private Long id;

    @NotEmpty
    @OnlyLetters
    private String language_name;


}
