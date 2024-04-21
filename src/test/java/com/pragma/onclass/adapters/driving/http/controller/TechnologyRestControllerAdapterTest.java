package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
}
