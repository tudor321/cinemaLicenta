package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.CinemaDTO;
import cinema.cinemaLicenta.entity.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaMapper {
    @Autowired
    private AddressMapper addressMapper;

    public Cinema mapToCinema(CinemaDTO cinemaDTO) {
        if ((cinemaDTO.getAddressDTO() != null)) {
            return Cinema.builder()
                    .cinema_name(cinemaDTO.getCinema_name())
                    .address(addressMapper.mapToAddress(cinemaDTO.getAddressDTO()))
                    .build();
        } else {
            return Cinema.builder()
                    .cinema_name(cinemaDTO.getCinema_name())
                    .build();
        }
    }


    public CinemaDTO mapToCinemaDTO(Cinema cinema) {
        if ((cinema.getAddress() != null)) {
            return CinemaDTO.builder()
                    .cinema_name(cinema.getCinema_name())
                    .addressDTO(addressMapper.mapToAddressDTO(cinema.getAddress()))
                    .build();
        } else {
            return CinemaDTO.builder()
                    .cinema_name(cinema.getCinema_name())
                    .build();
        }
    }
}
