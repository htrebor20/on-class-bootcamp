package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.Constants;
import com.pragma.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.model.Technology;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technology")
@RequiredArgsConstructor
public class TechnologyRestControllerAdapter {
    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;

    @PostMapping("")
    public ResponseEntity<Void> addTechnology(@Valid @RequestBody AddTechnologyRequest request) throws BadRequestException {
        technologyServicePort.saveTechnology(technologyRequestMapper.addRequestToTechnology(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<TechnologyResponse>> getAllTechnology(@RequestParam Integer page,
                                                                     @RequestParam Integer size,
                                                                     @RequestParam(required = false)
                                                                     Constants.Sort sort) {
        List<Technology> response = technologyServicePort.getAllTechnology(page, size, sort);
        return ResponseEntity.ok(technologyRequestMapper.toTechenologyResponseList(response));
    }
}
