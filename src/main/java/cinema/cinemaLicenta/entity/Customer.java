package cinema.cinemaLicenta.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_first")
    private String customer_first;

    @Column(name = "customer_last")
    private String customer_last;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
