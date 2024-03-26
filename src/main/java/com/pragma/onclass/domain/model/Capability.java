package com.pragma.onclass.domain.model;

import java.util.List;

public class Capability {
    private final Long id;
    private final String name;
    private final String description;
    private List<Technology> technologies;
    private List<Bootcamp> bootcampList;

    public Capability(Long id, String name, String description, List<Technology> technologies, List<Bootcamp> bootcampList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologies = technologies;
        this.bootcampList = bootcampList;
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

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
    public List<Bootcamp> getBootcampList() {
        return bootcampList;
    }
}

