package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Override
    Optional<Country> findById(Long id);

    @Query("SELECT C FROM Country C WHERE C.country_name = :country_name")
    List<Country> findCountryByCountry_name(String country_name);
}
