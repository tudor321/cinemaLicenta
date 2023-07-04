package cinema.cinemaLicenta.repository;

import cinema.cinemaLicenta.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("SELECT C FROM Category C WHERE C.category_name = :category_name")
    List<Category> findCategoriesByCategory_name(String category_name);

    @Override
    Optional<Category> findById(Long id);
}
