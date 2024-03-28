package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface IBootcampEntityMapper {
    Bootcamp toModel(BootcampEntity bootcampEntity);
    BootcampEntity toEntity(Bootcamp bootcamp);

    @Mapping(target = "capabilities", qualifiedByName = "toCapabilityList")
    List<Bootcamp> toBootcampList(List<BootcampEntity> bootcampList);
}
