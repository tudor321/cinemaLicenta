package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.CountryDTO;
import cinema.cinemaLicenta.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public Country mapToCountry(CountryDTO countryDTO) {
        return Country.builder()
                .country_name(countryDTO.getCountry_name())
                .build();
    }

    public CountryDTO mapToCountryDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .country_name(country.getCountry_name())
                .build();
    }
}
