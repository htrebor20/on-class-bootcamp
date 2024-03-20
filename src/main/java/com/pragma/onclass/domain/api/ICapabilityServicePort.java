package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.model.Capability;

import java.util.List;

public interface ICapabilityServicePort {
    void saveCapability(Capability capability, List<Long> technologyIds);
}
