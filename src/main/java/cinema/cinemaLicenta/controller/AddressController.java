package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.AddressDTO;
import cinema.cinemaLicenta.entity.Address;
import cinema.cinemaLicenta.services.AddressService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.ADDRESS_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a new Address")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(addressService.addAddress(addressDTO));
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all addresses")

    public ResponseEntity<List<Address>> getAllAddress() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/getAddress/{id}")
    @ApiOperation(value = "Get address by id")

    public ResponseEntity<Optional<Address>> getOneAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddress(id));
    }

    @GetMapping("/getByName/{name}")
    @ApiOperation(value = "Get address by name")

    public ResponseEntity<List<AddressDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(addressService.getAddressByName(name));
    }

    @GetMapping("/getByPostal/{postalCode}")
    @ApiOperation(value = "Get address by postal code")

    public ResponseEntity<List<AddressDTO>> getByPostal(@PathVariable Long postalCode) {
        return ResponseEntity.ok(addressService.getAddressByPostal(postalCode));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete address")

    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.ok(String.format(ADDRESS_WAS_DELETED, id));
    }
}
