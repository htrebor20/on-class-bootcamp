package com.pragma.onclass.testdata;

import com.pragma.onclass.adapters.ConstantsAdapters;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capability;
import com.pragma.onclass.domain.model.Technology;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestData {

    /// TECHNOLOGY ///
    public static Technology createTechnologyModel() {
        return new Technology(1L, "java", "java language");
    }

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

    /// CAPABILITY ///

    public static Capability createCapabilityModel() {
        return new Capability(1L, "Backend Java", "Backend Java Description", getTechnologiesList());
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

    /// BOOTCAMP ///

    public static Bootcamp createBootcamp() {
        return new Bootcamp(1L, "Bootcamp Pragma", "Bootcamp Pragma Backend", getCapabilityList());
    }

    public static List<Bootcamp> getBootcampsList() {
        return getBootcampsList(3);
    }

    public static List<Bootcamp> getBootcampsList(int maxCapacities) {
        List<Bootcamp> bootcamp = new ArrayList<>();
        bootcamp.add(new Bootcamp(1L, "Bootcamp Pragma I", "Backend Java Description", getCapabilityList(2)));
        bootcamp.add(new Bootcamp(2L, "Bootcamp Pragma II", "Frontend Javascript Description", getCapabilityList(1)));
        bootcamp.add(new Bootcamp(3L, "Bootcamp Pragma III", "Mobile Flutter Description", getCapabilityList(3)));

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
}