package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ICapabilityEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapabilityRepository;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class CapabilityAdapter implements ICapabilityPersistencePort {
    private final ICapabilityRepository capabilityRepository;
    private final ICapabilityEntityMapper capabilityEntityMapper;
    @Override
    public void saveCapability(Capability capability) {
        capabilityRepository.save(capabilityEntityMapper.toEntity(capability));
    }

    @Override
    public List<Capability> getAllCapability(Pageable pageable) {
        List<CapabilityEntity> response = capabilityRepository.findAll(pageable).getContent();
        return capabilityEntityMapper.toCapabilityList(response);
    }

    @Override
    public List<Capability> findAllSortedByTechnologyCountAsc(Pageable pageable) {
        List<CapabilityEntity> response = capabilityRepository.findAllSortedByTechnologyCountAsc(pageable).getContent();
        return capabilityEntityMapper.toCapabilityList(response);
    }

    @Override
    public List<Capability> findAllSortedByTechnologyCountDesc(Pageable pageable) {
        List<CapabilityEntity> response = capabilityRepository.findAllSortedByTechnologyCountDesc(pageable).getContent();
        return capabilityEntityMapper.toCapabilityList(response);
    }

    @Override
    public List<Capability> getAllCapabilitiesByIds(List<Long> ids) {
        List<CapabilityEntity> response = capabilityRepository.findAllById(ids);
        return capabilityEntityMapper.toCapabilityList(response);
    }
}
