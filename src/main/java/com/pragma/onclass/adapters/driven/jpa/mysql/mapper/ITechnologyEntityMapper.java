package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    Technology toModel(TechnologyEntity technologyEntity);

    TechnologyEntity toEntity(Technology technology);

    @Named("toTechnologyList")
    @Mapping(target = "capabilityList", ignore = true)
    List<Technology> toTechenologyList(List<TechnologyEntity> technologies);
}
