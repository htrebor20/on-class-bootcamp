package com.pragma.onclass.domain.api;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Version;

import java.util.List;

public interface IVersionServicePort {
    void saveVersion(Version version) throws BadRequestValidationException;

    List<Version> getAllVersion(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy);
}
