package cinema.cinemaLicenta.service;

import cinema.cinemaLicenta.dto.AddressDTO;
import cinema.cinemaLicenta.dto.CityDTO;
import cinema.cinemaLicenta.entity.Address;
import cinema.cinemaLicenta.entity.City;
import cinema.cinemaLicenta.exception.AddressNotFoundException;
import cinema.cinemaLicenta.mapper.AddressMapper;
import cinema.cinemaLicenta.repository.AddressRepository;
import cinema.cinemaLicenta.repository.CityRepository;
import cinema.cinemaLicenta.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAddress() {
        AddressDTO addressDTO = AddressDTO.builder()
                .id(1L)
                .address("Street")
                .cityDTO(CityDTO.builder().id(1L).build())
                .build();

        City city = City.builder()
                .id(1L)
                .city_name("City")
                .build();

        Address address = Address.builder()
                .id(1L)
                .address("Street")
                .city(city)
                .build();

        when(addressMapper.mapToAddress(any(AddressDTO.class))).thenReturn(address);
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(city));
        when(addressMapper.mapToAddressDTO(any(Address.class))).thenReturn(addressDTO);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressDTO response = addressService.addAddress(addressDTO);

        assertThat(response).isEqualTo(addressDTO);
        verify(addressMapper, times(1)).mapToAddress(addressDTO);
        verify(cityRepository, times(1)).findById(addressDTO.getCityDTO().getId());
        verify(addressMapper, times(1)).mapToAddressDTO(address);
        verify(addressRepository, times(1)).save(address);
    }


    @Test
    void testGetAddress() {
        Long addressId = 1L;
        Address address = Address.builder()
                .id(addressId)
                .address("Street")
                .build();

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        Optional<Address> response = addressService.getAddress(addressId);

        assertThat(response.isPresent()).isTrue();
        assertThat(response.get()).isEqualTo(address);
        verify(addressRepository, times(1)).findById(addressId);
    }

    @Test
    public void testGetAllAddresses() {
        // Create a list of actors
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1, "Street"));
        addresses.add(new Address(2, "Cisnadie"));

        // Mock the behavior of the actorRepository.findAll() method
        when(addressRepository.findAll()).thenReturn(addresses);

        // Call the getAllActors() method
        List<Address> result = addressService.getAllAddresses();

        // Assert the result
        assertThat(result).isEqualTo(addresses);
    }


    @Test
    void testGetAddressByName() {
        String addressName = "Street";

        List<Address> addresses = new ArrayList<>();
        addresses.add(Address.builder().id(1L).address(addressName).build());
        addresses.add(Address.builder().id(2L).address(addressName).build());

        when(addressRepository.findAddressByAddress(addressName)).thenReturn(addresses);
        when(addressMapper.mapToAddressDTO(any(Address.class))).thenReturn(AddressDTO.builder().build());

        List<AddressDTO> response = addressService.getAddressByName(addressName);

        assertThat(response).isNotEmpty();
        verify(addressRepository, times(1)).findAddressByAddress(addressName);
        verify(addressMapper, times(addresses.size())).mapToAddressDTO(any(Address.class));
    }

    @Test
    void testDelete() {
        Long addressId = 1L;
        Address address = Address.builder().id(addressId).build();

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        boolean response = addressService.delete(addressId);

        assertThat(response).isTrue();
        verify(addressRepository, times(1)).findById(addressId);
        verify(addressRepository, times(1)).delete(address);
    }

    @Test
    void testDelete_NotFound() {
        Long addressId = 1L;

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> addressService.delete(addressId));
        verify(addressRepository, times(1)).findById(addressId);
        verify(addressRepository, never()).delete(any(Address.class));
    }
}
