package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.Constants;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;

import java.util.List;
import java.util.Optional;

public class BootcampUseCase implements IBootcampServicePort {
    private final IBootcampPersistencePort bootcampPersistencePort;
    private final ICapabilityServicePort capabilityServicePort;

    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort, ICapabilityServicePort capabilityServicePort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
        this.capabilityServicePort = capabilityServicePort;
    }

    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        if (bootcamp.getCapabilities().isEmpty() || bootcamp.getCapabilities().size() > 4) {
            throw new BadRequestValidationException(Constants.BOOTCAMP_VALIDATIONS_EXCEPTION_MESSAGE);
        }
        List<Long> ids = bootcamp.getCapabilities().stream().map(Capability::getId).toList();
        List<Capability> allCapabilitiesByIds = capabilityServicePort.getAllCapabilitiesByIds(ids);
        bootcamp.setCapabilities(allCapabilitiesByIds);
        bootcampPersistencePort.saveBootcamp(bootcamp);
    }

    @Override
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, ConstantsAdapters.Sort sort, ConstantsAdapters.SortBy sortBy) {
        return bootcampPersistencePort.getAllBootcamp(page, size, sort, sortBy);
    }

    @Override
    public Bootcamp findById(Long id) {
        Optional<Bootcamp> bootcamp = Optional.ofNullable(bootcampPersistencePort.findById(id));
        if(bootcamp.isEmpty()) {
            throw new BadRequestValidationException(String.format(Constants.ID_BOOTCAMP_VALIDATIONS_EXCEPTION_MESSAGE, id));
        }
        return bootcamp.get();
    }
}
