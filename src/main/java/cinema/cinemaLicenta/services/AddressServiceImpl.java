package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.AddressDTO;
import cinema.cinemaLicenta.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressServiceImpl {
    AddressDTO addAddress(AddressDTO addressDTO);

    Optional<Address> getAddress(Long id);

    List<Address> getAllAddresses();

    List<AddressDTO> getAddressByName(String address);

    List<AddressDTO> getAddressByPostal(Long postal_code);

    boolean delete(Long id);
}
