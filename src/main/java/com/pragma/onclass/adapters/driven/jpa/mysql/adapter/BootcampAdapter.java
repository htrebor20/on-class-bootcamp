package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import com.pragma.onclass.utilities.Sorting;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        BootcampEntity entity = bootcampEntityMapper.toEntity(bootcamp);
        bootcampRepository.save(entity);
    }

    @Override
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy) {
        Pageable pagination = PageRequest.of(page, size);
        if (sortType != null && sortBy != null) {
            if (sortBy == ConstantsAdapters.SortBy.CAPABILITY_COUNT) {
                if (sortType == ConstantsAdapters.Sort.ASC) {
                    List<BootcampEntity> response = bootcampRepository.findAllSortedByCapabilityCountAsc(pagination).getContent();
                    return bootcampEntityMapper.toBootcampList(response);
                } else {
                    List<BootcampEntity> response = bootcampRepository.findAllSortedByCapabilityCountDesc(pagination).getContent();
                    return bootcampEntityMapper.toBootcampList(response);
                }
            } else {
                pagination = Sorting.sortByField(page, size, sortType, sortBy);
            }
        }
        List<BootcampEntity> response = bootcampRepository.findAll(pagination).getContent();
        return bootcampEntityMapper.toBootcampList(response);
    }

    @Override
    public Bootcamp findById(Long id) {
         Optional<BootcampEntity> bootcamp = bootcampRepository.findById(id);
         return bootcamp.map(bootcampEntityMapper::toModel).orElse(null);
    }
}
