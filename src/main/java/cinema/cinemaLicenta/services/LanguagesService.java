package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.LanguagesDTO;
import cinema.cinemaLicenta.entity.Languages;
import cinema.cinemaLicenta.exception.LanguagesNotFoundException;
import cinema.cinemaLicenta.mapper.LanguagesMapper;
import cinema.cinemaLicenta.repository.LanguagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.LANGUAGES_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LanguagesService implements LanguagesServiceImpl {

    @Autowired
    private LanguagesMapper languagesMapper;

    @Autowired
    private LanguagesRepository languagesRepository;

    @Override
    public LanguagesDTO addLanguages(LanguagesDTO languagesDTO) {
        return languagesMapper.mapToLanguagesDTO(languagesRepository.save(languagesMapper.mapToLanguages(languagesDTO)));
    }

    @Override
    public Optional<Languages> getLanguages(Long id) {
        return languagesRepository.findById(id);
    }

    @Override
    public List<Languages> getAllLanguages() {
        List<Languages> languages = new ArrayList<>();
        for (int i = 0; i < languagesRepository.findAll().size(); i++) {
            languages.add(languagesRepository.findAll().get(i));
        }
        return languages;
    }

    @Override
    public List<LanguagesDTO> getByName(String language_name) {
        List<LanguagesDTO> languagesDTOS = languagesRepository.findLanguagesBylanguage_name(language_name).stream()
                .map(name -> languagesMapper.mapToLanguagesDTO(name)).collect(Collectors.toList());
        if (languagesDTOS.isEmpty()) {
            throw new LanguagesNotFoundException(String.format(LANGUAGES_NOT_FOUND, language_name));
        }
        return languagesDTOS;
    }
}
