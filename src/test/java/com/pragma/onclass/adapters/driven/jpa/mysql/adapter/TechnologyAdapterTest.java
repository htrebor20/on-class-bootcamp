package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TechnologyAdapterTest {
    @Mock
    private ITechnologyRepository technologyRepository;
    @Mock
    private ITechnologyEntityMapper technologyEntityMapper;

    @InjectMocks
    private TechnologyAdapter technologyAdapter;

    @Test
    void shouldGetTechnologyByNameSuccessfully() {
        TechnologyEntity technologyEntity = TestData.createTechnologyEntity();
        Technology technology = TestData.createTechnologyModel();
        given(technologyRepository.findByName("java")).willReturn(Optional.of(technologyEntity));
        given(technologyEntityMapper.toModel(technologyEntity)).willReturn(technology);

        Optional<Technology> result = technologyAdapter.getTechnology("java");

        verify(technologyRepository).findByName("java");
        verify(technologyEntityMapper).toModel(technologyEntity);

        assertThat(result).isPresent();
        result.ifPresent(tech -> assertThat(tech.getName()).isEqualTo("java"));
    }

    @Test
    void shouldGetAllTechnologySuccessfully() {
        Pageable pagination = PageRequest.of(0, 3);
        Page<TechnologyEntity> technologyEntity = TestData.getTechnologiesEntityPage();
        List<Technology> technologiesList = TestData.getTechnologiesList();
        given(technologyRepository.findAll(any(Pageable.class))).willReturn(technologyEntity);
        given(technologyEntityMapper.toTechenologyList(technologyEntity.getContent())).willReturn(technologiesList);

        List<Technology> result = technologyAdapter.getAllTechnology(pagination);

        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("Java");
        assertThat(result.get(1).getName()).isEqualTo("Python");
        assertThat(result.get(2).getName()).isEqualTo("JavaScript");
    }

    @Test
    void shouldSaveTechnologySuccessfully() {
        Technology technology = TestData.createTechnologyModel();
        TechnologyEntity technologyEntity = TestData.createTechnologyEntity();
        given(technologyEntityMapper.toEntity(technology)).willReturn(technologyEntity);

        technologyAdapter.saveTechnology(technology);

        verify(technologyEntityMapper).toEntity(technology);
        verify(technologyRepository).save(technologyEntity);
    }

    @Test
    void shouldGetAllTechnologiesByIdsSuccessfully() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<TechnologyEntity> technologyEntities = TestData.getTechnologiesEntityList();
        List<Technology> technologies = TestData.getTechnologiesList();
        given(technologyRepository.findAllById(ids)).willReturn(technologyEntities);
        given(technologyEntityMapper.toTechenologyList(technologyEntities)).willReturn(technologies);

        List<Technology> result = technologyAdapter.getAllTechnologiesByIds(ids);

        verify(technologyEntityMapper).toTechenologyList(technologyEntities);
        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("Java");
        assertThat(result.get(1).getName()).isEqualTo("Python");
        assertThat(result.get(2).getName()).isEqualTo("JavaScript");
    }
}
