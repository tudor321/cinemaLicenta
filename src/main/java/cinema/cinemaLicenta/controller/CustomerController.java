package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CustomerDTO;
import cinema.cinemaLicenta.entity.Customer;
import cinema.cinemaLicenta.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.CUSTOMER_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a customer")

    public ResponseEntity<CustomerDTO> addCustomers(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.addCustomer(customerDTO));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get customer by id")

    public ResponseEntity<Optional<Customer>> getOneCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all customers")

    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/getByFirstNameAndLastName/{first:[a-zA-Z ]*}/{last:[a-zA-Z ]*}")
    @ApiOperation(value = "Get a Customer by first and last name")
    public ResponseEntity<List<CustomerDTO>> getByFirstAndLast(@PathVariable String first, @PathVariable String last) {
        return ResponseEntity.ok(customerService.getByFirstAndLast(first, last));
    }

    @PutMapping("/update/{id}/{first}/{last}/{phone}/{email}")
    @ApiOperation(value = "Update a customer")

    public ResponseEntity<CustomerDTO> updateACustomer(@PathVariable Long id, @PathVariable String first, @PathVariable String last, @PathVariable String phone, @PathVariable String email) {
        return ResponseEntity.ok(customerService.updateCustomer(id, first, last, phone, email));
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @ApiOperation(value = "Delete a customer")

    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok(String.format(CUSTOMER_WAS_DELETED, id));
    }

}
