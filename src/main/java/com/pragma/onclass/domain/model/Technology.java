package com.pragma.onclass.domain.model;

import java.util.List;

public class Technology {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Capability> capabilityList;

    public Technology(Long id, String name, String description, List<Capability> capabilityList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capabilityList = capabilityList;
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

    public List<Capability> getCapabilityList() {
        return capabilityList;
    }
}
