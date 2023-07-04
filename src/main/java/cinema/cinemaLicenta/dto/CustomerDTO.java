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
public class CustomerDTO {

    private Long id;

    @NotEmpty
    @OnlyLetters
    private String customer_first;

    @NotEmpty
    @OnlyLetters
    private String customer_last;

    @NotEmpty
    @OnlyLetters
    private String email;

    @NotEmpty
    @OnlyLetters
    private String phone;
}
