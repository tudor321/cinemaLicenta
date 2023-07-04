package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CountryDTO;
import cinema.cinemaLicenta.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryServiceImpl {
    CountryDTO addCountry(CountryDTO countryDTO);

    Optional<Country> getCountry(Long id);

    List<CountryDTO> getCountryByName(String country_name);

    boolean delete(Long id);
}
