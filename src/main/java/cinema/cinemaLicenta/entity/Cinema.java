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
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "cinema_address",
            joinColumns = {
                    @JoinColumn(name = "cinema", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "address", referencedColumnName = "id")
            }
    )
    private Address address;

    @Column(name = "cinema_name")
    private String cinema_name;
}
