package com.pragma_bootcamp.emazon.application.dto;

import lombok.Data;

@Data
public class CategoryFilter {
    private int pageNumber = 0;
    private int pageSize = 10;
    private String sortBy = "name";
    private String sortDirection = "asc";
}
