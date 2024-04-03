package com.pragma.onclass.adapters;

public class ConstantsAdapters {
    private ConstantsAdapters() {
        throw new IllegalStateException("utility class");
    }

    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "5";

    public enum Sort {
        ASC,
        DESC
    }

    public enum SortBy {
        NAME,
        TECHNOLOGY_COUNT,
        CAPABILITY_COUNT
    }
}
