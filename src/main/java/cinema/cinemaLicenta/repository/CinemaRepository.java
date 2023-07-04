package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Override
    Optional<Cinema> findById(Long id);

    @Query("SELECT C FROM Cinema C WHERE C.cinema_name = :cinema_name")
    List<Cinema> findCinemaByCinema_name(String cinema_name);
}
