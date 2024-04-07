package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.VersionResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Version;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
public class VersionRestControllerAdapter {
    private final IVersionRequestMapper versionRequestMapper;
    private final IVersionServicePort versionServicePort;

    @PostMapping("")
    public ResponseEntity<Void> addVersion(@Valid @RequestBody AddVersionRequest request) {
        Version version = versionRequestMapper.addRequestToVersion(request);
        versionServicePort.saveVersion(version);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<VersionResponse>> getAllVersion(@RequestParam(defaultValue = ConstantsAdapters.DEFAULT_PAGE) Integer page,
                                                               @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_SIZE) Integer size,
                                                               @RequestParam(required = false) ConstantsAdapters.Sort sort,
                                                               @RequestParam(required = false) ConstantsAdapters.SortBy sortBy) {
        List<Version> response = versionServicePort.getAllVersion(page, size, sort, sortBy);
        return ResponseEntity.ok(versionRequestMapper.toVersionResponseList(response));
    }

    @GetMapping("/bootcamp/{id}")
    public ResponseEntity<List<VersionResponse>> getVersionByBootcampId(@PathVariable Long id,
                                                                        @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_PAGE) Integer page,
                                                                        @RequestParam(defaultValue = ConstantsAdapters.DEFAULT_SIZE) Integer size,
                                                                        @RequestParam(required = false) ConstantsAdapters.Sort sort,
                                                                        @RequestParam(required = false) ConstantsAdapters.SortBy sortBy) {
        List<Version> response = versionServicePort.findVersionsByBootcampId(id, page, size, sort, sortBy);
        return ResponseEntity.ok(versionRequestMapper.toVersionResponseList(response));
    }
}
