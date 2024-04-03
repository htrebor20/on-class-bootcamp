package com.pragma.onclass.domain.model;

import java.util.List;
import java.util.Objects;

public class Bootcamp {
    private final Long id;
    private final String name;
    private final String description;
    private List<Capability> capabilities;
    private List<Version> versions;

    public Bootcamp(Long id, String name, String description, List<Capability> capabilities, List<Version> versions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capabilities = capabilities;
        this.versions = versions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bootcamp bootcamp = (Bootcamp) o;
        return Objects.equals(id, bootcamp.id) && Objects.equals(name, bootcamp.name) && Objects.equals(description, bootcamp.description) && Objects.equals(capabilities, bootcamp.capabilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, capabilities);
    }
}
