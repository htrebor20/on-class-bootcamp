package com.pragma.onclass.adapters.driving.http.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BootcampResponse {
        private final Long id;
        private final String  name;
        private final String description;
        private final List<BootcampCapabilityResponse> capabilities;
}
