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
@Table(name = "screening")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private String start_time;

    @Column(name = "end_time")
    private String end_time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "screening_film",
            joinColumns = {
                    @JoinColumn(name = "screening", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "film", referencedColumnName = "id")
            }
    )
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "screening_room",
            joinColumns = {
                    @JoinColumn(name = "screening", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "room", referencedColumnName = "id")
            }
    )
    private Room room;
}
