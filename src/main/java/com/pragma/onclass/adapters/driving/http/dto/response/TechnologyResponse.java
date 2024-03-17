package com.pragma.onclass.adapters.driving.http.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TechnologyResponse {
    private final Long id;
    private final String  name;
    private final String description;
}
