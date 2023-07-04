package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.AddressDTO;
import cinema.cinemaLicenta.entity.Address;
import cinema.cinemaLicenta.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAddress() {
        AddressDTO addressDTO = AddressDTO.builder()
                .address("123 Main St")
                .postal_code(12345L)
                .build();

        AddressDTO savedAddressDTO = AddressDTO.builder()
                .id(1L)
                .address("123 Main St")
                .postal_code(12345L)
                .build();

        when(addressService.addAddress(any(AddressDTO.class))).thenReturn(savedAddressDTO);

        ResponseEntity<AddressDTO> response = addressController.addAddress(addressDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(savedAddressDTO);

        verify(addressService, times(1)).addAddress(any(AddressDTO.class));
    }

    @Test
    public void testGetAllAddress() {
        Address address1 = Address.builder()
                .id(1L)
                .address("123 Main St")
                .postal_code(12345L)
                .build();
        Address address2 = Address.builder()
                .id(2L)
                .address("456 Elm St")
                .postal_code(67890L)
                .build();
        List<Address> addresses = Arrays.asList(address1, address2);

        when(addressService.getAllAddresses()).thenReturn(addresses);

        ResponseEntity<List<Address>> response = addressController.getAllAddress();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(addresses);

        verify(addressService, times(1)).getAllAddresses();
    }

    @Test
    public void testGetOneAddress() {
        Long addressId = 1L;
        Address address = Address.builder()
                .id(addressId)
                .address("123 Main St")
                .postal_code(12345L)
                .build();

        when(addressService.getAddress(anyLong())).thenReturn(Optional.of(address));

        ResponseEntity<Optional<Address>> response = addressController.getOneAddress(addressId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(Optional.of(address));

        verify(addressService, times(1)).getAddress(anyLong());
    }

    @Test
    public void testGetByName() {
        String name = "City";
        AddressDTO addressDTO = AddressDTO.builder()
                .id(1L)
                .address("123 Main St")
                .postal_code(12345L)
                .build();
        List<AddressDTO> addresses = Arrays.asList(addressDTO);

        when(addressService.getAddressByName(anyString())).thenReturn(addresses);

        ResponseEntity<List<AddressDTO>> response = addressController.getByName(name);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(addresses);

        verify(addressService, times(1)).getAddressByName(anyString());
    }

    @Test
    public void testGetByPostal() {
        Long postalCode = 12345L;
        AddressDTO addressDTO = AddressDTO.builder()
                .id(1L)
                .address("123 Main St")
                .postal_code(postalCode)
                .build();
        List<AddressDTO> addresses = Arrays.asList(addressDTO);

        when(addressService.getAddressByPostal(anyLong())).thenReturn(addresses);

        ResponseEntity<List<AddressDTO>> response = addressController.getByPostal(postalCode);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(addresses);

        verify(addressService, times(1)).getAddressByPostal(anyLong());
    }

    @Test
    public void testDeleteAddress() {
        Long addressId = 1L;
        String expectedResponse = String.format("Address %d was deleted", addressId);

        when(addressService.delete(addressId)).thenReturn(true);

        ResponseEntity<String> response = addressController.deleteAddress(addressId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(addressService, times(1)).delete(addressId);
    }
}
