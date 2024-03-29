package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IBootcampRequestMapper {
    @Mapping(target = "capabilities", source = "capabilityIds")
    Bootcamp addRequestToBootcamp(AddBootcampRequest addBootcampRequest);

    default List<Capability> mapCapabilityIds(List<Long> capabilityIds) {
        return capabilityIds.stream()
                .map(id -> new Capability(id, null, null, null, null))
                .toList();
    }
}
