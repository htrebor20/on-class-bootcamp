package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    @Override
    public Optional<Technology> getTechnology(String name) {
        return technologyRepository.findByName(name).map(technology -> technologyEntityMapper.toModel(technology)) ;
    }

    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size) {
        return null;
    }

    @Override
    public Technology updateTechnology(Technology technology) {
        return null;
    }

    @Override
    public void saveTechnology(Technology technology) {
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }

    @Override
    public void deleteTechnology(Long id) {

    }
}
