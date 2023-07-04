package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.CityDTO;
import cinema.cinemaLicenta.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    @Autowired
    private CountryMapper countryMapper;

    public City mapToCity(CityDTO cityDTO) {
        if ((cityDTO.getCountryDTO() != null)) {
            return City.builder()
                    .city_name(cityDTO.getCity_name())
                    .country(countryMapper.mapToCountry(cityDTO.getCountryDTO()))
                    .build();
        } else {
            return City.builder()
                    .city_name(cityDTO.getCity_name())
                    .build();
        }
    }

    public CityDTO mapToCityDTO(City city) {
        if ((city.getCountry() != null)) {
            return CityDTO.builder()
                    .id(city.getId())
                    .city_name(city.getCity_name())
                    .countryDTO(countryMapper.mapToCountryDTO(city.getCountry()))
                    .build();
        } else {
            return CityDTO.builder()
                    .id(city.getId())
                    .city_name(city.getCity_name())
                    .build();
        }
    }

}
