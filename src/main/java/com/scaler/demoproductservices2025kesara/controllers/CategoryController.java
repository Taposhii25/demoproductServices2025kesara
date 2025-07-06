package com.scaler.demoproductservices2025kesara.controllers;

import com.scaler.demoproductservices2025kesara.repository.CategoryRepository;
import com.scaler.demoproductservices2025kesara.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @DeleteMapping("/{id}")
    public void deletecategory(@PathVariable("id") Long categoryid) {
        categoryService.deleteCategory(categoryid);
    }
}
