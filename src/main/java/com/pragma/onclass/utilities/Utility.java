package com.pragma.onclass.utilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utility {
    private Utility() {
        throw new IllegalStateException("utility class");
    }

    public static boolean hasRepeatedIds(List<Long> ids) {
        Set<Long> uniqueIds = new HashSet<>(ids);
        return ids.size() != uniqueIds.size();
    }
}
