package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Override
    Optional<Address> findById(Long id);

    @Query("SELECT A FROM Address A WHERE A.address = :address")
    List<Address> findAddressByAddress(String address);

    @Query("SELECT B FROM Address B WHERE B.postal_code = :postal_code")
    List<Address> findAddressByPostal_code(Long postal_code);
}
