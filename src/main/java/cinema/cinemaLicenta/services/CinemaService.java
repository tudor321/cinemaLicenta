package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CinemaDTO;
import cinema.cinemaLicenta.entity.Address;
import cinema.cinemaLicenta.entity.Cinema;
import cinema.cinemaLicenta.exception.CinemaNotFoundException;
import cinema.cinemaLicenta.exception.ConditionNotFoundException;
import cinema.cinemaLicenta.mapper.CinemaMapper;
import cinema.cinemaLicenta.repository.AddressRepository;
import cinema.cinemaLicenta.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.CINEMA_NOT_FOUND;
import static cinema.cinemaLicenta.constants.ProjectConstants.CONDITION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CinemaService implements CinemaServiceImpl {


    @Autowired
    private CinemaMapper cinemaMapper;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public CinemaDTO addCinema(CinemaDTO cinemaDTO) {
        Cinema cinema = cinemaMapper.mapToCinema(cinemaDTO);
        if ((cinemaDTO.getAddressDTO() != null)) {
            Optional<Address> address = addressRepository.findById(cinemaDTO.getAddressDTO().getId());

            if (address.isEmpty()) {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND, cinema.getAddress()));

            }
            cinema.setAddress(address.get());
        }
        return cinemaMapper.mapToCinemaDTO(cinemaRepository.save(cinema));
    }

    @Override
    public Optional<Cinema> getCinema(Long id) {
        return cinemaRepository.findById(id);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        for (int i = 0; i < cinemaRepository.findAll().size(); i++) {
            cinemas.add(cinemaRepository.findAll().get(i));
        }
        return cinemas;
    }

    @Override
    public List<CinemaDTO> getCinemaByName(String cinema_name) {
        List<CinemaDTO> cinemaDTOS = cinemaRepository.findCinemaByCinema_name(cinema_name).stream()
                .map(name -> cinemaMapper.mapToCinemaDTO(name)).collect(Collectors.toList());
        if (cinemaDTOS.isEmpty()) {
            throw new CinemaNotFoundException(String.format(CINEMA_NOT_FOUND, cinema_name));
        }
        return cinemaDTOS;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Cinema> cinemaFound = cinemaRepository.findById(id);
        if (cinemaFound.isPresent()) {
            cinemaRepository.delete(cinemaFound.get());
        } else {
            throw new CinemaNotFoundException(String.format(CINEMA_NOT_FOUND, id));
        }
        return true;
    }
}
