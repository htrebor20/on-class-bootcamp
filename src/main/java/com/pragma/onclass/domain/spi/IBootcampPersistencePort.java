package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Bootcamp;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamp(Pageable pageable);
    List<Bootcamp> findAllSortedByCapabilityCountAsc(Pageable pageable);
    List<Bootcamp> findAllSortedByCapabilityCountDesc(Pageable pageable);
    Bootcamp findById(Long id);
}
