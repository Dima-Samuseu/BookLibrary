package com.example.catalog.util;

public class ModelUtils {

    public static boolean idIsPresent(Long id) {
        return id != null && id > 0;
    }

    public static boolean idIsNotPresent(Long id) {
        return !idIsPresent(id);
    }
}
