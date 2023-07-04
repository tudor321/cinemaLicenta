package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.LanguagesDTO;
import cinema.cinemaLicenta.entity.Languages;

import java.util.List;
import java.util.Optional;

public interface LanguagesServiceImpl {
    LanguagesDTO addLanguages(LanguagesDTO languagesDTO);

    Optional<Languages> getLanguages(Long id);

    List<Languages> getAllLanguages();

    List<LanguagesDTO> getByName(String language_name);
}
