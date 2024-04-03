package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Version;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
public class VersionRestControllerAdapter {
    private final IVersionRequestMapper versionRequestMapper;
    private final IVersionServicePort versionServicePort;

    @PostMapping("")
    public ResponseEntity<Void> addVersion(@RequestBody AddVersionRequest request) {
        Version version = versionRequestMapper.addRequestToVersion(request);
        versionServicePort.saveVersion(version);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
