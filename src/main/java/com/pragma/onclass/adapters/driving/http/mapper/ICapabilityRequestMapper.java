package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddCapabilityRequest;

import com.pragma.onclass.domain.model.Capability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICapabilityRequestMapper {
    @Mapping(target = "id", ignore = true)
    Capability addRequestToCapability(AddCapabilityRequest addCapabilityRequest);
}
