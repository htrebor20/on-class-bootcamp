package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
 import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import com.pragma.onclass.utilities.Sorting;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;

    @Override
    public void saveVersion(Version version) {
        VersionEntity entity = versionEntityMapper.toEntity(version);
        versionRepository.save(entity);
    }

    @Override
    public List <Version> findAll(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy) {
        Pageable pagination = PageRequest.of(page, size);
        if (sortBy != null) {
         pagination = Sorting.sortByField(page, size, sortType, sortBy);
        }
        List<VersionEntity> response = versionRepository.findAll(pagination).getContent();
        return versionEntityMapper.toVersionList(response);
    }
}
