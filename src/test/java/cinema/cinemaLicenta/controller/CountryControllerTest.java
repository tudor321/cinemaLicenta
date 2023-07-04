package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CountryDTO;
import cinema.cinemaLicenta.entity.Country;
import cinema.cinemaLicenta.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CountryControllerTest {
    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCountry() {
        CountryDTO countryDTO = new CountryDTO();
        // Set up the necessary data for the test

        CountryDTO expectedResponse = new CountryDTO();
        // Set up the expected response

        when(countryService.addCountry(countryDTO)).thenReturn(expectedResponse);

        ResponseEntity<CountryDTO> response = countryController.addCountry(countryDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetOneCountry() {
        Long countryId = 1L;
        Optional<Country> expectedResponse = Optional.of(new Country());
        // Set up the expected response

        when(countryService.getCountry(countryId)).thenReturn(expectedResponse);

        ResponseEntity<Optional<Country>> response = countryController.getOneCountry(countryId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetByName() {
        String countryName = "Country";
        List<CountryDTO> expectedResponse = new ArrayList<>();
        // Set up the expected response

        when(countryService.getCountryByName(countryName)).thenReturn(expectedResponse);

        ResponseEntity<List<CountryDTO>> response = countryController.getByName(countryName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void testDeleteCountry() {
        Long countryId = 1L;
        String expectedResponse = String.format("Country with id %d was deleted", countryId);

        ResponseEntity<String> response = countryController.deleteCountry(countryId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(countryService, times(1)).delete(countryId);
    }


    // Other test cases...
}
