package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CityDTO;
import cinema.cinemaLicenta.entity.City;
import cinema.cinemaLicenta.services.CityService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.CITY_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a city")

    public ResponseEntity<CityDTO> addCity(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.addCity(cityDTO));
    }

    @GetMapping("/getCity/{id}")
    @ApiOperation(value = "Get city by id")

    public ResponseEntity<Optional<City>> getOneCity(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getCity(id));
    }

    @GetMapping("/getByName/{name:[a-zA-Z ]*}")
    @ApiOperation(value = "Get city by name")

    public ResponseEntity<List<CityDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(cityService.getCityByName(name));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a city")

    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.ok(String.format(CITY_WAS_DELETED, id));
    }
}
