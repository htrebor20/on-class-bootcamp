package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driving.http.dto.request.AddCapabilityRequest;
import com.pragma.onclass.adapters.driving.http.mapper.ICapabilityRequestMapper;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capability")
@RequiredArgsConstructor
public class CapabilityRestControllerAdapter {
    private final ICapabilityServicePort capabilityServicePort;
    private final ICapabilityRequestMapper capabilityRequestMapper;

    @PostMapping("")
    public ResponseEntity<Void> addTechnology(@RequestBody AddCapabilityRequest request) {
        capabilityServicePort.saveCapability(capabilityRequestMapper.addRequestToCapability(request), request.getTechnologyIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
