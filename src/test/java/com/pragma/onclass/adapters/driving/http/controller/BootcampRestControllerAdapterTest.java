package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.model.Bootcamp;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BootcampRestControllerAdapterTest {

    @Mock
    private IBootcampServicePort bootcampServicePort;
    @Mock
    private IBootcampRequestMapper bootcampRequestMapper;

    @InjectMocks
    private BootcampRestControllerAdapter bootcampRestControllerAdapter;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bootcampRestControllerAdapter).build();
    }

    @Test
    void shouldAddBootcampSuccessfully() throws Exception {
        String body = "{\"name\":\"Pragma Backend\",\"description\":\"Backend Java Description\",\"capabilityIds\":[1,2,3]}";
        Bootcamp bootcamp = TestData.createBootcamp();
        AddBootcampRequest bootcampDTO = TestData.createBootcampDTO();
        given(bootcampRequestMapper.addRequestToBootcamp(bootcampDTO)).willReturn(bootcamp);

        bootcampRestControllerAdapter.addBootcamp(bootcampDTO);
        MockHttpServletRequestBuilder request = post("/bootcamp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        verify(bootcampServicePort).saveBootcamp(bootcamp);
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllBootcampWithoutSort() {
        int page = 0;
        int size = 3;
        List<Bootcamp> bootcampList = TestData.getBootcampsList();
        List<BootcampResponse> bootcampDTOList = TestData.getBootcampsDTOList();
        given(bootcampServicePort.getAllBootcamp(page, size, null,null)).willReturn(bootcampList);
        given(bootcampRequestMapper.toBootcampResponseList(bootcampList)).willReturn(bootcampDTOList);

        ResponseEntity<List<BootcampResponse>> response = bootcampRestControllerAdapter.getAllBootcamp(page, size, null, null);
        List<BootcampResponse> bodyResponse =  response.getBody();

        verify(bootcampServicePort).getAllBootcamp(page, size, null, null);
        verify(bootcampRequestMapper).toBootcampResponseList(bootcampList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Bootcamp Pragma I", bodyResponse.get(0).getName());
        assertEquals("Bootcamp Pragma II", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllBootcampSortByNameAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortby = ConstantsAdapters.SortBy.NAME;
        List<Bootcamp> bootcampList = TestData.getBootcampsList();
        List<BootcampResponse> bootcampDTOList = TestData.getBootcampDTOListSorted(sort);
        given(bootcampServicePort.getAllBootcamp(page, size, sort,sortby)).willReturn(bootcampList);
        given(bootcampRequestMapper.toBootcampResponseList(bootcampList)).willReturn(bootcampDTOList);

        ResponseEntity<List<BootcampResponse>> response = bootcampRestControllerAdapter.getAllBootcamp(page, size, sort, sortby);
        List<BootcampResponse> bodyResponse =  response.getBody();

        verify(bootcampServicePort).getAllBootcamp(page, size, sort, sortby);
        verify(bootcampRequestMapper).toBootcampResponseList(bootcampList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Bootcamp Pragma I", bodyResponse.get(0).getName());
        assertEquals("Bootcamp Pragma II", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllBootcampSortByNameDesc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        ConstantsAdapters.SortBy sortby = ConstantsAdapters.SortBy.NAME;
        List<Bootcamp> bootcampList = TestData.getBootcampsList();
        List<BootcampResponse> bootcampDTOList = TestData.getBootcampDTOListSorted(sort);
        given(bootcampServicePort.getAllBootcamp(page, size, sort,sortby)).willReturn(bootcampList);
        given(bootcampRequestMapper.toBootcampResponseList(bootcampList)).willReturn(bootcampDTOList);

        ResponseEntity<List<BootcampResponse>> response = bootcampRestControllerAdapter.getAllBootcamp(page, size, sort, sortby);
        List<BootcampResponse> bodyResponse =  response.getBody();

        verify(bootcampServicePort).getAllBootcamp(page, size, sort, sortby);
        verify(bootcampRequestMapper).toBootcampResponseList(bootcampList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Bootcamp Pragma III", bodyResponse.get(0).getName());
        assertEquals("Bootcamp Pragma II", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllBootcampSortByCapabilitiesCountAsc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.ASC;
        ConstantsAdapters.SortBy sortby = ConstantsAdapters.SortBy.CAPABILITY_COUNT;
        List<Bootcamp> bootcampList = TestData.getBootcampsList();
        List<BootcampResponse> bootcampDTOList = TestData.getBootcampDTOListSortedByTechnologyCount(sort);
        given(bootcampServicePort.getAllBootcamp(page, size, sort,sortby)).willReturn(bootcampList);
        given(bootcampRequestMapper.toBootcampResponseList(bootcampList)).willReturn(bootcampDTOList);

        ResponseEntity<List<BootcampResponse>> response = bootcampRestControllerAdapter.getAllBootcamp(page, size, sort, sortby);
        List<BootcampResponse> bodyResponse =  response.getBody();

        verify(bootcampServicePort).getAllBootcamp(page, size, sort, sortby);
        verify(bootcampRequestMapper).toBootcampResponseList(bootcampList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Bootcamp Pragma III", bodyResponse.get(0).getName());
        assertEquals("Bootcamp Pragma I", bodyResponse.get(1).getName());
    }

    @Test
    void shouldGetAllBootcampSortByCapabilitiesCountDesc() {
        int page = 0;
        int size = 3;
        ConstantsAdapters.Sort sort = ConstantsAdapters.Sort.DESC;
        ConstantsAdapters.SortBy sortby = ConstantsAdapters.SortBy.CAPABILITY_COUNT;
        List<Bootcamp> bootcampList = TestData.getBootcampsList();
        List<BootcampResponse> bootcampDTOList = TestData.getBootcampDTOListSortedByTechnologyCount(sort);
        given(bootcampServicePort.getAllBootcamp(page, size, sort,sortby)).willReturn(bootcampList);
        given(bootcampRequestMapper.toBootcampResponseList(bootcampList)).willReturn(bootcampDTOList);

        ResponseEntity<List<BootcampResponse>> response = bootcampRestControllerAdapter.getAllBootcamp(page, size, sort, sortby);
        List<BootcampResponse> bodyResponse =  response.getBody();

        verify(bootcampServicePort).getAllBootcamp(page, size, sort, sortby);
        verify(bootcampRequestMapper).toBootcampResponseList(bootcampList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(bodyResponse).isNotNull().hasSize(3);
        assertEquals("Bootcamp Pragma II", bodyResponse.get(0).getName());
        assertEquals("Bootcamp Pragma I", bodyResponse.get(1).getName());
    }
}
