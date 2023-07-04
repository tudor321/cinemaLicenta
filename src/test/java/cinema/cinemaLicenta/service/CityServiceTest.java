package cinema.cinemaLicenta.service;


import cinema.cinemaLicenta.dto.CityDTO;
import cinema.cinemaLicenta.dto.CountryDTO;
import cinema.cinemaLicenta.entity.City;
import cinema.cinemaLicenta.entity.Country;
import cinema.cinemaLicenta.exception.CityNotFoundException;
import cinema.cinemaLicenta.mapper.CityMapper;
import cinema.cinemaLicenta.repository.CityRepository;
import cinema.cinemaLicenta.repository.CountryRepository;
import cinema.cinemaLicenta.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCityWithExistingCountry() {
        // Arrange
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Test City");
        cityDTO.setCountryDTO(new CountryDTO(1L, "Test Country"));

        City city = new City();
        city.setName("Test City");
        city.setCountry(new Country("Test Country"));

        when(countryRepository.findById(anyLong())).thenReturn(Optional.of(new Country()));
        when(cityMapper.mapToCity(cityDTO)).thenReturn(city);
        when(cityRepository.save(city)).thenReturn(city);
        when(cityMapper.mapToCityDTO(city)).thenReturn(cityDTO);

        // Act
        CityDTO addedCityDTO = cityService.addCity(cityDTO);

        // Assert
        assertNotNull(addedCityDTO);
        assertEquals(cityDTO.getName(), addedCityDTO.getName());
        assertEquals(cityDTO.getCountryDTO().getName(), addedCityDTO.getCountryDTO().getName());
    }

    @Test
    void testAddCityWithNonExistingCountry() {
        // Arrange
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Test City");
        cityDTO.setCountryDTO(new CountryDTO(1L, "Non-existing Country"));

        City city = new City();
        city.setName("Test City");
        city.setCountry(new Country("Non-existing Country"));

        when(countryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
    }

    @Test
    void testGetCityByNameWithNonExistingCity() {
        // Arrange
        String cityName = "Non-existing City";

        when(cityRepository.findCityByCity_name(cityName)).thenReturn(List.of());

        // Act & Assert
        assertThrows(CityNotFoundException.class, () -> cityService.getCityByName(cityName));
    }

    @Test
    void testDeleteExistingCity() {
        // Arrange
        Long cityId = 1L;
        City city = new City();
        city.setId(cityId);

        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));

        // Act
        boolean result = cityService.delete(cityId);

        // Assert
        assertTrue(result);
    }

    @Test
    void testDeleteNonExistingCity() {
        // Arrange
        Long cityId = 1L;

        when(cityRepository.findById(cityId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CityNotFoundException.class, () -> cityService.delete(cityId));
    }
}
