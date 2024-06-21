package com.ukma.string.utils;

import org.apache.commons.lang3.StringUtils;

public class StringValidationUtils {

    public static boolean isBlank(String value) {
        return StringUtils.isBlank(value);
    }

    public static boolean isAllUpperCase(String value) {
        return StringUtils.isAllUpperCase(value);
    }

    public static boolean isNumeric(String value) {
        return StringUtils.isNumeric(value);
    }

    public static boolean isUkrainianPhone(String value) {
        String regex = "\\+380[0-9]{2}[0-9]{7}";

        return isNumeric(value.substring(1)) && value.matches(regex);
    }

    public static boolean isEmail(String value) {
        String regex = "[a-zA-z0-9.]{5,20}@[a-z]{3,}\\.[a-z]{2,}";
        return value.matches(regex);
    }
}
