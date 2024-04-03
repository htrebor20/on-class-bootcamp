package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.Constants;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import com.pragma.onclass.utilities.Utility;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CapabilityUseCase implements ICapabilityServicePort {
    private final ICapabilityPersistencePort capabilityPersistencePort;
    private final ITechnologyServicePort technologyServicePort;
    public CapabilityUseCase(ICapabilityPersistencePort capabilityPersistencePort, ITechnologyServicePort technologyServicePort) {
        this.capabilityPersistencePort = capabilityPersistencePort;
        this.technologyServicePort = technologyServicePort;
    }

    @Override
    public void saveCapability(Capability capability) throws BadRequestValidationException {
        List<Long> ids = capability.getTechnologies().stream().map(Technology::getId).toList();

        if(Utility.hasRepeatedIds(ids)) {
            throw new BadRequestValidationException(Constants.CAPABILITY_REPEATED_VALIDATIONS_EXCEPTION_MESSAGE);}

        if (ids.size() < 3 || ids.size() > 20) {
                throw new BadRequestValidationException(Constants.CAPABILITY_VALIDATIONS_EXCEPTION_MESSAGE);
        }

        List<Technology> technologies = technologyServicePort.getAllTechnologiesByIds(ids);
        capability.setTechnologies(technologies);
        capabilityPersistencePort.saveCapability(capability);
    }

    @Override
    public List<Capability> getAllCapability(Integer page, Integer size, ConstantsAdapters.Sort sort, ConstantsAdapters.SortBy sortBy) {
        Pageable pagination;
        if (sort != null && sortBy != null) {
            if (sortBy == ConstantsAdapters.SortBy.TECHNOLOGY_COUNT) {
                pagination = PageRequest.of(page, size);
                if (sort == ConstantsAdapters.Sort.ASC) {
                    return capabilityPersistencePort.findAllSortedByTechnologyCountAsc(pagination);
                } else {
                    return capabilityPersistencePort.findAllSortedByTechnologyCountDesc(pagination);
                }
            } else {
                if (sort == ConstantsAdapters.Sort.ASC) {
                    pagination = PageRequest.of(page, size, Sort.by("name").ascending());

                } else {
                    pagination = PageRequest.of(page, size, Sort.by("name").descending());
                }
            }
        } else {
            pagination = PageRequest.of(page, size);
        }
        return capabilityPersistencePort.getAllCapability(pagination);
    }

    @Override
    public List<Capability> getAllCapabilitiesByIds(List<Long> ids) {
        return capabilityPersistencePort.getAllCapabilitiesByIds(ids);
    }
}



