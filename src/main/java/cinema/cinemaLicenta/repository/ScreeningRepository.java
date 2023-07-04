package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Override
    Optional<Screening> findById(Long id);

    @Query("SELECT S FROM Screening S WHERE S.start_time = :start_time")
    List<Screening> findScreeningByStart_time(String start_time);

}
