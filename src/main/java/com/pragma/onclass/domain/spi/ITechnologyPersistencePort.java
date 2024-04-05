package com.pragma.onclass.domain.spi;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.model.Technology;

import java.util.List;
import java.util.Optional;

public interface ITechnologyPersistencePort {
    Optional<Technology> getTechnology(String name);
    List<Technology> getAllTechnology(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy);
    void saveTechnology(Technology technology);
    List<Technology> getAllTechnologiesByIds(List<Long> ids);
}
