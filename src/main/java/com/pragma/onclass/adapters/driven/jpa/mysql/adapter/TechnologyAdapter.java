package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import com.pragma.onclass.utilities.Sorting;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Override
    public Optional<Technology> getTechnology(String name) {
        Optional<TechnologyEntity> technologyEntity = technologyRepository.findByName(name);
        return technologyEntity.map(technologyEntityMapper::toModel);
    }

    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy) {
        Pageable pagination = PageRequest.of(page, size);

        if (sortBy == ConstantsAdapters.SortBy.NAME) {
            pagination = Sorting.sortByField(page, size, sortType, sortBy);
        }
        List<TechnologyEntity> response = technologyRepository.findAll(pagination).getContent();
        return technologyEntityMapper.toTechenologyList(response);
    }

    @Override
    public void saveTechnology(Technology technology) {
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }

    @Override
    public List<Technology> getAllTechnologiesByIds(List<Long> ids) {
        List<TechnologyEntity> response = technologyRepository.findAllById(ids);
        return technologyEntityMapper.toTechenologyList(response);
    }
}
