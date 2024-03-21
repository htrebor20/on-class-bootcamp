package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.model.Capability;
import org.apache.coyote.BadRequestException;

public interface ICapabilityServicePort {
    void saveCapability(Capability capability) throws BadRequestException;
}
