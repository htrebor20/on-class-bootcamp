package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ICapabilityPersistencePort;
import com.pragma.onclass.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CapabilityUseCaseTest {
    @Mock
    private ICapabilityPersistencePort capabilityPersistencePort;
    @Mock
    private ITechnologyServicePort technologyServicePort;
    @InjectMocks
    private CapabilityUseCase capabilityUseCase;
    private final Capability capability = TestData.createCapabilityModel();
    private final Capability capabilityWithRepeatTech = TestData.createCapabilityModelWithRepeatTech();

    @Test
    void shouldSaveCapabilitySuccessfully() {
        List<Technology> mockedTechnologies = TestData.getTechnologiesList();
        given(technologyServicePort.getAllTechnologiesByIds(anyList())).willReturn(mockedTechnologies);
        capabilityUseCase.saveCapability(capability);
        verify(capabilityPersistencePort).saveCapability(capability);
    }

    @Test
    void shouldThrowExceptionIfIdsContainDuplicates() {
        assertThrows(BadRequestValidationException.class, () -> capabilityUseCase.saveCapability(capabilityWithRepeatTech));
    }

    @Test
    void shouldThrowExceptionIfNumberOfIdsIsOutOfRange() {
        List<Technology> technologies = TestData.getTechnologiesList(2);
        Capability capability = new Capability(1L, "Java", "Java language", technologies);

        assertThrows(BadRequestValidationException.class, () -> capabilityUseCase.saveCapability(capability));
    }

    @Test
    void shouldGetAllCapabilityWithoutSorting() {
        Integer page = 0;
        Integer size = 3;
        List<Capability> capabilities = TestData.getCapabilityList();

        given(capabilityPersistencePort.getAllCapability(any(Pageable.class))).willReturn(capabilities);

        List<Capability> capabilitiesList = capabilityUseCase.getAllCapability(page, size, null, null);

        assertEquals(capabilities, capabilitiesList);
        verify(capabilityPersistencePort).getAllCapability(any(Pageable.class));
        assertThat(capabilitiesList).hasSize(3);
        assertThat(capabilitiesList.get(0).getName()).isEqualTo("Backend Java");
        assertThat(capabilitiesList.get(1).getName()).isEqualTo("Frontend JavaScript");
        assertThat(capabilitiesList.get(2).getName()).isEqualTo("Mobile Development");
    }

    @Test
    void shouldGetAllCapabilitySortedByTechnologyCountAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.TECHNOLOGY_COUNT;
        Pageable pageable = PageRequest.of(page, size);

        given(capabilityPersistencePort.findAllSortedByTechnologyCountAsc(pageable)).willReturn(TestData.getCapabilitiesListSortedByTechnologyCount(sort));

        List<Capability> result = capabilityUseCase.getAllCapability(page, size, sort, sortBy);

        assertThat(result).isNotNull().hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Frontend JavaScript");
        assertThat(result.get(1).getName()).isEqualTo("Backend Java");
        assertThat(result.get(2).getName()).isEqualTo("Mobile Development");
    }

    @Test
    void shouldGetAllCapabilitySortedByTechnologyCountDesc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.TECHNOLOGY_COUNT;
        Pageable pageable = PageRequest.of(page, size);

        given(capabilityPersistencePort.findAllSortedByTechnologyCountDesc(pageable)).willReturn(TestData.getCapabilitiesListSortedByTechnologyCount(sort));

        List<Capability> result = capabilityUseCase.getAllCapability(page, size, sort, sortBy);

        assertThat(result).isNotNull().hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Mobile Development");
        assertThat(result.get(1).getName()).isEqualTo("Backend Java");
        assertThat(result.get(2).getName()).isEqualTo("Frontend JavaScript");
    }

    @Test
    void shouldGetAllCapabilitySortedByNameAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.NAME;

        given(capabilityPersistencePort.getAllCapability(any(Pageable.class))).willReturn(TestData.getCapabilitiesListSorted(sort));

        List<Capability> result = capabilityUseCase.getAllCapability(page, size, sort, sortBy);

        assertThat(result).isNotNull();
        verify(capabilityPersistencePort).getAllCapability(any(Pageable.class));
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Backend Java");
        assertThat(result.get(1).getName()).isEqualTo("Frontend JavaScript");
        assertThat(result.get(2).getName()).isEqualTo("Mobile Development");
    }

    @Test
    void shouldGetAllCapabilitySortedByNameDesc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.NAME;

        given(capabilityPersistencePort.getAllCapability(any(Pageable.class))).willReturn(TestData.getCapabilitiesListSorted(sort));

        List<Capability> result = capabilityUseCase.getAllCapability(page, size, sort, sortBy);

        assertThat(result).isNotNull();
        verify(capabilityPersistencePort).getAllCapability(any(Pageable.class));
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Mobile Development");
        assertThat(result.get(1).getName()).isEqualTo("Frontend JavaScript");
        assertThat(result.get(2).getName()).isEqualTo("Backend Java");
    }

    @Test
    void shouldGetAllCapabilitiesByIdsSuccessfully() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<Capability> capabilities = TestData.getCapabilityList();
        given(capabilityPersistencePort.getAllCapabilitiesByIds(ids)).willReturn(capabilities);

        List<Capability> result = capabilityUseCase.getAllCapabilitiesByIds(ids);

        assertThat(result).isNotNull().isEqualTo(TestData.getCapabilityList());
        assertThat(capabilities.get(0).getName()).isEqualTo("Backend Java");
        assertThat(capabilities.get(1).getName()).isEqualTo("Frontend JavaScript");
        assertThat(capabilities.get(2).getName()).isEqualTo("Mobile Development");
    }
}
