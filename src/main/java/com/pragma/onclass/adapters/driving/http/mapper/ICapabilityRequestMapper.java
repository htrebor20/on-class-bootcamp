package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddCapabilityRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.CapabilityResponse;
import com.pragma.onclass.domain.model.Capability;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapabilityRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "technologies", ignore = true)
    Capability addRequestToCapability(AddCapabilityRequest addCapabilityRequest);

    @Mapping(target = "technologies",  qualifiedByName = "mapListTechnologiesWithoutDesc")
    List<CapabilityResponse>toCapabilityResponseList(List<Capability> capabilities);
}
