package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface IBootcampEntityMapper {
    IBootcampEntityMapper bootcampEntityMapper = Mappers.getMapper(IBootcampEntityMapper.class);
    Bootcamp toModel(BootcampEntity bootcampEntity);
    BootcampEntity toEntity(Bootcamp bootcamp);
}
