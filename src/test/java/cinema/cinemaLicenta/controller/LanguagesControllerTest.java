package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.LanguagesDTO;
import cinema.cinemaLicenta.entity.Languages;
import cinema.cinemaLicenta.services.LanguagesService;
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
import static org.mockito.Mockito.when;

class LanguagesControllerTest {
    @Mock
    private LanguagesService languagesService;

    @InjectMocks
    private LanguagesController languagesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddLanguages_ValidationSuccess() {
        LanguagesDTO languagesDTO = new LanguagesDTO();
        languagesDTO.setLanguage_name("English");

        LanguagesDTO expectedResponse = new LanguagesDTO();
        expectedResponse.setId(1L);
        expectedResponse.setLanguage_name("English");

        when(languagesService.addLanguages(languagesDTO)).thenReturn(expectedResponse);

        ResponseEntity<LanguagesDTO> response = languagesController.addLanguagies(languagesDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }


    public void testGetAllLanguages() {
        List<Languages> languagesList = new ArrayList<>();
        languagesList.add(new Languages(1L, "English"));
        languagesList.add(new Languages(2L, "French"));

        when(languagesService.getAllLanguages()).thenReturn(languagesList);

        ResponseEntity<List<Languages>> response = languagesController.getAllLanguagies();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(languagesList);
    }

    @Test
    public void testGetOneLanguage() {
        Long languageId = 1L;
        Optional<Languages> language = Optional.of(new Languages(languageId, "English"));

        when(languagesService.getLanguages(languageId)).thenReturn(language);

        ResponseEntity<Optional<Languages>> response = languagesController.getOneLanguage(languageId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(language);
    }

    @Test
    public void testGetLanguageByName() {
        String languageName = "English";
        List<LanguagesDTO> languageDTOList = new ArrayList<>();
        languageDTOList.add(new LanguagesDTO(1L, "English"));
        languageDTOList.add(new LanguagesDTO(2L, "English (US)"));

        when(languagesService.getByName(languageName)).thenReturn(languageDTOList);

        ResponseEntity<List<LanguagesDTO>> response = languagesController.getLanguageByName(languageName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(languageDTOList);
    }

    // Additional test cases...
}
