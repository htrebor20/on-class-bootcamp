package com.pragma.onclass.domain.api;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.model.Technology;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ITechnologyServicePort {
      List<Technology> getAllTechnology(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy);
      void saveTechnology(Technology technology) throws BadRequestException;
      List<Technology> getAllTechnologiesByIds(List<Long> ids);
}

