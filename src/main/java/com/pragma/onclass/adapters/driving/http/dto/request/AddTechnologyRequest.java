package com.pragma.onclass.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddTechnologyRequest {
    @NotBlank(message = "The name is required")
    @Size(max =50, message = "The maximum name size is 50 characters")
    private final String  name;
    @Size(max =90, message = "The maximum description size is 90 characters")
    private final String description;
}
