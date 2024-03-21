package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    @Mapping(target = "capabilityList", ignore = true)
    Technology toModel(TechnologyEntity technologyEntity);
    @Mapping(target = "capabilityList", ignore = true)
    TechnologyEntity toEntity(Technology technology);
    @Mapping(target = "capabilityList", ignore = true)
    List<Technology> toTechenologyResponseList(List<TechnologyEntity> technologies);
}
