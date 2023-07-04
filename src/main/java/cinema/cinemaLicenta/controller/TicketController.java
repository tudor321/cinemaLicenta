package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.TicketDTO;
import cinema.cinemaLicenta.entity.Ticket;
import cinema.cinemaLicenta.services.TicketService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.TICKET_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a ticket")

    public ResponseEntity<TicketDTO> addTicket(@RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.addTicket(ticketDTO));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get ticket by id")
    public ResponseEntity<Optional<Ticket>> getOneTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @GetMapping("/getByNumber/{number:[0-9]+}")
    @ApiOperation(value = "Get ticket by number")

    public ResponseEntity<List<TicketDTO>> getBySeat(@PathVariable String number) {
        return ResponseEntity.ok(ticketService.getTicketyBySeat(number));
    }

    @PutMapping("/update/{id}/{price}/{seat_number}")
    @ApiOperation(value = "Update a ticket")

    public ResponseEntity<TicketDTO> updateATicket(@PathVariable Long id, @PathVariable String price, @PathVariable String seat_number) {
        return ResponseEntity.ok(ticketService.updateTicket(id, price, seat_number));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a ticket")

    public ResponseEntity<String> deleteScreening(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.ok(String.format(TICKET_WAS_DELETED, id));
    }
}
