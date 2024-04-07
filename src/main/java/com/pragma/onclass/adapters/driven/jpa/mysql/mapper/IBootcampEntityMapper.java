package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.domain.model.Bootcamp;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {IVersionEntityMapper.class}
)
public interface IBootcampEntityMapper {
    Bootcamp toModel(BootcampEntity bootcampEntity);

    BootcampEntity toEntity(Bootcamp bootcamp);

    @Mapping(target = "capabilities", qualifiedByName = "toCapabilityList")
    List<Bootcamp> toBootcampList(List<BootcampEntity> bootcampList);
}
