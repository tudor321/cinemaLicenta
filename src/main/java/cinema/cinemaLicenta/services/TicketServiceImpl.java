package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.TicketDTO;
import cinema.cinemaLicenta.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketServiceImpl {
    TicketDTO addTicket(TicketDTO ticketDTO);

    Optional<Ticket> getTicket(Long id);

    List<TicketDTO> getTicketyBySeat(String seat_number);

    TicketDTO updateTicket(Long id, String newPrice, String newSeatNumber);

    boolean delete(Long id);
}
