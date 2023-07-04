package cinema.cinemaLicenta.controller;


import cinema.cinemaLicenta.dto.LanguagesDTO;
import cinema.cinemaLicenta.entity.Languages;
import cinema.cinemaLicenta.services.LanguagesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/languages")
public class LanguagesController {

    @Autowired
    private LanguagesService languagesService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a language")

    public ResponseEntity<LanguagesDTO> addLanguagies(@RequestBody LanguagesDTO languagesDTO) {
        return ResponseEntity.ok(languagesService.addLanguages(languagesDTO));
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all languages")

    public ResponseEntity<List<Languages>> getAllLanguagies() {
        return ResponseEntity.ok(languagesService.getAllLanguages());
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get language by id")

    public ResponseEntity<Optional<Languages>> getOneLanguage(@PathVariable Long id) {
        return ResponseEntity.ok(languagesService.getLanguages(id));
    }

    @GetMapping("/getByName/{language_name:[a-zA-Z]*}")
    @ApiOperation(value = "Get language by name")
    ResponseEntity<List<LanguagesDTO>> getLanguageByName(@PathVariable String language_name) {
        return ResponseEntity.ok(languagesService.getByName(language_name));
    }
}

