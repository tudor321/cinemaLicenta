package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Override
    Optional<Film> findById(Long id);

    @Query("SELECT F FROM Film F WHERE F.title = :title")
    List<Film> findFilmByTitle(String title);
}
