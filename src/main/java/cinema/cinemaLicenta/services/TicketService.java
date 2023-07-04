package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.TicketDTO;
import cinema.cinemaLicenta.entity.Customer;
import cinema.cinemaLicenta.entity.Screening;
import cinema.cinemaLicenta.entity.Ticket;
import cinema.cinemaLicenta.exception.ConditionNotFoundException;
import cinema.cinemaLicenta.exception.TicketNotFoundException;
import cinema.cinemaLicenta.mapper.TicketMapper;
import cinema.cinemaLicenta.repository.CustomerRepository;
import cinema.cinemaLicenta.repository.ScreeningRepository;
import cinema.cinemaLicenta.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class TicketService implements TicketServiceImpl {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public TicketDTO addTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.mapToTicket(ticketDTO);
        if ((ticketDTO.getScreeningDTO() != null) && (ticketDTO.getCustomerDTO() != null)) {
            Optional<Screening> screening = screeningRepository.findById(ticketDTO.getScreeningDTO().getId());
            Optional<Customer> customer = customerRepository.findById(ticketDTO.getCustomerDTO().getId());

            if (screening.isEmpty() && customer.isEmpty()) {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND, ticket.getScreening(), ticket.getCustomer()));
            }
            ticket.setCustomer(customer.get());
            ticket.setScreening(screening.get());
        }
        return ticketMapper.mapToTicketDTO(ticketRepository.save(ticket));
    }

    @Override
    public Optional<Ticket> getTicket(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<TicketDTO> getTicketyBySeat(String seat_number) {
        List<TicketDTO> ticketDTOS = ticketRepository.findTicketBySeat_number(seat_number).stream()
                .map(name -> ticketMapper.mapToTicketDTO(name)).collect(Collectors.toList());
        if (ticketDTOS.isEmpty()) {
            throw new TicketNotFoundException(String.format(TICKET_NOT_FOUND, seat_number));
        }
        return ticketDTOS;
    }

    @Override
    public TicketDTO updateTicket(Long id, String newPrice, String newSeatNumber) {
        Ticket ticket = ticketRepository.getReferenceById(id);
        if (ticket == null) {
            throw new TicketNotFoundException(String.format(TICKET_ID_NOT_FOUND, id));
        }
        ticket.setPrice(newPrice);
        ticket.setSeat_number(newSeatNumber);
        return ticketMapper.mapToTicketDTO(ticketRepository.save(ticket));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Ticket> ticketFound = ticketRepository.findById(id);
        if (ticketFound.isPresent()) {
            ticketRepository.delete(ticketFound.get());
        } else {
            throw new TicketNotFoundException(String.format(TICKET_ID_NOT_FOUND, id));
        }
        return true;
    }
}
