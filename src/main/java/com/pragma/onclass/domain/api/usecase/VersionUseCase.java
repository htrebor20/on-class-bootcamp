package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.Constants;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.exception.BadRequestValidationException;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;

public class VersionUseCase implements IVersionServicePort {
    private final IVersionPersistencePort versionPersistencePort;
    private final IBootcampServicePort bootcampServicePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort, IBootcampServicePort bootcampServicePort) {
        this.versionPersistencePort = versionPersistencePort;
        this.bootcampServicePort = bootcampServicePort;
    }

    @Override
    public void saveVersion(Version version) throws BadRequestValidationException {
        if (version.getEndingDate().isBefore(version.getStartDate())) {
            throw new BadRequestValidationException(Constants.VERSION_VALIDATIONS_EXCEPTION_MESSAGE);
        }
        Bootcamp bootcamp = bootcampServicePort.findById(version.getBootcamp().getId());
        version.setBootcamp(bootcamp);
        versionPersistencePort.saveVersion(version);
    }
}
