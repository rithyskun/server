package com.vpos.server.category;

/*
 * @created 11/04/2023 - 5:05 PM
 * @project server
 * @author Rithy SKUN
 */

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Collection<Category>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(@Valid @PathVariable("id") Long id) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("The category id " + id + " has been removed", Boolean.TRUE);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.updateCategory(id, category));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/categories").toUriString());
        return ResponseEntity.created(uri).body(categoryService.createCategory(category));
    }
}
