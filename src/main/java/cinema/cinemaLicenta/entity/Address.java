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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "address_city",
            joinColumns = {
                    @JoinColumn(name = "address", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "city", referencedColumnName = "id")
            }
    )
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private Long postal_code;

    public Address(int i, String address) {
        this.address = address;

    }


}
