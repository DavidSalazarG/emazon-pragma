package com.pragma_bootcamp.emazon.domain.usecase;

import com.pragma_bootcamp.emazon.domain.model.CategoryEntity;
import com.pragma_bootcamp.emazon.domain.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListCategory {

    private final CategoryRepository categoryRepository;

    public ListCategory(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Page<CategoryEntity> listCategories(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return categoryRepository.findAll(pageRequest);
    }
}