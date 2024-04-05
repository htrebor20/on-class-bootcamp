package com.pragma.onclass.utilities;

import com.pragma.onclass.adapters.ConstantsAdapters;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public  class Sorting {
    private Sorting() {
        throw new IllegalStateException("utility class");
    }

    public static Pageable sortByField(Integer page, Integer size, ConstantsAdapters.Sort sort, ConstantsAdapters.SortBy sortBy) {
        String field = sortBy.name().toLowerCase();
        Sort.Direction direction = (sort == ConstantsAdapters.Sort.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sortObject = Sort.by(direction, field);
        return PageRequest.of(page, size, sortObject);
    }
}
