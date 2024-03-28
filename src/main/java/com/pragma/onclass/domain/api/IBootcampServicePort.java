package com.pragma.onclass.domain.api;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {
    void saveBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamp(Integer page, Integer size, ConstantsAdapters.Sort sortType, ConstantsAdapters.SortBy sortBy);
}
