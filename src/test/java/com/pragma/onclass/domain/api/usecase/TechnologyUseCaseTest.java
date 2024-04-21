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
    void shouldGetAllTechnologySuccessfully(){
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.NAME;
        List<Technology> technologiesList = TestData.getTechnologiesList();
        given( technologyPersistencePort.getAllTechnology(page, size, sort, sortBy)).willReturn(technologiesList);

        List<Technology> result = technologyUseCase.getAllTechnology(page, size, sort, sortBy);

        assertThat(result).isNotNull();
    }
    @Test
    void  shouldGetAllTechnologiesByIdsSuccessfully() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        given(technologyPersistencePort.getAllTechnologiesByIds(ids)).willReturn(TestData.getTechnologiesList());

        List<Technology> result = technologyUseCase.getAllTechnologiesByIds(ids);

        assertThat(result).isNotNull().isEqualTo(TestData.getTechnologiesList());
    }
}
