package com.pragma.onclass.domain.api;

import com.pragma.onclass.adapters.Constants;
import com.pragma.onclass.domain.model.Technology;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ITechnologyServicePort {
    Technology getTechnology(String name);
    List<Technology> getAllTechnology(Integer page, Integer size, Constants.Sort sort);
    Technology updateTechnology(Technology technology);
    void saveTechnology(Technology technology) throws BadRequestException;
    void deleteTechnology(Long id);
    List<Technology> getAllTechnologiesByIds(List<Long> ids);
}

