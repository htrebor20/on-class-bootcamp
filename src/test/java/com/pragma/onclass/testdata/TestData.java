package com.pragma.onclass.testdata;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapabilityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.onclass.adapters.driving.http.dto.request.AddCapabilityRequest;
import com.pragma.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.*;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.model.Technology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestData {

    /// TECHNOLOGY ///
    public static Technology createTechnologyModel() { return new Technology(1L, "java", "java language");}

    public static AddTechnologyRequest createTechnologyDTO() {return new AddTechnologyRequest("java", "java language");}

    public static TechnologyEntity createTechnologyEntity() {return new TechnologyEntity(1L, "java", "java language");}

    public static List<Technology> getTechnologiesList() {
        return getTechnologiesList(5);
    }

    public static List<Technology> getTechnologiesList(int maxTechnologies) {
        List<Technology> technologies = new ArrayList<>();

        technologies.add(new Technology(1L, "Java", "Java language"));
        technologies.add(new Technology(2L, "Python", "Python language"));
        technologies.add(new Technology(3L, "JavaScript", "JavaScript language"));
        technologies.add(new Technology(4L, "C++", "C++ programming language"));
        technologies.add(new Technology(5L, "Ruby", "Ruby programming language"));

        if (maxTechnologies > 0 && maxTechnologies < technologies.size()) {
            return technologies.subList(0, maxTechnologies);
        } else {
            return technologies;
        }
    }

    public static List<TechnologyResponse> getTechnologiesDTOList() {
        return getTechnologiesDTOList(5);
    }

    public static List<TechnologyResponse> getTechnologiesDTOList(int maxTechnologies) {
        List<TechnologyResponse> technologies = new ArrayList<>();

        technologies.add(new TechnologyResponse(1L, "Java", "Java language"));
        technologies.add(new TechnologyResponse(2L, "Python", "Python language"));
        technologies.add(new TechnologyResponse(3L, "JavaScript", "JavaScript language"));
        technologies.add(new TechnologyResponse(4L, "C++", "C++ programming language"));
        technologies.add(new TechnologyResponse(5L, "Ruby", "Ruby programming language"));

        if (maxTechnologies > 0 && maxTechnologies < technologies.size()) {
            return technologies.subList(0, maxTechnologies);
        } else {
            return technologies;
        }
    }

    public static List<TechnologyEntity> getTechnologiesEntityList() {
        List<TechnologyEntity> technologies = new ArrayList<>();

        technologies.add(new TechnologyEntity(1L, "Java", "Java language"));
        technologies.add(new TechnologyEntity(2L, "Python", "Python language"));
        technologies.add(new TechnologyEntity(3L, "JavaScript", "JavaScript language"));

        return technologies;
    }

    public static Page<TechnologyEntity> getTechnologiesEntityPage() {
        int pageSize = 10;
        int fromIndex = 0;
        int toIndex = Math.min(fromIndex + pageSize, getTechnologiesEntityList().size());

        return new PageImpl<>(getTechnologiesEntityList().subList(fromIndex, toIndex));
    }

    public static List<CapabilityTechnologyResponse> getCapabilityTechnologyResponseList() {
        return getCapabilityTechnologyResponseList(3);
    }

    public static List<CapabilityTechnologyResponse> getCapabilityTechnologyResponseList(int maxCapacities) {
        List<CapabilityTechnologyResponse> capabilities = new ArrayList<>();
        capabilities.add(new CapabilityTechnologyResponse(1L, "Java"));
        capabilities.add(new CapabilityTechnologyResponse(2L, "Python"));
        capabilities.add(new CapabilityTechnologyResponse(3L, "JavaScript"));

        if (maxCapacities > 0 && maxCapacities < capabilities.size()) {
            return capabilities.subList(0, maxCapacities);
        } else {
            return capabilities;
        }
    }

    public static List<Technology> getTechnologiesListRepeat() {
        List<Technology> technologies = getTechnologiesList();
        technologies.add(new Technology(1L, "Java", "Java language"));
        return technologies;
    }

    public static List<Technology> getTechnologiesListSorted(ConstantsAdapters.Sort sortOrder) {
        List<Technology> technologies = getTechnologiesList();
        Comparator<Technology> comparator = Comparator.comparing(Technology::getName);

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        technologies.sort(comparator);
        return technologies;
    }

    public static List<TechnologyResponse> getTechnologiesDTOListSorted(ConstantsAdapters.Sort sortOrder) {
        List<TechnologyResponse> technologies = getTechnologiesDTOList();
        Comparator<TechnologyResponse> comparator = Comparator.comparing(TechnologyResponse::getName);

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        technologies.sort(comparator);
        return technologies;
    }

    /// CAPABILITY ///

    public static Capability createCapabilityModel() {
        return new Capability(1L, "Backend Java", "Backend Java Description", getTechnologiesList());
    }

    public static AddCapabilityRequest createCapabilityDTO() {
        return new AddCapabilityRequest (1L, "Backend Java", "Backend Java Description", Arrays.asList(1L, 2L, 3L));
    }

    public static CapabilityEntity createCapabilityEntity() {
        return new CapabilityEntity (1L, "Backend Java", "Backend Java Description", getTechnologiesEntityList());
    }

    public static Capability createCapabilityModelWithRepeatTech() {
        List<Technology> technologies = getTechnologiesListRepeat();
        technologies.add(new Technology(1L, "Java", "Java language"));
        return new Capability(1L, "Backend Java", "Backend Java Description", technologies);
    }

    public static List<Capability> getCapabilityList() {
        return getCapabilityList(3);
    }

    public static List<Capability> getCapabilityList(int maxCapacities) {
        List<Capability> capabilities = new ArrayList<>();
        capabilities.add(new Capability(1L, "Backend Java", "Backend Java Description", getTechnologiesList(4)));
        capabilities.add(new Capability(2L, "Frontend JavaScript", "Frontend JavaScript Description", getTechnologiesList(3)));
        capabilities.add(new Capability(3L, "Mobile Development", "Mobile Development Description", getTechnologiesList(5)));

        if (maxCapacities > 0 && maxCapacities < capabilities.size()) {
            return capabilities.subList(0, maxCapacities);
        } else {
            return capabilities;
        }
    }

    public static List<CapabilityResponse> getCapabilityDTOList() {
        return getCapabilityDTOList(3);
    }

    public static List<CapabilityResponse> getCapabilityDTOList(int maxCapacities) {
        List<CapabilityResponse> capabilities = new ArrayList<>();
        capabilities.add(new CapabilityResponse(1L, "Backend Java", "Backend Java Description", getCapabilityTechnologyResponseList(2)));
        capabilities.add(new CapabilityResponse(2L, "Frontend JavaScript", "Frontend JavaScript Description", getCapabilityTechnologyResponseList(3)));
        capabilities.add(new CapabilityResponse(3L, "Mobile Development", "Mobile Development Description",getCapabilityTechnologyResponseList(1)));
        if (maxCapacities > 0 && maxCapacities < capabilities.size()) {
            return capabilities.subList(0, maxCapacities);
        } else {
            return capabilities;
        }
    }

    public static List<Capability> getCapabilitiesListSorted(ConstantsAdapters.Sort sortOrder) {
        List<Capability> capabilities = getCapabilityList();
        Comparator<Capability> comparator = Comparator.comparing(Capability::getName);

        if (sortOrder ==ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        capabilities.sort(comparator);
        return capabilities;
    }

    public static List<Capability> getCapabilitiesListSortedByTechnologyCount(ConstantsAdapters.Sort sortOrder) {
        List<Capability> capabilities = getCapabilityList();
        Comparator<Capability> comparator = Comparator.comparingInt(capability -> capability.getTechnologies().size());

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        capabilities.sort(comparator);
        return capabilities;
    }

    public static List<CapabilityResponse> getCapabilitiesDTOListSorted(ConstantsAdapters.Sort sortOrder) {
        List<CapabilityResponse> capabilities = getCapabilityDTOList();
        Comparator<CapabilityResponse> comparator = Comparator.comparing(CapabilityResponse::getName);

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        capabilities.sort(comparator);
        return capabilities;
    }

    public static List<CapabilityResponse> getCapabilitiesDTOListSortedByTechnologyCount(ConstantsAdapters.Sort sortOrder) {
        List<CapabilityResponse> capabilities = getCapabilityDTOList();
        Comparator<CapabilityResponse> comparator = Comparator.comparingInt(capability -> capability.getTechnologies().size());

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        capabilities.sort(comparator);
        return capabilities;
    }

    public static List<BootcampCapabilityResponse> getBootcampCapabilitiesResponseList() {
        return getBootcampCapabilitiesResponseList(3);
    }

    public static List<BootcampCapabilityResponse> getBootcampCapabilitiesResponseList(int maxCapacities) {
        List<BootcampCapabilityResponse> capabilities = new ArrayList<>();
        capabilities.add(new BootcampCapabilityResponse(1L, "Backend Java", getCapabilityTechnologyResponseList()));
        capabilities.add(new BootcampCapabilityResponse(2L, "Backend Python", getCapabilityTechnologyResponseList()));
        capabilities.add(new BootcampCapabilityResponse(3L, "Frontend JavaScript", getCapabilityTechnologyResponseList()));

        if (maxCapacities > 0 && maxCapacities < capabilities.size()) {
            return capabilities.subList(0, maxCapacities);
        } else {
            return capabilities;
        }
    }

    public static List<CapabilityEntity> getCapabilitiesEntityList() {
        List<CapabilityEntity> capabilities = new ArrayList<>();
        capabilities.add(new CapabilityEntity(1L, "Backend Java", "Backend Java Description", getTechnologiesEntityList()));
        capabilities.add(new CapabilityEntity(2L, "Frontend JavaScript", "Frontend JavaScript Description", getTechnologiesEntityList()));
        capabilities.add(new CapabilityEntity(3L, "Mobile Development", "Mobile Development Description", getTechnologiesEntityList()));
        return capabilities;
    }

    public static Page<CapabilityEntity> getCapabilitiesEntityPage() {
        int pageSize = 10;
        int fromIndex = 0;
        int toIndex = Math.min(fromIndex + pageSize, getTechnologiesEntityList().size());
        return new PageImpl<>(getCapabilitiesEntityList().subList(fromIndex, toIndex));
    }

    /// BOOTCAMP ///

    public static Bootcamp createBootcamp() {
        return new Bootcamp(1L, "Bootcamp Pragma", "Bootcamp Pragma Backend", getCapabilityList(), null);
    }

    public static AddBootcampRequest createBootcampDTO() {
        return new AddBootcampRequest (1L, "Pragma Backend", "Backend Java Description", Arrays.asList(1L, 2L, 3L));
    }

    public static BootcampEntity createBootcampEntity() {
        return new BootcampEntity (1L, "Pragma Backend", "Backend Java Description", getCapabilitiesEntityList(),null);
    }

    public static List<Bootcamp> getBootcampsList() {
        return getBootcampsList(3);
    }

    public static List<Bootcamp> getBootcampsList(int maxCapacities) {
        List<Bootcamp> bootcamp = new ArrayList<>();
        bootcamp.add(new Bootcamp(1L, "Bootcamp Pragma I", "Backend Java Description", getCapabilityList(2), null));
        bootcamp.add(new Bootcamp(2L, "Bootcamp Pragma II", "Frontend Javascript Description", getCapabilityList(1), null));
        bootcamp.add(new Bootcamp(3L, "Bootcamp Pragma III", "Mobile Flutter Description", getCapabilityList(3), null));

        if (maxCapacities > 0 && maxCapacities < bootcamp.size()) {
            return bootcamp.subList(0, maxCapacities);
        } else {
            return bootcamp;
        }
    }

    public static List<BootcampResponse> getBootcampsDTOList() {
        return getBootcampsDTOList(3);
    }

    public static List<BootcampResponse> getBootcampsDTOList(int maxCapacities) {
        List<BootcampResponse> bootcamp = new ArrayList<>();
        bootcamp.add(new BootcampResponse(1L, "Bootcamp Pragma I", "Backend Java Description", getBootcampCapabilitiesResponseList(2)));
        bootcamp.add(new BootcampResponse(2L, "Bootcamp Pragma II", "Frontend JavaScript Description", getBootcampCapabilitiesResponseList(3)));
        bootcamp.add(new BootcampResponse(3L, "Bootcamp Pragma III", "Mobile Development Description",getBootcampCapabilitiesResponseList(1)));
        if (maxCapacities > 0 && maxCapacities < bootcamp.size()) {
            return bootcamp.subList(0, maxCapacities);
        } else {
            return bootcamp;
        }
    }

    public static List<Bootcamp> getBootcampListSortedByCapabilityCount(ConstantsAdapters.Sort sortOrder) {
        List<Bootcamp> bootcampList = getBootcampsList();
        Comparator<Bootcamp> comparator = Comparator.comparingInt(bootcamp -> bootcamp.getCapabilities().size());

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }

        bootcampList.sort(comparator);
        return bootcampList;
    }

    public static List<Bootcamp> getBootcampListSorted(ConstantsAdapters.Sort sortOrder) {
        List<Bootcamp> bootcampList = getBootcampsList();
        Comparator<Bootcamp> comparator = Comparator.comparing(Bootcamp::getName);

        if (sortOrder ==ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        bootcampList.sort(comparator);
        return bootcampList;
    }

    public static List<BootcampResponse> getBootcampDTOListSorted(ConstantsAdapters.Sort sortOrder) {
        List<BootcampResponse> bootcamp = getBootcampsDTOList();
        Comparator<BootcampResponse> comparator = Comparator.comparing(BootcampResponse::getName);

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        bootcamp.sort(comparator);
        return bootcamp;
    }

    public static List<BootcampResponse> getBootcampDTOListSortedByTechnologyCount(ConstantsAdapters.Sort sortOrder) {
        List<BootcampResponse> bootcamps = getBootcampsDTOList();
        Comparator<BootcampResponse> comparator = Comparator.comparingInt(bootcamp -> bootcamp.getCapabilities().size());

        if (sortOrder == ConstantsAdapters.Sort.DESC) {
            comparator = comparator.reversed();
        }
        bootcamps.sort(comparator);
        return bootcamps;
    }

    public static List<BootcampEntity> getBootcampEntityList() {
        List<BootcampEntity> bootcampEntities = new ArrayList<>();
        bootcampEntities.add(new BootcampEntity(1L, "Bootcamp Pragma I", "Backend Java Description", getCapabilitiesEntityList(),null));
        bootcampEntities.add(new BootcampEntity(2L, "Bootcamp Pragma II", "Frontend JavaScript Description", getCapabilitiesEntityList(),null));
        bootcampEntities.add(new BootcampEntity(3L, "Bootcamp Pragma III", "Mobile Development Description", getCapabilitiesEntityList(),null));
        return bootcampEntities;
    }

    public static Page<BootcampEntity> getBootcampEntityPage() {
        int pageSize = 10;
        int fromIndex = 0;
        int toIndex = Math.min(fromIndex + pageSize, getBootcampEntityList().size());
        return new PageImpl<>(getBootcampEntityList().subList(fromIndex, toIndex));
    }
}
