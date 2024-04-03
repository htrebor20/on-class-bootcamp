package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.model.Technology;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TechnologyRestControllerAdapterTest {
    @Mock
    private ITechnologyServicePort technologyServicePort;
    @Mock
    private ITechnologyRequestMapper technologyRequestMapper;

    @InjectMocks
    private TechnologyRestControllerAdapter technologyRestControllerAdapter;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(technologyRestControllerAdapter).build();
    }

    @Test
    void shouldAddTechnology() throws Exception {
        String body = "{\"name\":\"Java\",\"description\":\"Language Java\"}";
        Technology technology = TestData.createTechnologyModel();
        AddTechnologyRequest technologyDTO = TestData.createTechnologyDTO();
        given(technologyRequestMapper.addRequestToTechnology(technologyDTO)).willReturn(technology);

        technologyRestControllerAdapter.addTechnology(technologyDTO);
        MockHttpServletRequestBuilder request = post("/technology")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        verify(technologyServicePort).saveTechnology(technology);
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    void shouldHandleSizeNameValidationError() throws Exception {
        String body = "{\"name\":\"Java-----------------------------------------------------------------------------------------------\",\"description\":\"Language Java\"}";

        MockHttpServletRequestBuilder request = post("/technology")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void shouldHandleSizeDescriptionValidationError() throws Exception {
        String body = "{\"name\":\"Java\",\"description\":\"Language Java---------------------------------------------------------------------------------------------------------------------------------------------\"}";

        MockHttpServletRequestBuilder request = post("/technology")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetAllTechnologiesWithoutSort() {
        int page = 0;
        int size = 5;
        List<Technology> technologiesList = TestData.getTechnologiesList();
        List<TechnologyResponse> technologiesDTOList = TestData.getTechnologiesDTOList();
        given(technologyServicePort.getAllTechnology(page, size, null)).willReturn(technologiesList);
        given(technologyRequestMapper.toTechenologyResponseList(technologiesList)).willReturn(technologiesDTOList);

        ResponseEntity<List<TechnologyResponse>> response = technologyRestControllerAdapter.getAllTechnology(page, size, null);
        List<TechnologyResponse> bodyResponse = response.getBody();

        verify(technologyServicePort).getAllTechnology(page, size, null);
        verify(technologyRequestMapper).toTechenologyResponseList(technologiesList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(5);
        assertEquals("Java", bodyResponse.get(0).getName());
        assertEquals("Python", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllTechnologiesSortDesc() {
        int page = 0;
        int size = 5;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        List<Technology> technologiesList = TestData.getTechnologiesList();
        List<TechnologyResponse> technologiesDTOList = TestData.getTechnologiesDTOListSorted(sort);
        given(technologyServicePort.getAllTechnology(page, size, sort)).willReturn(technologiesList);
        given(technologyRequestMapper.toTechenologyResponseList(technologiesList)).willReturn(technologiesDTOList);

        ResponseEntity<List<TechnologyResponse>> response = technologyRestControllerAdapter.getAllTechnology(page, size, sort);
        List<TechnologyResponse> bodyResponse = response.getBody();

        verify(technologyServicePort).getAllTechnology(page, size, sort);
        verify(technologyRequestMapper).toTechenologyResponseList(technologiesList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(5);
        assertEquals("Ruby", bodyResponse.get(0).getName());
        assertEquals("Python", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllTechnologiesSortAsc() {
        int page = 0;
        int size = 5;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        List<Technology> technologiesList = TestData.getTechnologiesList();
        List<TechnologyResponse> technologiesDTOList = TestData.getTechnologiesDTOListSorted(sort);
        given(technologyServicePort.getAllTechnology(page, size, sort)).willReturn(technologiesList);
        given(technologyRequestMapper.toTechenologyResponseList(technologiesList)).willReturn(technologiesDTOList);

        ResponseEntity<List<TechnologyResponse>> response = technologyRestControllerAdapter.getAllTechnology(page, size, sort);
        List<TechnologyResponse> bodyResponse = response.getBody();

        verify(technologyServicePort).getAllTechnology(page, size, sort);
        verify(technologyRequestMapper).toTechenologyResponseList(technologiesList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(5);
        assertEquals("C++", bodyResponse.get(0).getName());
        assertEquals("Java", bodyResponse.get(1).getName());
    }
}
