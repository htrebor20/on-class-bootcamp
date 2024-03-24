package com.pragma.onclass.adapters;

public class ConstantsAdapters {
    private ConstantsAdapters() {
        throw new IllegalStateException("utility class");
    }

    public enum Sort {
        ASC,
        DESC
    }

    public enum SortBy {
        NAME,
        TECHNOLOGY_COUNT
    }
}
