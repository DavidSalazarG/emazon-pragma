package com.pragma_bootcamp.emazon.application.rest;

import com.pragma_bootcamp.emazon.application.dto.CategoryDto;
import com.pragma_bootcamp.emazon.application.dto.CategoryFilter;
import com.pragma_bootcamp.emazon.domain.model.CategoryEntity;
import com.pragma_bootcamp.emazon.domain.usecase.CreateCategory;
import com.pragma_bootcamp.emazon.domain.usecase.ListCategory;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategory createCategory;
    private final ListCategory listCategory;

    public CategoryController(CreateCategory createCategory,
                              ListCategory listCategories){
        this.createCategory = createCategory;
        this.listCategory = listCategories;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryEntity newCategory = new CategoryEntity(null, categoryDto.getName(), categoryDto.getDescription());
        CategoryEntity createdCategory = createCategory.createCategory(newCategory);
        categoryDto.setId(createdCategory.getId());
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(CategoryFilter categoryFilterDto) {
        Page<CategoryEntity> categoriesPage = listCategory.listCategories(
                categoryFilterDto.getPageNumber(),
                categoryFilterDto.getPageSize(),
                categoryFilterDto.getSortBy(),
                categoryFilterDto.getSortDirection()
        );

        List<CategoryDto> categories = categoriesPage.getContent().stream()
                .map(category -> new CategoryDto(category.getId(), category.getName(),
                        category.getDescription()))
                .collect(Collectors.toList());

        return  ResponseEntity.ok().body(categories);
    }
}
