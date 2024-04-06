package com.pragma.onclass.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AddVersionRequest {
    @NotNull(message = "Bootcamp id cannot be null")
    private final Long id;
    private final Long idBootcamp;
    private final LocalDate startDate;
    private final LocalDate endingDate;
    private final int maximumQuota;
}
