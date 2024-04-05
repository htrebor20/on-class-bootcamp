package com.pragma.onclass.domain.spi;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.model.Version;

import java.util.List;

public interface IVersionPersistencePort {
    void saveVersion(Version version);

    List<Version> findAll(Integer page, Integer size, ConstantsAdapters.Sort sort, ConstantsAdapters.SortBy sortBy);

}
