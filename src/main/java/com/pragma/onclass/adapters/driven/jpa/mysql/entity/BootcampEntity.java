package com.pragma.onclass.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bootcamp")
public class BootcampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(unique = true, length = 50)
    private  String  name;
    @Column(length = 90)
    private  String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bootcamp_capability",
            joinColumns = @JoinColumn(name = "bootcamp_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id"))
    private List<CapabilityEntity> capabilities;

    @OneToMany(mappedBy = "bootcamp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VersionEntity> versions = new ArrayList<>();

    public void addVersion(VersionEntity version) {
        this.versions.add(version);
        version.setBootcamp(this);
    }
}
