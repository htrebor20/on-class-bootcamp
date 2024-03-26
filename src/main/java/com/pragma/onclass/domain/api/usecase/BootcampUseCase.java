package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.Constants;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;

import java.util.List;

public class BootcampUseCase implements IBootcampServicePort {
    private final IBootcampPersistencePort bootcampPersistencePort;
    private final ICapabilityServicePort capabilityServicePort;

    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort, ICapabilityServicePort capabilityServicePort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
        this.capabilityServicePort = capabilityServicePort;
    }

    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        List<Long> ids = bootcamp.getCapabilities().stream().map(capability -> capability.getId()).toList();
        List<Capability> allCapabilitiesByIds = capabilityServicePort.getAllCapabilitiesByIds(ids);
        bootcamp.setCapabilities(allCapabilitiesByIds);
        if (bootcamp.getCapabilities().isEmpty() || bootcamp.getCapabilities().size() > 4) {
            throw new BadRequestValidationException(Constants.BOOTCAMP_VALIDATIONS_EXCEPTION_MESSAGE);
        }
        bootcampPersistencePort.saveBootcamp(bootcamp);
    }
}
