package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
 import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;

    @Override
    public void saveVersion(Version version) {
        VersionEntity entity = versionEntityMapper.toEntity(version);
        versionRepository.save(entity);
    }
}
