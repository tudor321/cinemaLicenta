package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CountryDTO;
import cinema.cinemaLicenta.entity.Country;
import cinema.cinemaLicenta.services.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.COUNTRY_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a country")

    public ResponseEntity<CountryDTO> addCountry(@RequestBody CountryDTO countryDTO) {
        return ResponseEntity.ok(countryService.addCountry(countryDTO));
    }

    @GetMapping("/getCountry/{id}")
    @ApiOperation(value = "Get country by id")

    public ResponseEntity<Optional<Country>> getOneCountry(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountry(id));
    }

    @GetMapping("/getByName/{name:[a-zA-Z ]*}")
    @ApiOperation(value = "Get country by name")

    public ResponseEntity<List<CountryDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(countryService.getCountryByName(name));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a country")


    public ResponseEntity<String> deleteCountry(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.ok(String.format(COUNTRY_WAS_DELETED, id));
    }
}
