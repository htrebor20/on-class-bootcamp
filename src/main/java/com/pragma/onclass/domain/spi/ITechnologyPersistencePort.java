package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Technology;

import java.util.List;

public interface ITechnologyPersistencePort {
    Technology getTechnology(String name);
    List<Technology> getAllTechnology(Integer page, Integer size);
    Technology updateTechnology(Technology technology);
    void saveTechnology(Technology technology);
    void deleteTechnology(Long id);
}
