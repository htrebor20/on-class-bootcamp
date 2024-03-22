package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Capability;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICapabilityPersistencePort {
    void saveCapability(Capability capability);
    List<Capability> getAllCapability(Pageable pageable);
}
