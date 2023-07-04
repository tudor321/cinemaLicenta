package cinema.cinemaLicenta.services;

import cinema.cinemaLicenta.dto.CategoryDTO;
import cinema.cinemaLicenta.entity.Category;
import cinema.cinemaLicenta.exception.CategoryNotFoundException;
import cinema.cinemaLicenta.mapper.CategoryMapper;
import cinema.cinemaLicenta.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cinema.cinemaLicenta.constants.ProjectConstants.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceImpl {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return categoryMapper.mapToCategoryDTO(categoryRepository.save(categoryMapper.mapToCategory(categoryDTO)));
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < categoryRepository.findAll().size(); i++) {
            categories.add(categoryRepository.findAll().get(i));
        }
        return categories;
    }

    @Override
    public List<CategoryDTO> getByName(String category_name) {
        List<CategoryDTO> categoryDTOS = categoryRepository.findCategoriesByCategory_name(category_name).stream()
                .map(name -> categoryMapper.mapToCategoryDTO(name)).collect(Collectors.toList());
        if (categoryDTOS.isEmpty()) {
            throw new CategoryNotFoundException(String.format(CATEGORY_NOT_FOUND, category_name));
        }
        return categoryDTOS;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Category> categoryFound = categoryRepository.findById(id);
        if (categoryFound.isPresent()) {
            categoryRepository.delete(categoryFound.get());
        } else {
            throw new CategoryNotFoundException(String.format(CATEGORY_NOT_FOUND, id));
        }
        return true;
    }

}
