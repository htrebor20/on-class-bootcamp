package com.pragma.onclass.adapters.driven.jpa.mysql.repository;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IVersionRepository extends JpaRepository<VersionEntity, Long> {
    Page<VersionEntity> findAll(Pageable pageable);

    @Query("SELECT v FROM VersionEntity v WHERE v.bootcamp.id = :bootcampId")
    Page<VersionEntity> findVersionsByBootcampId(@Param("bootcampId") Long bootcampId, Pageable pageable);
}
