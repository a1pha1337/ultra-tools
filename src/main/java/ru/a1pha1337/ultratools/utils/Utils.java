package ru.a1pha1337.ultratools.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utils {
    public static <T> Map<String, T> getFieldsFromClass(Class<?> clazz, Class<T> castTo) {
        HashMap<String, T> items = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field field : fields) {
                items.put(field.getName().toLowerCase(Locale.ROOT), castTo.cast(field.get(null)));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}
