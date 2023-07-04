package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CityDTO;
import cinema.cinemaLicenta.entity.City;
import cinema.cinemaLicenta.entity.Country;
import cinema.cinemaLicenta.exception.CityNotFoundException;
import cinema.cinemaLicenta.exception.ConditionNotFoundException;
import cinema.cinemaLicenta.mapper.CityMapper;
import cinema.cinemaLicenta.repository.CityRepository;
import cinema.cinemaLicenta.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class CityService implements CityServiceImpl {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CityDTO addCity(CityDTO cityDTO) {
        City city = cityMapper.mapToCity(cityDTO);
        if ((cityDTO.getCountryDTO() != null)) {
            Optional<Country> country = countryRepository.findById(cityDTO.getCountryDTO().getId());

            if (country.isEmpty()) {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND, city.getCountry()));

            }
            city.setCountry(country.get());
        }
        return cityMapper.mapToCityDTO(cityRepository.save(city));

    }

    @Override
    public Optional<City> getCity(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<CityDTO> getCityByName(String city_name) {
        List<CityDTO> cityDTOS = cityRepository.findCityByCity_name(city_name).stream()
                .map(name -> cityMapper.mapToCityDTO(name)).collect(Collectors.toList());
        if (cityDTOS.isEmpty()) {
            throw new CityNotFoundException(String.format(CITY_NOT_FOUND, city_name));
        }
        return cityDTOS;
    }

    @Override
    public boolean delete(Long id) {
        Optional<City> cityFound = cityRepository.findById(id);
        if (cityFound.isPresent()) {
            cityRepository.delete(cityFound.get());
        } else {
            throw new CityNotFoundException(String.format(CITY_ID_NOT_FOUND, id));
        }
        return true;
    }
}
