package com.pragma.onclass.adapters.driving.http.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class VersionResponse {
    private final Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long idBootcamp;
    private final LocalDate startDate;
    private final LocalDate endingDate;
    private final int maximumQuota;
}
