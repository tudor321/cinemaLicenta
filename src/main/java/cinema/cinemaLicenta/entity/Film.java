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
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private String release_year;

    @Column(name = "duration")
    private String duration;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "film_actor",
            joinColumns = {
                    @JoinColumn(name = "film", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "actor", referencedColumnName = "id")
            }
    )
    private Actor actor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "film_category",
            joinColumns = {
                    @JoinColumn(name = "film", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "category", referencedColumnName = "id")
            }
    )
    private Category category;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "film_languages",
            joinColumns = {
                    @JoinColumn(name = "film", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "languages", referencedColumnName = "id")
            }
    )
    private Languages languages;

    public void setReleaseYear(String release_year) {
        this.release_year=release_year;
    }
}
