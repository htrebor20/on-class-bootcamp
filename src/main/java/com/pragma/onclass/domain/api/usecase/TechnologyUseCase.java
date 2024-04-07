package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {
    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy) {
        return technologyPersistencePort.getAllTechnology(page, size, sortType, sortBy);
    }

    @Override
    public void saveTechnology(Technology technology) throws BadRequestValidationException {
        if (technologyPersistencePort.getTechnology(technology.getName()).isPresent()) {
            throw new BadRequestValidationException(com.pragma.onclass.domain.Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        technologyPersistencePort.saveTechnology(technology);
    }

    @Override
    public List<Technology> getAllTechnologiesByIds(List<Long> ids) {
        return technologyPersistencePort.getAllTechnologiesByIds(ids);
    }
}
