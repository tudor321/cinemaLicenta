package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CityDTO;
import cinema.cinemaLicenta.entity.City;
import cinema.cinemaLicenta.services.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CityControllerTest {
    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    public CityControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCity() {
        CityDTO cityDTO = new CityDTO();
        // Set properties of cityDTO

        CityDTO expectedResult = new CityDTO();
        // Set expected result properties

        when(cityService.addCity(cityDTO)).thenReturn(expectedResult);

        ResponseEntity<CityDTO> response = cityController.addCity(cityDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void testGetOneCity() {
        Long cityId = 1L;
        Optional<City> expectedResult = Optional.of(new City());
        // Set expected result

        when(cityService.getCity(cityId)).thenReturn(expectedResult);

        ResponseEntity<Optional<City>> response = cityController.getOneCity(cityId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void testGetByName() {
        String cityName = "City";
        List<CityDTO> expectedResult = Arrays.asList(new CityDTO(), new CityDTO());
        // Set expected result

        when(cityService.getCityByName(cityName)).thenReturn(expectedResult);

        ResponseEntity<List<CityDTO>> response = cityController.getByName(cityName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void testDeleteCity() {
        Long cityId = 1L;

        ResponseEntity<String> response = cityController.deleteCity(cityId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(String.format("City %d was deleted", cityId));

        verify(cityService, times(1)).delete(cityId);
    }

    // Other test cases...
}
