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
public class TicketDTO {

    private Long id;

    @NotEmpty
    @OnlyLetters
    private String price;

    @NotEmpty
    @OnlyLetters
    private String seat_number;

    private ScreeningDTO screeningDTO;
    private CustomerDTO customerDTO;
}
