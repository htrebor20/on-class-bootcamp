package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.adapters.ConstantsAdapters;
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
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void shouldGetAllBootcampWithoutSorting() {
        Integer page = 0;
        Integer size = 3;
        List<Bootcamp> bootcamp = TestData.getBootcampsList();
        given(bootcampPersistencePort.getAllBootcamp(any(Pageable.class))).willReturn(bootcamp);

        List<Bootcamp> bootcampList = bootcampUseCase.getAllBootcamp(page, size, null, null);

        assertEquals(bootcamp, bootcampList);
        verify(bootcampPersistencePort).getAllBootcamp(any(Pageable.class));
        assertThat(bootcampList).hasSize(3);
        assertThat(bootcampList.get(0).getName()).isEqualTo("Bootcamp Pragma I");
        assertThat(bootcampList.get(1).getName()).isEqualTo("Bootcamp Pragma II");
        assertThat(bootcampList.get(2).getName()).isEqualTo("Bootcamp Pragma III");
    }

    @Test
    void shouldGetAllBootcampSortedByCapabilitiesCountAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.CAPABILITY_COUNT;
        given(bootcampPersistencePort.findAllSortedByCapabilityCountAsc(any(Pageable.class))).willReturn(TestData.getBootcampListSortedByCapabilityCount(sort));

        List<Bootcamp> result = bootcampUseCase.getAllBootcamp(page, size, sort, sortBy);

        assertThat(result).isNotNull().hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Bootcamp Pragma II");
        assertThat(result.get(1).getName()).isEqualTo("Bootcamp Pragma I");
        assertThat(result.get(2).getName()).isEqualTo("Bootcamp Pragma III");
    }

    @Test
    void shouldGetAllBootcampSortedByCapabilitiesCountDesc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.CAPABILITY_COUNT;
        given(bootcampPersistencePort.findAllSortedByCapabilityCountDesc(any(Pageable.class))).willReturn(TestData.getBootcampListSortedByCapabilityCount(sort));

        List<Bootcamp> result = bootcampUseCase.getAllBootcamp(page, size, sort, sortBy);

        assertThat(result).isNotNull().hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Bootcamp Pragma III");
        assertThat(result.get(1).getName()).isEqualTo("Bootcamp Pragma I");
        assertThat(result.get(2).getName()).isEqualTo("Bootcamp Pragma II");
    }

    @Test
    void shouldGetAllBootcampSortedByNameAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.NAME;
        given(bootcampPersistencePort.getAllBootcamp(any(Pageable.class))).willReturn(TestData.getBootcampListSorted(sort));

        List<Bootcamp> result = bootcampUseCase.getAllBootcamp(page, size, sort, sortBy);

        assertThat(result).isNotNull();
        verify(bootcampPersistencePort).getAllBootcamp(any(Pageable.class));
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Bootcamp Pragma I");
        assertThat(result.get(1).getName()).isEqualTo("Bootcamp Pragma II");
        assertThat(result.get(2).getName()).isEqualTo("Bootcamp Pragma III");
    }

    @Test
    void shouldGetAllBootcampSortedByNameDesc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        ConstantsAdapters.SortBy sortBy = ConstantsAdapters.SortBy.NAME;
        given(bootcampPersistencePort.getAllBootcamp(any(Pageable.class))).willReturn(TestData.getBootcampListSorted(sort));

        List<Bootcamp> result = bootcampUseCase.getAllBootcamp(page, size, sort, sortBy);

        assertThat(result).isNotNull();
        verify(bootcampPersistencePort).getAllBootcamp(any(Pageable.class));
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Bootcamp Pragma III");
        assertThat(result.get(1).getName()).isEqualTo("Bootcamp Pragma II");
        assertThat(result.get(2).getName()).isEqualTo("Bootcamp Pragma I");
    }
}
