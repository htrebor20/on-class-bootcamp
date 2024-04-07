package com.pragma.onclass.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AddCapabilityRequest {
    private final Long id;
    @NotBlank(message = "The name is required")
    @Size(max =50, message = "The maximum name size is 50 characters")
    private final String name;
    @Size(max =90, message = "The maximum description size is 90 characters")
    private final String description;
    @NotEmpty(message = "At least three technology ids are required")
    @Size(min = 3, max = 20, message = "The number of technology ids must be between 3 and 20")
    private final List<Long> technologyIds;
}
