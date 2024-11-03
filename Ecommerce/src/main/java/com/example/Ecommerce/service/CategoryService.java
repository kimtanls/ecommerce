package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.CategoryDTO;
import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryDTO) {

        Category category = new Category();

        category.setName(categoryDTO.getName());

        category.setDescription(categoryDTO.getDescription());

        category.setCreatedAt(new Date());

        return categoryRepository.save(category);
    }

    public CategoryDTO updateCategory(Long categoryId , CategoryDTO categoryDTO) throws Exception {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new Exception("Category not found"));
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setUpdatedAt(new Date());
        return categoryRepository.save(category).convertToDTO();
    }

    public List<CategoryDTO> getAllCategories() throws Exception {
        // Chuyển Iterable thành Stream và sau đó chuyển đổi sang List
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(Category::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteCategory(Long categoryId) throws Exception {
        categoryRepository.deleteById(categoryId);
    }

}
