package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.TicketDTO;
import cinema.cinemaLicenta.entity.Ticket;
import cinema.cinemaLicenta.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTicket() {
        TicketDTO ticketDTO = TicketDTO.builder()
                .price("10.00")
                .seat_number("A1")
                .build();

        TicketDTO expectedResponse = TicketDTO.builder()
                .id(1L)
                .price("10.00")
                .seat_number("A1")
                .build();

        when(ticketService.addTicket(any(TicketDTO.class))).thenReturn(expectedResponse);

        ResponseEntity<TicketDTO> response = ticketController.addTicket(ticketDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(ticketService, times(1)).addTicket(any(TicketDTO.class));
    }

    @Test
    void testGetOneTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);

        when(ticketService.getTicket(ticketId)).thenReturn(Optional.of(ticket));

        ResponseEntity<Optional<Ticket>> response = ticketController.getOneTicket(ticketId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get().getId()).isEqualTo(ticketId);

        verify(ticketService, times(1)).getTicket(ticketId);
    }

    @Test
    void testGetTicketBySeat() {
        String seat_number = "A1";

        when(ticketService.getTicketyBySeat(seat_number)).thenReturn(List.of());

        ResponseEntity<List<TicketDTO>> response = ticketController.getBySeat(seat_number);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        verify(ticketService, times(1)).getTicketyBySeat(seat_number);
    }

    @Test
    void testUpdateTicket() {
        Long ticketId = 1L;
        String price = "15.00";
        String seatNumber = "B2";

        TicketDTO updatedTicketDTO = TicketDTO.builder()
                .id(ticketId)
                .price(price)
                .seat_number(seatNumber)
                .build();

        when(ticketService.updateTicket(anyLong(), anyString(), anyString())).thenReturn(updatedTicketDTO);

        ResponseEntity<TicketDTO> response = ticketController.updateATicket(ticketId, price, seatNumber);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedTicketDTO);

        verify(ticketService, times(1)).updateTicket(ticketId, price, seatNumber);
    }

    @Test
    void testDeleteTicket() {
        Long ticketId = 1L;
        String expectedResponse = String.format("Ticket %d was deleted", ticketId);

        ResponseEntity<String> response = ticketController.deleteScreening(ticketId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(ticketService, times(1)).delete(ticketId);
    }
}
