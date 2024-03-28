package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.CapabilityTechnologyResponse;
import com.pragma.onclass.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {
    @Mapping(target = "id", ignore = true)
    Technology addRequestToTechnology(AddTechnologyRequest addTechnologyRequest);

    List<TechnologyResponse> toTechenologyResponseList(List<Technology> technologies);

    @Named("mapListTechnologiesWithoutDesc")
    @Mapping(target = "description", ignore = true)
    List<CapabilityTechnologyResponse> toTechnologyResponseListWithoutDesc(List<Technology> technologies);
}
