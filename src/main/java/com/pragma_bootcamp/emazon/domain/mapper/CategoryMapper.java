package com.pragma_bootcamp.emazon.domain.mapper;

import com.pragma_bootcamp.emazon.domain.model.CategoryEntity;
import org.mapstruct.Mapper;

import com.pragma_bootcamp.emazon.application.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryDtoToCategoryEntity(CategoryEntity category);
    CategoryEntity categoryEntityToCategoryDto(CategoryDto categoryDto);
}
