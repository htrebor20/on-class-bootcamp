package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;

import java.util.List;

public class CapabilityUseCase implements ICapabilityServicePort {
    final private ICapabilityPersistencePort capabilityPersistencePort;
    final private ITechnologyPersistencePort technologyPersistencePort;

    public CapabilityUseCase(ICapabilityPersistencePort capabilityPersistencePort, ITechnologyPersistencePort technologyPersistencePort) {
        this.capabilityPersistencePort = capabilityPersistencePort;
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public void saveCapability(Capability capability, List<Long> technologyIds) {
    List<Technology> technologyResponse = technologyPersistencePort.getAllByIds(technologyIds);
        capability.setTechnologies(technologyResponse);
        capabilityPersistencePort.saveCapability(capability);
    }
}
