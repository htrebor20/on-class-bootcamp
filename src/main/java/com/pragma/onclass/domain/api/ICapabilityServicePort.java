package com.pragma.onclass.domain.api;

import com.pragma.onclass.adapters.Constants;
import com.pragma.onclass.domain.model.Capability;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ICapabilityServicePort {
    void saveCapability(Capability capability) throws BadRequestException;
    List<Capability> getAllCapability(Integer page, Integer size, Constants.Sort sort);
}
