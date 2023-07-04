package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CustomerDTO;
import cinema.cinemaLicenta.entity.Customer;
import cinema.cinemaLicenta.exception.CountryNotFoundException;
import cinema.cinemaLicenta.exception.CustomernotFoundException;
import cinema.cinemaLicenta.mapper.CustomerMapper;
import cinema.cinemaLicenta.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.CUSTOMER_ID_NOT_FOUND;
import static cinema.cinemaLicenta.constants.ProjectConstants.CUSTOMER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceImpl {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        return customerMapper.mapToCustomerDTO(customerRepository.save(customerMapper.maptToCustomer(customerDTO)));
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < customerRepository.findAll().size(); i++) {
            customers.add(customerRepository.findAll().get(i));
        }
        return customers;
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<CustomerDTO> getByFirstAndLast(String customer_first, String customer_last) {
        List<CustomerDTO> customerDTOS = customerRepository.findByCustomer_firstAndCustomer_last(customer_first, customer_last).stream()
                .map(name -> customerMapper.mapToCustomerDTO(name)).collect(Collectors.toList());
        if (customerDTOS.isEmpty()) {
            throw new CustomernotFoundException(String.format(CUSTOMER_NOT_FOUND, customer_first, customer_last));
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, String newFirstName, String newLastName, String newPhone, String newEmail) {
        Customer customer = customerRepository.getReferenceById(id);
        if (customer == null) {
            throw new CustomernotFoundException(String.format(CUSTOMER_ID_NOT_FOUND, id));
        }
        customer.setCustomer_first(newFirstName);
        customer.setCustomer_last(newLastName);
        customer.setEmail(newEmail);
        customer.setPhone(newPhone);
        return customerMapper.mapToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> customerFound = customerRepository.findById(id);
        if (customerFound.isPresent()) {
            customerRepository.delete(customerFound.get());
        } else {
            throw new CountryNotFoundException(String.format(CUSTOMER_ID_NOT_FOUND, id));
        }
        return true;
    }
}
