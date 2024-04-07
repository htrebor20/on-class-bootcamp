package com.pragma.onclass.utilities;

import com.pragma.onclass.adapters.ConstantsAdapters;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Sorting {
    private Sorting() {
        throw new IllegalStateException("utility class");
    }

    public static Pageable sortByField(Integer page, Integer size, ConstantsAdapters.Sort sort, ConstantsAdapters.SortBy sortBy) {
        String field = enumToCamelCase(sortBy);
        Sort.Direction direction = (sort == ConstantsAdapters.Sort.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sortObject = Sort.by(direction, field);
        return PageRequest.of(page, size, sortObject);
    }

    public static String enumToCamelCase(ConstantsAdapters.SortBy sortBy) {
        if (sortBy == ConstantsAdapters.SortBy.BOOTCAMP_NAME) {
            return "bootcamp.name";
        } else {
            String[] parts = sortBy.name().toLowerCase().split("_");
            StringBuilder camelCase = new StringBuilder(parts[0]);

            for (int i = 1; i < parts.length; i++) {
                camelCase.append(Character.toUpperCase(parts[i].charAt(0)))
                        .append(parts[i].substring(1));
            }
            return camelCase.toString();
        }
    }
}