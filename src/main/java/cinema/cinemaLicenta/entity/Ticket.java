package cinema.cinemaLicenta.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private String price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "ticket_screening",
            joinColumns = {
                    @JoinColumn(name = "ticket", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "screening", referencedColumnName = "id")
            }
    )
    private Screening screening;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "ticket_customer",
            joinColumns = {
                    @JoinColumn(name = "ticket", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "customer", referencedColumnName = "id")
            }
    )
    private Customer customer;

    @Column(name = "seat_number")
    private String seat_number;

}
