package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.onclass.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionEntityMapper {
    @Mapping(target = "bootcamp", ignore = true)
    Version toModel(VersionEntity versionEntity);

    @Mapping(target = "bootcamp", ignore = true)
    VersionEntity toEntity(Version version);

    List<Version> toVersionList(List<VersionEntity> versionEntityList);

}
