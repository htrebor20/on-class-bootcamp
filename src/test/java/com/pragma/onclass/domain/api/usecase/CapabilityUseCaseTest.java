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
