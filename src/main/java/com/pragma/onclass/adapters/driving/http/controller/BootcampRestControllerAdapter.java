package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.model.Bootcamp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootcampRestControllerAdapter {
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampServicePort bootcampServicePort;

    @PostMapping("")
    public ResponseEntity<Void> addBootcamp(@RequestBody AddBootcampRequest request) {
        Bootcamp bootcamp = bootcampRequestMapper.addRequestToBootcamp(request);
        bootcampServicePort.saveBootcamp(bootcamp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<BootcampResponse>> getAllBootcamp(@RequestParam(defaultValue = ConstantsAdapters.DEFAULT_PAGE) Integer page,
                                                                 @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_SIZE) Integer size,
                                                                 @RequestParam(required = false) ConstantsAdapters.Sort sort,
                                                                 @RequestParam(required = false) ConstantsAdapters.SortBy sortBy) {
        List<Bootcamp> response = bootcampServicePort.getAllBootcamp(page, size, sort, sortBy);
        return ResponseEntity.ok(bootcampRequestMapper.toBootcampResponseList(response));
    }
}
