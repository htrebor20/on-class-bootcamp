package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.onclass.domain.model.Capability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapabilityEntityMapper {

    Capability toModel(CapabilityEntity capabilityEntity);
    CapabilityEntity toEntity(Capability capability);

    @Named("toCapabilityList")
    @Mapping(target = "bootcampList", ignore = true)
    @Mapping(target = "technologies", qualifiedByName = "toTechnologyList")
    List<Capability> toCapabilityList(List<CapabilityEntity> capabilities);

}


