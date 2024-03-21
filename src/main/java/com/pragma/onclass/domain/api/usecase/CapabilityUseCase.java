package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import org.apache.coyote.BadRequestException;

public class CapabilityUseCase implements ICapabilityServicePort {
    final private ICapabilityPersistencePort capabilityPersistencePort;

    public CapabilityUseCase(ICapabilityPersistencePort capabilityPersistencePort) {
        this.capabilityPersistencePort = capabilityPersistencePort;
    }

    @Override
    public void saveCapability(Capability capability) throws BadRequestValidationException {
        if(capability.getTechnologies().size()<=3 & capability.getTechnologies().size()<=20) {
            throw new BadRequestValidationException(com.pragma.onclass.domain.Constants.CAPABILITY_VALIDATIONS_EXCEPTION_MESSAGE);
        }
        capabilityPersistencePort.saveCapability(capability);
    }
}
