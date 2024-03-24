package com.pragma.onclass.adapters.driven.jpa.mysql.repository;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICapabilityRepository extends JpaRepository<CapabilityEntity, Long> {
    Page<CapabilityEntity> findAll(Pageable pageable);

    @Query("SELECT c FROM CapabilityEntity c LEFT JOIN c.technologies t GROUP BY c.id ORDER BY COUNT(t.id) ASC")
    Page<CapabilityEntity> findAllSortedByTechnologyCountAsc(Pageable pageable);

    @Query("SELECT c FROM CapabilityEntity c LEFT JOIN c.technologies t GROUP BY c.id ORDER BY COUNT(t.id) DESC")
    Page<CapabilityEntity> findAllSortedByTechnologyCountDesc(Pageable pageable);
}
