package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.LanguagesDTO;
import cinema.cinemaLicenta.entity.Languages;
import org.springframework.stereotype.Component;

@Component
public class LanguagesMapper {
    public Languages mapToLanguages(LanguagesDTO languagesDTO) {
        return Languages.builder()
                .language_name(languagesDTO.getLanguage_name())
                .build();
    }

    public LanguagesDTO mapToLanguagesDTO(Languages languages) {
        return LanguagesDTO.builder()
                .id(languages.getId())
                .language_name(languages.getLanguage_name())
                .build();
    }

}
