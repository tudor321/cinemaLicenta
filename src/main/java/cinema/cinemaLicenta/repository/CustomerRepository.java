package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long id);

    @Query("SELECT A FROM Customer A WHERE A.customer_first = :customer_first AND A.customer_last =:customer_last")
    List<Customer> findByCustomer_firstAndCustomer_last(String customer_first, String customer_last);
}
