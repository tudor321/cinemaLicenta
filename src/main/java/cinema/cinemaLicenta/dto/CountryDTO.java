package cinema.cinemaLicenta.dto;

import cinema.cinemaLicenta.validation.OnlyLetters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    private Long id;

    @NotEmpty
    @OnlyLetters
    private String country_name;

    private List<CityDTO> cityDTOS;

    private String name;


    public CountryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}


