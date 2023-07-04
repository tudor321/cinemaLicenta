package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.TicketDTO;
import cinema.cinemaLicenta.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ScreeningMapper screeningMapper;

    public Ticket mapToTicket(TicketDTO ticketDTO) {
        if ((ticketDTO.getCustomerDTO() != null) && (ticketDTO.getScreeningDTO() != null)) {
            return Ticket.builder()
                    .price(ticketDTO.getPrice())
                    .seat_number(ticketDTO.getSeat_number())
                    .customer(customerMapper.maptToCustomer(ticketDTO.getCustomerDTO()))
                    .screening(screeningMapper.mapToScreening(ticketDTO.getScreeningDTO()))
                    .build();
        } else {
            return Ticket.builder()
                    .price(ticketDTO.getPrice())
                    .seat_number(ticketDTO.getSeat_number())
                    .build();
        }
    }


    public TicketDTO mapToTicketDTO(Ticket ticket) {
        if ((ticket.getScreening() != null) && (ticket.getCustomer() != null)) {
            return TicketDTO.builder()
                    .id(ticket.getId())
                    .price(ticket.getPrice())
                    .seat_number(ticket.getSeat_number())
                    .screeningDTO(screeningMapper.mapToScreeningDTO(ticket.getScreening()))
                    .customerDTO(customerMapper.mapToCustomerDTO(ticket.getCustomer()))
                    .build();
        } else {
            return TicketDTO.builder()
                    .id(ticket.getId())
                    .price(ticket.getPrice())
                    .seat_number(ticket.getSeat_number())
                    .build();
        }
    }

}
