package com.pragma.onclass.domain.spi;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.model.Capability;

import java.util.List;

public interface ICapabilityPersistencePort {
    void saveCapability(Capability capability);
    List<Capability> getAllCapability(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy);
    List<Capability> getAllCapabilitiesByIds(List<Long> ids);
}
