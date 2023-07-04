package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Override
    Optional<Actor> findById(Long id);

    @Query("SELECT A FROM Actor A WHERE A.first_name = :first_name AND A.last_name =:last_name")
    List<Actor> findByFirst_nameAndLast_name(String first_name, String last_name);
}
