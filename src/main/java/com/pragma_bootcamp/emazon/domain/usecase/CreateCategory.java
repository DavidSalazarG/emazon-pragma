package com.pragma_bootcamp.emazon.domain.usecase;

import com.pragma_bootcamp.emazon.domain.model.CategoryEntity;
import com.pragma_bootcamp.emazon.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCategory {

    private final CategoryRepository categoryRepository;

    public CreateCategory(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        if (categoryRepository.findByName(categoryEntity.getName()).isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        return categoryRepository.save(categoryEntity);
    }
}