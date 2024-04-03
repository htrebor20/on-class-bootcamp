package com.pragma.onclass.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public class Version {
    private final Long id;
    private final Long idBootcamp;
    private final LocalDate startDate;
    private final LocalDate endingDate;
    private final int maximumQuota;

    public Version(Long id, Long idBootcamp, LocalDate startDate, LocalDate endingDate, int maximumQuota) {
        this.id = id;
        this.idBootcamp = idBootcamp;
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.maximumQuota = maximumQuota;
    }

    public Long getId() {
        return id;
    }

    public Long getIdBootcamp() {
        return idBootcamp;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public int getMaximumQuota() {
        return maximumQuota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return maximumQuota == version.maximumQuota && Objects.equals(id, version.id) && Objects.equals(idBootcamp, version.idBootcamp) && Objects.equals(startDate, version.startDate) && Objects.equals(endingDate, version.endingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBootcamp, startDate, endingDate, maximumQuota);
    }
}
