package cinema.cinemaLicenta.mapper;

import cinema.cinemaLicenta.dto.CategoryDTO;
import cinema.cinemaLicenta.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category mapToCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .category_name(categoryDTO.getCategory_name())
                .build();
    }

    public CategoryDTO mapToCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .category_name(category.getCategory_name())
                .build();

    }
}
