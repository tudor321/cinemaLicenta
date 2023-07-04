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
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "room_cinema",
            joinColumns = {
                    @JoinColumn(name = "room", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "cinema", referencedColumnName = "id")
            }
    )
    private Cinema cinema;


    @Column(name = "room_number")
    private Long room_number;

    @Column(name = "capacity")
    private Long capacity;
}
