package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.ScreeningDTO;
import cinema.cinemaLicenta.entity.Screening;
import cinema.cinemaLicenta.services.ScreeningService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.SCREENING_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/screening")
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a screening")

    public ResponseEntity<ScreeningDTO> addScreening(@RequestBody ScreeningDTO screeningDTO) {
        return ResponseEntity.ok(screeningService.addScreening(screeningDTO));
    }

    @GetMapping("/getScreening/{id}")
    @ApiOperation(value = "Get screening by id")

    public ResponseEntity<Optional<Screening>> getOneScreening(@PathVariable Long id) {
        return ResponseEntity.ok(screeningService.getScreening(id));
    }

    @GetMapping("/getByStart/{startTime:\\d{2}:\\d{2}}")
    @ApiOperation(value = "Get screening by start time")

    public ResponseEntity<List<ScreeningDTO>> getByName(@PathVariable String startTime) {
        return ResponseEntity.ok(screeningService.getScreeningByStart(startTime));
    }

    @PutMapping("/update/{id}/{start_time}/{end_time}")
    @ApiOperation(value = "Update a screening")

    public ResponseEntity<ScreeningDTO> updateACustomer(@PathVariable Long id, @PathVariable String start_time, @PathVariable String end_time) {
        return ResponseEntity.ok(screeningService.updateScreening(id, start_time, end_time));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a screening")

    public ResponseEntity<String> deleteScreening(@PathVariable Long id) {
        screeningService.delete(id);
        return ResponseEntity.ok(String.format(SCREENING_WAS_DELETED, id));
    }
}
