package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driving.http.dto.request.AddCapabilityRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.CapabilityResponse;
import com.pragma.onclass.adapters.driving.http.mapper.ICapabilityRequestMapper;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.testdata.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CapabilityRestControllerAdapterTest {
    @Mock
    private ICapabilityServicePort capabilityServicePort;
    @Mock
    private ICapabilityRequestMapper capabilityRequestMapper;

    @InjectMocks
    private CapabilityRestControllerAdapter capabilityRestControllerAdapter;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(capabilityRestControllerAdapter).build();
    }

    @Test
    void shouldAddCapability() throws Exception {
        String body = "{\"name\":\"Backend Java\",\"description\":\"Backend Java Description\",\"technologyIds\":[1,2,3]}";
        Capability capability = TestData.createCapabilityModel();
        AddCapabilityRequest capabilityDTO = TestData.createCapabilityDTO();
        given(capabilityRequestMapper.addRequestToCapability(capabilityDTO)).willReturn(capability);

        capabilityRestControllerAdapter.addCapability(capabilityDTO);
        MockHttpServletRequestBuilder request = post("/capability")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        verify(capabilityServicePort).saveCapability(capability);
        mockMvc.perform(request).andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetAllCapabilitiesWithoutSort() {
        int page = 0;
        int size = 3;
        List<Capability> capabilityList = TestData.getCapabilityList();
        List<CapabilityResponse> capabilityDTOList = TestData.getCapabilityDTOList();
        given(capabilityServicePort.getAllCapability(page, size, null,null)).willReturn(capabilityList);
        given(capabilityRequestMapper.toCapabilityResponseList(capabilityList)).willReturn(capabilityDTOList);

        ResponseEntity<List<CapabilityResponse>> response = capabilityRestControllerAdapter.getAllCapability(page, size, null, null);
        List<CapabilityResponse> bodyResponse =  response.getBody();

        verify(capabilityServicePort).getAllCapability(page, size, null, null);
        verify(capabilityRequestMapper).toCapabilityResponseList(capabilityList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Backend Java", bodyResponse.get(0).getName());
        assertEquals("Frontend JavaScript", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllCapabilitiesSorBytNameDesc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        ConstantsAdapters.SortBy sortby = ConstantsAdapters.SortBy.NAME;
        List<Capability> capabilityList = TestData.getCapabilityList();
        List<CapabilityResponse> capabilityResponseList = TestData.getCapabilitiesDTOListSorted(sort);
        given(capabilityServicePort.getAllCapability(page, size, sort, sortby)).willReturn(capabilityList);
        given(capabilityRequestMapper.toCapabilityResponseList(capabilityList)).willReturn(capabilityResponseList);

        ResponseEntity<List<CapabilityResponse>> response = capabilityRestControllerAdapter.getAllCapability(page, size, sort, sortby);
        List<CapabilityResponse> bodyResponse = response.getBody();

        verify(capabilityServicePort).getAllCapability(page, size, sort, sortby);
        verify(capabilityRequestMapper).toCapabilityResponseList(capabilityList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Mobile Development", bodyResponse.get(0).getName());
        assertEquals("Frontend JavaScript", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllCapabilitiesSortByNameAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortby = ConstantsAdapters.SortBy.NAME;
        List<Capability> capabilityList = TestData.getCapabilityList();
        List<CapabilityResponse> capabilityResponseList = TestData.getCapabilitiesDTOListSorted(sort);
        given(capabilityServicePort.getAllCapability(page, size, sort, sortby)).willReturn(capabilityList);
        given(capabilityRequestMapper.toCapabilityResponseList(capabilityList)).willReturn(capabilityResponseList);

        ResponseEntity<List<CapabilityResponse>> response = capabilityRestControllerAdapter.getAllCapability(page, size, sort, sortby);
        List<CapabilityResponse> bodyResponse = response.getBody();

        verify(capabilityServicePort).getAllCapability(page, size, sort, sortby);
        verify(capabilityRequestMapper).toCapabilityResponseList(capabilityList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Backend Java", bodyResponse.get(0).getName());
        assertEquals("Frontend JavaScript", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllCapabilitiesSortByTechnologyCountAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortby = ConstantsAdapters.SortBy.TECHNOLOGY_COUNT;
        List<Capability> capabilityList = TestData.getCapabilityList();
        List<CapabilityResponse> capabilityResponseList = TestData.getCapabilitiesDTOListSortedByTechnologyCount(sort);
        given(capabilityServicePort.getAllCapability(page, size, sort, sortby)).willReturn(capabilityList);
        given(capabilityRequestMapper.toCapabilityResponseList(capabilityList)).willReturn(capabilityResponseList);

        ResponseEntity<List<CapabilityResponse>> response = capabilityRestControllerAdapter.getAllCapability(page, size, sort, sortby);
        List<CapabilityResponse> bodyResponse = response.getBody();
        verify(capabilityServicePort).getAllCapability(page, size, sort, sortby);
        verify(capabilityRequestMapper).toCapabilityResponseList(capabilityList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Mobile Development", bodyResponse.get(0).getName());
        assertEquals("Backend Java", bodyResponse.get(1).getName());
    }


}
