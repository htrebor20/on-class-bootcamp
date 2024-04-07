package com.pragma.onclass.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "capability")
public class CapabilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(unique = true, length = 50)
    private  String  name;
    @Column(length = 90)
    private  String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "capability_technology",
            joinColumns = @JoinColumn(name = "capability_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<TechnologyEntity> technologies;
}


