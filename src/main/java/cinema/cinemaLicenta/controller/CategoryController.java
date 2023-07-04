package cinema.cinemaLicenta.controller;

import cinema.cinemaLicenta.dto.CategoryDTO;
import cinema.cinemaLicenta.entity.Category;
import cinema.cinemaLicenta.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cinema.cinemaLicenta.constants.ProjectConstants.CATEGORY_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @Autowired

    private CategoryService categoryService;

    @PostMapping("/add")
    @ApiOperation(value = "Add a new category")

    public ResponseEntity<CategoryDTO> addCategories(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all categories")

    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get category by id")

    public ResponseEntity<Optional<Category>> getOneCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping("/getByName/{category_name:[a-zA-Z]*}")
    @ApiOperation(value = "Get category by name")
    ResponseEntity<List<CategoryDTO>> getCategoryByName(@PathVariable String category_name) {
        return ResponseEntity.ok(categoryService.getByName(category_name));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a category")

    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(String.format(CATEGORY_WAS_DELETED, id));
    }
}
