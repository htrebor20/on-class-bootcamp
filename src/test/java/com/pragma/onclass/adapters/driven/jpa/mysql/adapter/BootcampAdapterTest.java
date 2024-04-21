package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

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
}
