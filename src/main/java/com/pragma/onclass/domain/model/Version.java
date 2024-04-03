package com.pragma.onclass.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public class Version {
    private final Long id;
    private final LocalDate startDate;
    private final LocalDate endingDate;
    private final int maximumQuota;
    private Bootcamp bootcamp;

    public Version(Long id, LocalDate startDate, LocalDate endingDate, int maximumQuota, Bootcamp bootcamp) {
        this.id = id;
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.maximumQuota = maximumQuota;
        this.bootcamp = bootcamp;
    }

    public Long getId() {
        return id;
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

    public Bootcamp getBootcamp() {
        return bootcamp;
    }

    public void setBootcamp(Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return maximumQuota == version.maximumQuota && Objects.equals(id, version.id) && Objects.equals(startDate, version.startDate) && Objects.equals(endingDate, version.endingDate) && Objects.equals(bootcamp, version.bootcamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endingDate, maximumQuota, bootcamp);
    }
}
