package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ICapabilityEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapabilityRepository;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import com.pragma.onclass.utilities.Sorting;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public List<Capability> getAllCapability(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy) {
        Pageable pagination = PageRequest.of(page, size);
        if (sortType != null && sortBy != null) {
            if (sortBy == ConstantsAdapters.SortBy.TECHNOLOGY_COUNT) {
                if (sortType == ConstantsAdapters.Sort.ASC) {
                    List<CapabilityEntity> response = capabilityRepository.findAllSortedByTechnologyCountAsc(pagination).getContent();
                    return capabilityEntityMapper.toCapabilityList(response);
                } else {
                    List<CapabilityEntity> response = capabilityRepository.findAllSortedByTechnologyCountDesc(pagination).getContent();
                    return capabilityEntityMapper.toCapabilityList(response);
                }
            } else {
                pagination = Sorting.sortByField(page, size, sortType, sortBy);
            }
        }
        List<CapabilityEntity> response = capabilityRepository.findAll(pagination).getContent();
        return capabilityEntityMapper.toCapabilityList(response);
    }

    @Override
    public List<Capability> getAllCapabilitiesByIds(List<Long> ids) {
        List<CapabilityEntity> response = capabilityRepository.findAllById(ids);
        return capabilityEntityMapper.toCapabilityList(response);
    }
}
