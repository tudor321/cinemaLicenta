package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Override
    Optional<Ticket> findById(Long id);

    @Query("SELECT T FROM Ticket T WHERE T.seat_number = :seat_number")
    List<Ticket> findTicketBySeat_number(String seat_number);
}
