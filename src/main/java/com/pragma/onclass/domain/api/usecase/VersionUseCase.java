package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.Constants;
import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;

public class VersionUseCase implements IVersionServicePort {
    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }

    @Override
    public void saveVersion(Version version) throws BadRequestValidationException {
        if (version.getEndingDate().isBefore(version.getStartDate())) {
            throw new BadRequestValidationException(Constants.VERSION_VALIDATIONS_EXCEPTION_MESSAGE);
        }
        versionPersistencePort.saveVersion(version);
    }
}
