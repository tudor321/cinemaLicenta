package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CategoryDTO;
import cinema.cinemaLicenta.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceImpl {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    Optional<Category> getCategory(Long id);

    List<Category> getAllCategories();

    List<CategoryDTO> getByName(String category_name);

    boolean delete(Long id);
}
