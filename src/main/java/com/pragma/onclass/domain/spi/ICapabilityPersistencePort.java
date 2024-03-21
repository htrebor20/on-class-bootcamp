package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Capability;

public interface ICapabilityPersistencePort {
    void saveCapability(Capability capability);
}
