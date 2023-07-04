package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.CustomerDTO;
import cinema.cinemaLicenta.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer maptToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .customer_first(customerDTO.getCustomer_first())
                .customer_last(customerDTO.getCustomer_last())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .build();
    }

    public CustomerDTO mapToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .customer_first(customer.getCustomer_first())
                .customer_last(customer.getCustomer_last())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }
}
