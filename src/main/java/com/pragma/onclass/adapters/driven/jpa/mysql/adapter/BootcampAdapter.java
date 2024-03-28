package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    public List<Bootcamp> getAllBootcamp(Pageable pageable) {
        List<BootcampEntity> response = bootcampRepository.findAll(pageable).getContent();
        return bootcampEntityMapper.toBootcampList(response);
    }

    @Override
    public List<Bootcamp> findAllSortedByCapabilityCountAsc(Pageable pageable) {
        List<BootcampEntity> response = bootcampRepository.findAllSortedByCapabilityCountAsc(pageable).getContent();
        return bootcampEntityMapper.toBootcampList(response);
    }

    @Override
    public List<Bootcamp> findAllSortedByCapabilityCountDesc(Pageable pageable) {
        List<BootcampEntity> response = bootcampRepository.findAllSortedByCapabilityCountDesc(pageable).getContent();
        return bootcampEntityMapper.toBootcampList(response);
    }
}
