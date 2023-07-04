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
public class ScreeningDTO {

    private Long id;

    @NotEmpty
    @OnlyLetters
    private String start_time;

    @NotEmpty
    @OnlyLetters
    private String end_time;

    private FilmDTO filmDTO;
    private RoomDTO roomDTO;
}
