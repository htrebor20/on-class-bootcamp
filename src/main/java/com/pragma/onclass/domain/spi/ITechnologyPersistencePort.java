package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Technology;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITechnologyPersistencePort {
    Optional<Technology> getTechnology(String name);
    List<Technology> getAllTechnology(Pageable pageable);
    void saveTechnology(Technology technology);
    List<Technology> getAllTechnologiesByIds(List<Long> ids);
}
