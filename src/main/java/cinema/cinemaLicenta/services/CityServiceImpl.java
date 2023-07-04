package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CityDTO;
import cinema.cinemaLicenta.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityServiceImpl {
    CityDTO addCity(CityDTO cityDTO);

    Optional<City> getCity(Long id);

    List<CityDTO> getCityByName(String city_name);

    boolean delete(Long id);
}
