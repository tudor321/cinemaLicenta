package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CountryDTO;
import cinema.cinemaLicenta.entity.Country;
import cinema.cinemaLicenta.exception.CountryNotFoundException;
import cinema.cinemaLicenta.mapper.CountryMapper;
import cinema.cinemaLicenta.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.COUNTRY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CountryService implements CountryServiceImpl {

    @Autowired
    private CountryRepository countryRepository;


    @Autowired
    private CountryMapper countryMapper;

    @Override
    public CountryDTO addCountry(CountryDTO countryDTO) {
        return countryMapper.mapToCountryDTO(countryRepository.save(countryMapper.mapToCountry(countryDTO)));
    }

    @Override
    public Optional<Country> getCountry(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<CountryDTO> getCountryByName(String country_name) {
        List<CountryDTO> countryDTOS = countryRepository.findCountryByCountry_name(country_name).stream()
                .map(name -> countryMapper.mapToCountryDTO(name)).collect(Collectors.toList());
        if (countryDTOS.isEmpty()) {
            throw new CountryNotFoundException(String.format(COUNTRY_NOT_FOUND, country_name));
        }
        return countryDTOS;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Country> countryFound = countryRepository.findById(id);
        if (countryFound.isPresent()) {
            countryRepository.delete(countryFound.get());
        } else {
            throw new CountryNotFoundException(String.format(COUNTRY_NOT_FOUND, id));
        }
        return true;
    }
}
