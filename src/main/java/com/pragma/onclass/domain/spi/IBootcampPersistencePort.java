package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Bootcamp;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);
}
