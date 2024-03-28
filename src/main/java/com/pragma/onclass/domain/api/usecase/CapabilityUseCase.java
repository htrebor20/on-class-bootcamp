package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.Constants;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CapabilityUseCase implements ICapabilityServicePort {
    private final ICapabilityPersistencePort capabilityPersistencePort;

    public CapabilityUseCase(ICapabilityPersistencePort capabilityPersistencePort) {
        this.capabilityPersistencePort = capabilityPersistencePort;
    }

    @Override
    public void saveCapability(Capability capability) throws BadRequestValidationException {
        if (capability.getTechnologies().size() < 3 || capability.getTechnologies().size() > 20) {
            throw new BadRequestValidationException(Constants.CAPABILITY_VALIDATIONS_EXCEPTION_MESSAGE);
        }
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


