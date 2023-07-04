package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.AddressDTO;
import cinema.cinemaLicenta.entity.Address;
import cinema.cinemaLicenta.entity.City;
import cinema.cinemaLicenta.exception.AddressNotFoundException;
import cinema.cinemaLicenta.exception.CityNotFoundException;
import cinema.cinemaLicenta.exception.ConditionNotFoundException;
import cinema.cinemaLicenta.mapper.AddressMapper;
import cinema.cinemaLicenta.repository.AddressRepository;
import cinema.cinemaLicenta.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.ADDRESS_NOT_FOUND;
import static cinema.cinemaLicenta.constants.ProjectConstants.CONDITION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AddressService implements AddressServiceImpl {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public AddressDTO addAddress(AddressDTO addressDTO) {
        Address address = addressMapper.mapToAddress(addressDTO);
        if ((addressDTO.getCityDTO() != null)) {
            Optional<City> city = cityRepository.findById(addressDTO.getCityDTO().getId());

            if (city.isEmpty()) {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND, address.getCity()));

            }
            address.setCity(city.get());
        }
        return addressMapper.mapToAddressDTO(addressRepository.save(address));
    }

    @Override
    public Optional<Address> getAddress(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < addressRepository.findAll().size(); i++) {
            addresses.add(addressRepository.findAll().get(i));
        }
        return addresses;
    }

    @Override
    public List<AddressDTO> getAddressByName(String address) {
        List<AddressDTO> addressDTOS = addressRepository.findAddressByAddress(address).stream()
                .map(name -> addressMapper.mapToAddressDTO(name)).collect(Collectors.toList());
        if (addressDTOS.isEmpty()) {
            throw new CityNotFoundException(String.format(ADDRESS_NOT_FOUND, address));
        }
        return addressDTOS;
    }

    @Override
    public List<AddressDTO> getAddressByPostal(Long postal_code) {
        List<AddressDTO> addressDTOS = addressRepository.findAddressByPostal_code(postal_code).stream()
                .map(name -> addressMapper.mapToAddressDTO(name)).collect(Collectors.toList());
        if (addressDTOS.isEmpty()) {
            throw new CityNotFoundException(String.format(ADDRESS_NOT_FOUND, postal_code));
        }
        return addressDTOS;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Address> addressFound = addressRepository.findById(id);
        if (addressFound.isPresent()) {
            addressRepository.delete(addressFound.get());
        } else {
            throw new AddressNotFoundException(String.format(ADDRESS_NOT_FOUND, id));
        }
        return true;
    }

}
