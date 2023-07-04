package cinema.cinemaLicenta.dto;

import cinema.cinemaLicenta.entity.City;
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
public class CityDTO {

    private Long id;

    @NotEmpty
    @OnlyLetters
    private String city_name;
    private String name;
    private CountryDTO countryDTO;

    public CityDTO(Long id, String city_name) {
        this.id = id;
        this.city_name = city_name;
    }

    public CityDTO(City city) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
