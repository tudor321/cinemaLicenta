package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.AddressDTO;
import cinema.cinemaLicenta.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    @Autowired
    private CityMapper cityMapper;

    public Address mapToAddress(AddressDTO addressDTO) {
        if ((addressDTO.getCityDTO() != null)) {
            return Address.builder()
                    .address(addressDTO.getAddress())
                    .postal_code(addressDTO.getPostal_code())
                    .city(cityMapper.mapToCity(addressDTO.getCityDTO()))
                    .build();
        } else {
            return Address.builder()
                    .address(addressDTO.getAddress())
                    .postal_code(addressDTO.getPostal_code())
                    .build();
        }
    }

    public AddressDTO mapToAddressDTO(Address address) {
        if ((address.getCity() != null)) {
            return AddressDTO.builder()
                    .id(address.getId())
                    .address(address.getAddress())
                    .postal_code(address.getPostal_code())
                    .cityDTO(cityMapper.mapToCityDTO(address.getCity()))
                    .build();
        } else {
            return AddressDTO.builder()
                    .id(address.getId())
                    .address(address.getAddress())
                    .postal_code(address.getPostal_code())
                    .build();
        }
    }
}
