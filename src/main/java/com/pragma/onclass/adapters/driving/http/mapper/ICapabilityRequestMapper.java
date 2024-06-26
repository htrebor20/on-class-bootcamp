package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddCapabilityRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.BootcampCapabilityResponse;
import com.pragma.onclass.adapters.driving.http.dto.response.CapabilityResponse;
import com.pragma.onclass.domain.model.Capability;

import com.pragma.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapabilityRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "technologies", source = "technologyIds")
    Capability addRequestToCapability(AddCapabilityRequest addCapabilityRequest);

    default List<Technology> mapTechnologyIds(List<Long> technologies) {
        return technologies.stream()
                .map(id -> new Technology(id, null, null))
                .toList();
    }

    @Mapping(target = "technologies",  qualifiedByName = "mapListTechnologiesWithoutDesc")
    List<CapabilityResponse>toCapabilityResponseList(List<Capability> capabilities);

    @Named("mapListCapabilitiesWithoutDesc")
    @Mapping(target = "description", ignore = true)
    List<BootcampCapabilityResponse> toCapabilitiesResponseListWithoutDesc(List<Capability> capabilities);
}
