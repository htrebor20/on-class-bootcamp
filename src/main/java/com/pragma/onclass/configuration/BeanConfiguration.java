package com.pragma.onclass.configuration;

import com.pragma.onclass.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.pragma.onclass.adapters.driven.jpa.mysql.adapter.CapabilityAdapter;
import com.pragma.onclass.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ICapabilityEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapabilityRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.api.usecase.BootcampUseCase;
import com.pragma.onclass.domain.api.usecase.CapabilityUseCase;
import com.pragma.onclass.domain.api.usecase.TechnologyUseCase;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    private final ICapabilityRepository capabilityRepository;
    private final ICapabilityEntityMapper capabilityEntityMapper;
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }

    @Bean
    public ICapabilityPersistencePort capabilityPersistencePort() {
        return new CapabilityAdapter(capabilityRepository, capabilityEntityMapper);
    }

    @Bean
    public ICapabilityServicePort capabilityServicePort() {
        return new CapabilityUseCase(capabilityPersistencePort(), technologyServicePort());
    }

    @Bean
    public IBootcampPersistencePort bootcampPersistencePort() {
        return new BootcampAdapter(bootcampRepository, bootcampEntityMapper);
    }

    @Bean
    public IBootcampServicePort bootcampServicePort() {
        return new BootcampUseCase(bootcampPersistencePort(), capabilityServicePort());
    }
}
