package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CategoryDTO;
import cinema.cinemaLicenta.entity.Category;
import cinema.cinemaLicenta.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCategories() {
        CategoryDTO categoryDTO = new CategoryDTO();
        // Set categoryDTO properties as needed

        CategoryDTO expectedResponse = new CategoryDTO();
        // Set expectedResponse properties as needed

        when(categoryService.addCategory(categoryDTO)).thenReturn(expectedResponse);

        ResponseEntity<CategoryDTO> response = categoryController.addCategories(categoryDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetAllCategories() {
        List<Category> expectedCategories = Arrays.asList(new Category(), new Category());
        // Set expectedCategories as needed

        when(categoryService.getAllCategories()).thenReturn(expectedCategories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedCategories);
    }

    @Test
    public void testGetOneCategory() {
        Long categoryId = 1L;
        Optional<Category> expectedCategory = Optional.of(new Category());
        // Set expectedCategory as needed

        when(categoryService.getCategory(categoryId)).thenReturn(expectedCategory);

        ResponseEntity<Optional<Category>> response = categoryController.getOneCategory(categoryId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedCategory);
    }

    @Test
    public void testGetCategoryByName() {
        String categoryName = "example";
        List<CategoryDTO> expectedCategories = Arrays.asList(new CategoryDTO(), new CategoryDTO());
        // Set expectedCategories as needed

        when(categoryService.getByName(categoryName)).thenReturn(expectedCategories);

        ResponseEntity<List<CategoryDTO>> response = categoryController.getCategoryByName(categoryName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedCategories);
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;
        String expectedResponse = String.format("Category %d was deleted", categoryId);

        when(categoryService.delete(categoryId)).thenReturn(true);

        ResponseEntity<String> response = categoryController.deleteCategory(categoryId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);

        verify(categoryService, times(1)).delete(categoryId);
    }
}
