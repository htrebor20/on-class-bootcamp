package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.BootcampVersionResponse;
import com.pragma.onclass.adapters.driving.http.dto.response.VersionResponse;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bootcamp", source = "idBootcamp")
    Version addRequestToVersion(AddVersionRequest addVersionRequest);

    default Bootcamp  mapBootcamp(Long bootcampId) {
        return  new Bootcamp(bootcampId, null, null, null, null);
    }

    List<VersionResponse> toVersionResponseList(List<Version> versions);

    @Named("mapListVersionWithoutBootcampId")
    @Mapping(target = "idBootcamp", ignore = true)
    List<BootcampVersionResponse> mapListVersionWithoutBootcampId(List<Version> versions);
}
