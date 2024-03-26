package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        BootcampEntity entity = bootcampEntityMapper.toEntity(bootcamp);
        bootcampRepository.save(entity);
    }
}
