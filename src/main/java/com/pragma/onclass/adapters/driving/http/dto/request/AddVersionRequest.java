package com.pragma.onclass.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AddVersionRequest {
    private final Long id;
    @NotNull(message = "Bootcamp id cannot be null")
    private final Long idBootcamp;
    @FutureOrPresent(message = "Start date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;
    @Future(message = "Ending date must be today or in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endingDate;
    @Min(value = 1, message = "Maximum quota must be at least 1")
    private final int maximumQuota;
}
