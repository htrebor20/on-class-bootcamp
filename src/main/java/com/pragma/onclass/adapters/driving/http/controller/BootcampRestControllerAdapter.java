package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.model.Bootcamp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
