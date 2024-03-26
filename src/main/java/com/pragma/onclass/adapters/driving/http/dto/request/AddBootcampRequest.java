package com.pragma.onclass.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AddBootcampRequest {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Long> capabilityIds;
}
