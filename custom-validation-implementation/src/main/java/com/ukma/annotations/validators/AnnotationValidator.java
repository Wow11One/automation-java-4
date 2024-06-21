package com.ukma.annotations.validators;

import com.ukma.runtime.annotations.Length;
import com.ukma.runtime.annotations.NotBlank;
import com.ukma.string.utils.StringValidationUtils;

import java.lang.reflect.Field;

public class AnnotationValidator {
    public boolean validate(Object object) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(NotBlank.class)) {
                    field.setAccessible(true);
                    String value = (String) field.get(object);
                    if (StringValidationUtils.isBlank(value)) {
                        return false;
                    }
                }

                if (field.isAnnotationPresent(Length.class)) {
                    field.setAccessible(true);
                    int valueLength = ((String) field.get(object)).length();
                    var lengthAnnotation = field.getAnnotation(Length.class);
                    int min = lengthAnnotation.min();
                    int max = lengthAnnotation.max();
                    if (valueLength < min || valueLength > max) {
                        return false;
                    }
                }
            }
        } catch (Exception exception) {
            return false;
        }
        return true;
    }
}
