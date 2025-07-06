package com.scaler.demoproductservices2025kesara.service;

import com.scaler.demoproductservices2025kesara.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

   public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
   }
}
