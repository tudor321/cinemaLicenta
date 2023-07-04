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
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "city_country",
            joinColumns = {
                    @JoinColumn(name = "city", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "country", referencedColumnName = "id")
            }
    )
    private Country country;

    @Column(name = "city_name")
    private String city_name;

    public void setName(String name) {
        this.city_name = name;
    }

}
