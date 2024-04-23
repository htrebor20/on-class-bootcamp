package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ICapabilityEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapabilityRepository;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CapabilityAdapterTest {
    @Mock
    private ICapabilityRepository capabilityRepository;
    @Mock
    private ICapabilityEntityMapper capabilityEntityMapper;

    @InjectMocks
    private CapabilityAdapter capabilityAdapter;

    @Test
    void shouldSaveCapabilitySuccessfully() {
        Capability capability = TestData.createCapabilityModel();
        CapabilityEntity capabilityEntity = TestData.createCapabilityEntity();
        given(capabilityEntityMapper.toEntity(capability)).willReturn(capabilityEntity);

        capabilityAdapter.saveCapability(capability);

        verify(capabilityEntityMapper).toEntity(capability);
        verify(capabilityRepository).save(capabilityEntity);
    }

    @Test
    void shouldGetAllCapabilitiesByIdsSuccessfully() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<CapabilityEntity> capabilityEntities = TestData.getCapabilitiesEntityList();
        List<Capability> capabilities = TestData.getCapabilityList();
        given(capabilityRepository.findAllById(ids)).willReturn(capabilityEntities);
        given(capabilityEntityMapper.toCapabilityList(capabilityEntities)).willReturn(capabilities);

        List<Capability> result = capabilityAdapter.getAllCapabilitiesByIds(ids);

        verify(capabilityEntityMapper).toCapabilityList(capabilityEntities);
        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("Backend Java");
        assertThat(result.get(1).getName()).isEqualTo("Frontend JavaScript");
        assertThat(result.get(2).getName()).isEqualTo("Mobile Development");
    }
}
