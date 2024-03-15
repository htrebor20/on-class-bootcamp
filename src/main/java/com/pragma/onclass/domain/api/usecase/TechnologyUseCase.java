package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {
    final private ITechnologyPersistencePort technologyPersistencePort;
    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }
    @Override
    public Technology getTechnology(String name) {
        return null;
    }

    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size) {
        return null;
    }

    @Override
    public Technology updateTechnology(Technology technology) {
        return null;
    }

    @Override
    public void saveTechnology(Technology technology) {
        technologyPersistencePort.saveTechnology(technology);
    }

    @Override
    public void deleteTechnology(Long id) {

    }
}
