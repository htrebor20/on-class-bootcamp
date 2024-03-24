package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

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
    public List<Technology> getAllTechnology(Pageable pageable) {
       List<TechnologyEntity> response = technologyRepository.findAll(pageable).getContent();
        return technologyEntityMapper.toTechenologyResponseList(response);
    }

    @Override
    public void saveTechnology(Technology technology) {
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }

    @Override
    public List<Technology> getAllTechnologiesByIds(List<Long> ids) {
        List<TechnologyEntity> response = technologyRepository.findAllById(ids);
        return technologyEntityMapper.toTechenologyResponseList(response);
    }
}
