package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Technology;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITechnologyPersistencePort {
    Optional<Technology> getTechnology(String name);
    List<Technology> getAllTechnology(Pageable pageable);
    Technology updateTechnology(Technology technology);
    void saveTechnology(Technology technology);
    void deleteTechnology(Long id);
    List<Technology> getAllByIds(List<Long> ids);
}
