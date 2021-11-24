package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.model.entity.categories.Categories;
import br.com.vibbra.organizerecipes.model.response.CategoriesListResponse;
import br.com.vibbra.organizerecipes.model.response.CategoryResponse;
import br.com.vibbra.organizerecipes.service.categories.CategoriesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
@PreAuthorize("hasRole('USER')")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @ApiOperation(value = "Retrieve list of categories by NAME.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesListResponse> list(@RequestParam String name) {
        return ResponseEntity.ok(categoriesService.listCategories(name));
    }

    @ApiOperation(value = "Retrieve category data by ID.")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(categoriesService.getCategory(id));
    }

    @ApiOperation(value = "Create a specific category with the data entered.")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categories> create(@RequestBody Categories category) {
        return new ResponseEntity<>(categoriesService.createCategory(category), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change data for a specific category.")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Categories category) {
        categoriesService.updateCategory(id, category);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Change data for a category and archives.")
    @PutMapping(path = "/{id}/archives", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCategoryArchives(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}