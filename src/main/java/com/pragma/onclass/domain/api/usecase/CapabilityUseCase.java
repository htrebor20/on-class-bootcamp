package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;

public class CapabilityUseCase implements ICapabilityServicePort {
    final private ICapabilityPersistencePort capabilityPersistencePort;

    public CapabilityUseCase(ICapabilityPersistencePort capabilityPersistencePort) {
        this.capabilityPersistencePort = capabilityPersistencePort;
    }

    @Override
    public void saveCapability(Capability capability) {
        capabilityPersistencePort.saveCapability(capability);
    }
}
