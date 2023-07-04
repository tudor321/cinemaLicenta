package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CustomerDTO;
import cinema.cinemaLicenta.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceImpl {
    CustomerDTO addCustomer(CustomerDTO customerDTO);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomer(Long id);

    List<CustomerDTO> getByFirstAndLast(String customer_first, String customer_last);

    CustomerDTO updateCustomer(Long id, String newFirstName, String newLastName, String newPhone, String newEmail);

    boolean delete(Long id);
}
