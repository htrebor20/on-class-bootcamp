package com.pragma.onclass.adapters.driving.http.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class BootcampVersionResponse {
    private final Long id;
    private final LocalDate startDate;
    private final LocalDate endingDate;
    private final int maximumQuota;
}
