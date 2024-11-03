package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTO.CategoryDTO;
import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDTO));
    }

    @GetMapping
    ResponseEntity<List<CategoryDTO>> getAllCategories() throws Exception {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId,
                                               @RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId,categoryDTO));
    }


    @DeleteMapping("/{categoryId}")
    ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) throws Exception {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted category successfully");
    }
}
