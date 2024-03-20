package com.pragma.onclass.adapters.driven.jpa.mysql.repository;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICapabilityRepository extends JpaRepository<CapabilityEntity, Long> {
}
