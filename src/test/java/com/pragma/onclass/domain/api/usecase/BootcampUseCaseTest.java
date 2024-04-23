package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import com.pragma.onclass.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BootcampUseCaseTest {
    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;
    @Mock
    private ICapabilityServicePort capabilityServicePort;

    @InjectMocks
    private BootcampUseCase bootcampUseCase;
    private final Bootcamp bootcamp = TestData.createBootcamp();

    @Test
    void shouldSaveBootcampSuccessfully() {
        List<Capability> capabilities = TestData.getCapabilityList();
        given(capabilityServicePort.getAllCapabilitiesByIds(anyList())).willReturn(capabilities);
        bootcampUseCase.saveBootcamp(bootcamp);

        verify(bootcampPersistencePort).saveBootcamp(bootcamp);
    }

    @Test
    void shouldThrowExceptionIfNumberOfIdsIsOutOfRange() {
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp Pragma", "Bootcamp Pragma Backend", new ArrayList<>(), null);

        assertThrows(BadRequestValidationException.class,() -> bootcampUseCase.saveBootcamp(bootcamp));
    }
}
