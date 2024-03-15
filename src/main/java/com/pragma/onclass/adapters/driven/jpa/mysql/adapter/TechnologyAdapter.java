package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    @Override
    public Technology getTechnology(String name) {
        return null;
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
        if (technologyRepository.findByName(technology.getName()).isPresent()) {
            throw new TechnologyAlreadyExistsException();
        }

        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }

    @Override
    public void deleteTechnology(Long id) {

    }
}
