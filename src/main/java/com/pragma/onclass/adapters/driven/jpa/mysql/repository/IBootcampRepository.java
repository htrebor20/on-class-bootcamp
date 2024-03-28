package com.pragma.onclass.adapters.driven.jpa.mysql.repository;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBootcampRepository extends JpaRepository<BootcampEntity, Long> {
    Page<BootcampEntity> findAll(Pageable pageable);

    @Query("SELECT b FROM BootcampEntity b LEFT JOIN b.capabilities c GROUP BY b.id ORDER BY COUNT(c.id) ASC")
    Page<BootcampEntity> findAllSortedByCapabilityCountAsc(Pageable pageable);

    @Query("SELECT b FROM BootcampEntity b LEFT JOIN b.capabilities c GROUP BY b.id ORDER BY COUNT(c.id) DESC")
    Page<BootcampEntity> findAllSortedByCapabilityCountDesc(Pageable pageable);
}
