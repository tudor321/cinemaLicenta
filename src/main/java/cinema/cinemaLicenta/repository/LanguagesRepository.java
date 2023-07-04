package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguagesRepository extends JpaRepository<Languages, Long> {

    @Query("SELECT L FROM Languages L WHERE L.language_name = :language_name")
    List<Languages> findLanguagesBylanguage_name(String language_name);

    @Override
    Optional<Languages> findById(Long id);
}
