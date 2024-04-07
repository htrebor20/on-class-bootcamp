package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driving.http.dto.request.AddCapabilityRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.CapabilityResponse;
import com.pragma.onclass.adapters.driving.http.mapper.ICapabilityRequestMapper;
import com.pragma.onclass.domain.api.ICapabilityServicePort;
import com.pragma.onclass.domain.model.Capability;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capability")
@RequiredArgsConstructor
public class CapabilityRestControllerAdapter {
    private final ICapabilityServicePort capabilityServicePort;
    private final ICapabilityRequestMapper capabilityRequestMapper;

    @PostMapping("")
    public ResponseEntity<Void> addCapability(@RequestBody @Valid AddCapabilityRequest request) throws BadRequestException {
        Capability capability = capabilityRequestMapper.addRequestToCapability(request);
        capabilityServicePort.saveCapability(capability);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CapabilityResponse>> getAllCapability(@RequestParam(defaultValue = ConstantsAdapters.DEFAULT_PAGE) Integer page,
                                                                     @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_SIZE) Integer size,
                                                                     @RequestParam(required = false) ConstantsAdapters.Sort sort,
                                                                     @RequestParam(required = false) ConstantsAdapters.SortBy sortBy) {
        List<Capability> response = capabilityServicePort.getAllCapability(page, size, sort, sortBy);
        return ResponseEntity.ok(capabilityRequestMapper.toCapabilityResponseList(response));
    }
}
