package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BootcampAdapterTest {
    @Mock
    private IBootcampRepository bootcampRepository;
    @Mock
    private IBootcampEntityMapper bootcampEntityMapper;

    @InjectMocks
    private BootcampAdapter bootcampAdapter;

    @Test
    void shouldSaveBootcampSuccessfully() {
        Bootcamp bootcamp = TestData.createBootcamp();
        BootcampEntity bootcampEntity = TestData.createBootcampEntity();
        given(bootcampEntityMapper.toEntity(bootcamp)).willReturn(bootcampEntity);
        bootcampAdapter.saveBootcamp(bootcamp);

        verify(bootcampEntityMapper).toEntity(bootcamp);
        verify(bootcampRepository).save(bootcampEntity);
    }

    @Test
    void shouldGetAllBootcampSuccessfully() {
        Pageable pagination = PageRequest.of(0, 3);
        Page<BootcampEntity> bootcampEntities = TestData.getBootcampEntityPage();
        List<Bootcamp> bootcampList = TestData.getBootcampsList();
        given(bootcampRepository.findAll(any(Pageable.class))).willReturn(bootcampEntities);
        given(bootcampEntityMapper.toBootcampList(bootcampEntities.getContent())).willReturn(bootcampList);

        List<Bootcamp> result = bootcampAdapter.getAllBootcamp(pagination);

        verify(bootcampEntityMapper).toBootcampList(bootcampEntities.getContent());
        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("Bootcamp Pragma I");
        assertThat(result.get(1).getName()).isEqualTo("Bootcamp Pragma II");
        assertThat(result.get(2).getName()).isEqualTo("Bootcamp Pragma III");
    }

    @Test
    void shouldFindAllSortedByCapabilityCountAscSuccessfully() {
        Pageable pagination = PageRequest.of(0, 3);
        Page<BootcampEntity> bootcampEntities = TestData.getBootcampEntityPage();
        List<Bootcamp> bootcampList = TestData.getBootcampListSortedByCapabilityCount(ConstantsAdapters.Sort.ASC);
        given(bootcampRepository.findAll(any(Pageable.class))).willReturn(bootcampEntities);
        given(bootcampEntityMapper.toBootcampList(bootcampEntities.getContent())).willReturn(bootcampList);

        List<Bootcamp> result = bootcampAdapter.getAllBootcamp(pagination);

        verify(bootcampEntityMapper).toBootcampList(bootcampEntities.getContent());
        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("Bootcamp Pragma II");
        assertThat(result.get(1).getName()).isEqualTo("Bootcamp Pragma I");
        assertThat(result.get(2).getName()).isEqualTo("Bootcamp Pragma III");
    }

    @Test
    void shouldFindAllSortedByCapabilityCountDescSuccessfully() {
        Pageable pagination = PageRequest.of(0, 3);
        Page<BootcampEntity> bootcampEntities = TestData.getBootcampEntityPage();
        List<Bootcamp> bootcampList = TestData.getBootcampListSortedByCapabilityCount(ConstantsAdapters.Sort.DESC);
        given(bootcampRepository.findAll(any(Pageable.class))).willReturn(bootcampEntities);
        given(bootcampEntityMapper.toBootcampList(bootcampEntities.getContent())).willReturn(bootcampList);

        List<Bootcamp> result = bootcampAdapter.getAllBootcamp(pagination);

        verify(bootcampEntityMapper).toBootcampList(bootcampEntities.getContent());
        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("Bootcamp Pragma III");
        assertThat(result.get(1).getName()).isEqualTo("Bootcamp Pragma I");
        assertThat(result.get(2).getName()).isEqualTo("Bootcamp Pragma II");
    }
}
