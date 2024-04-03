package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import com.pragma.onclass.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {
    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    @InjectMocks
    private TechnologyUseCase technologyUseCase;
    private final Technology technology = TestData.createTechnologyModel();

    @Test
    void shouldSaveTechnologySuccessfully() {
        technologyUseCase.saveTechnology(technology);

        verify(technologyPersistencePort).saveTechnology(technology);
    }

    @Test
    void shouldThrowAnErrorWhenTechnologyAlreadyExists() {
        given(technologyPersistencePort.getTechnology(technology.getName())).willReturn(Optional.of(technology));

        assertThrows(BadRequestValidationException.class, () -> {
            technologyUseCase.saveTechnology(technology);
        });
    }

    @Test
    void shouldGetAllTechnologyWithoutSortSuccessfully() {
        int page = 0;
        int size = 5;
        ConstantsAdapters.Sort sort = null;
        Pageable pageable = PageRequest.of(page, size);
        given(technologyPersistencePort.getAllTechnology(pageable)).willReturn(TestData.getTechnologiesList());

        List<Technology> result = technologyUseCase.getAllTechnology(0, 5, null);

        assertThat(result).hasSize(5);
        assertThat(result.get(0).getName()).isEqualTo("Java");
        assertThat(result.get(1).getName()).isEqualTo("Python");
        assertThat(result.get(2).getName()).isEqualTo("JavaScript");
    }

    @Test
    void shouldGetAllTechnologyAscSortSuccessfully() {
        int page = 0;
        int size = 5;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        given(technologyPersistencePort.getAllTechnology(pageable)).willReturn(TestData.getTechnologiesListSorted(sort));

        List<Technology> result = technologyUseCase.getAllTechnology(page, size, sort);

        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("C++");
        assertThat(result.get(1).getName()).isEqualTo("Java");
        assertThat(result.get(2).getName()).isEqualTo("JavaScript");
    }

    @Test
    void shouldGetAllTechnologyDescSortSuccessfully() {
        int page = 0;
        int size = 5;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").descending());
        given(technologyPersistencePort.getAllTechnology(pageable)).willReturn(TestData.getTechnologiesListSorted(sort));

        List<Technology> result = technologyUseCase.getAllTechnology(page, size, sort);

        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("Ruby");
        assertThat(result.get(1).getName()).isEqualTo("Python");
        assertThat(result.get(2).getName()).isEqualTo("JavaScript");
    }

    @Test
    void  shouldGetAllTechnologiesByIdsSuccessfully() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        given(technologyPersistencePort.getAllTechnologiesByIds(ids)).willReturn(TestData.getTechnologiesList());

        List<Technology> result = technologyUseCase.getAllTechnologiesByIds(ids);

        assertThat(result).isNotNull().isEqualTo(TestData.getTechnologiesList());
    }
}
