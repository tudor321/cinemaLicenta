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
public class RoomDTO {

    private Long id;

    @NotEmpty
    @OnlyLetters
    private Long room_number;

    @NotEmpty
    @OnlyLetters
    private Long capacity;

    private CinemaDTO cinemaDTO;

}
