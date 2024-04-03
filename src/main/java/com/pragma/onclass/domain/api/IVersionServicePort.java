package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Version;

public interface IVersionServicePort {
    void saveVersion(Version version) throws BadRequestValidationException;
}