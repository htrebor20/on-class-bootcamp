package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.onclass.domain.model.Capability;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICapabilityEntityMapper {
    Capability toModel(CapabilityEntity capabilityEntity);
    CapabilityEntity toEntity(Capability capability);
}
