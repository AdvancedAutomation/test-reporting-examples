package com.report.examples.utils;

import java.util.Map;

public final class Mapper {

    /**
     * Mapper constructor.
     */
    private Mapper() {
    }

    /**
     * @param value
     * @param storedValues
     * @return The new endPoint with params replaced.
     */
    public static String mapValue(final String value, final Map<String, String> storedValues) {
        String val = value;
        for (Map.Entry<String, String> entry : storedValues.entrySet()) {
            val = val.replace("{" + entry.getKey() + "}", String.format("%s", entry.getValue()));
        }
        return val;
    }
}
