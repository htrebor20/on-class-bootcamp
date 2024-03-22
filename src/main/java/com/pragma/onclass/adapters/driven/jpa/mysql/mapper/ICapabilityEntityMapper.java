package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapabilityEntityMapper {
    ITechnologyEntityMapper technologyEntityMapper = Mappers.getMapper(ITechnologyEntityMapper.class);

    Capability toModel(CapabilityEntity capabilityEntity);
    CapabilityEntity toEntity(Capability capability);

    @Mapping(target = "technologies", expression = "java(mapTechnologies(capabilityEntity.getTechnologies()))")
    List<Capability> toCapabilityResponseList(List<CapabilityEntity> capabilities);

    default List<Technology> mapTechnologies(List<TechnologyEntity> technologyEntities) {
        return technologyEntityMapper.toTechenologyResponseList(technologyEntities);
    }
}
