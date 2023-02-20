package com.tuanla.springserver.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class StringUtils {

    private static final String WORD_SEPARATORS = " .-_/()";

    public static String toSentenceCase(final String s) {
        final StringBuilder sb = new StringBuilder(s);
        return toSentenceCase(sb).toString();
    }

    private static StringBuilder toSentenceCase(final StringBuilder sb) {
        boolean capitalizeNext = true;
        for (int i = 0; i < sb.length(); i++) {
            final char c = sb.charAt(i);
            if (c == '.') {
                capitalizeNext = true;
            } else if (capitalizeNext && !isSeparator(c)) {
                sb.setCharAt(i, Character.toTitleCase(c));
                capitalizeNext = false;
            } else if (!Character.isLowerCase(c)) {
                sb.setCharAt(i, Character.toLowerCase(c));
            }
        }
        return sb;
    }

    private static boolean isSeparator(char c) {
        return WORD_SEPARATORS.indexOf(c) >= 0;
    }

    public static String toTitleCase(final String s) {
        final StringBuilder sb = new StringBuilder(s);
        return toTitleCase(sb).toString();
    }

    private static StringBuilder toTitleCase(final StringBuilder sb) {
        boolean capitalizeNext = true;
        for (int i = 0; i < sb.length(); i++) {
            final char c = sb.charAt(i);
            if (isSeparator(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                sb.setCharAt(i, Character.toTitleCase(c));
                capitalizeNext = false;
            } else if (!Character.isLowerCase(c)) {
                sb.setCharAt(i, Character.toLowerCase(c));
            }
        }
        return sb;
    }

    public static String generateRandomString(int length) {
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    public static boolean isValidString(List<String> checkLstString, String tmpStr) {
        boolean isValidString = false;
        for (String tempStr : checkLstString) {
            if (tempStr.trim().equals(tmpStr.trim())) {
                isValidString = false;
            } else {
                isValidString = true;
            }
        }
        return isValidString;
    }

    public static String nvl(Object obj, String value) {
        return (obj.toString() == null || obj.toString().equals("") || obj.toString().isEmpty()) ? obj.toString() : value;
    }

    public static boolean isNotNullString(String tmpStr) {
        boolean isValidString = true;
        if ("".equals(tmpStr) || "".equals(tmpStr.trim())) {
            isValidString = false;
        }
        return isValidString;
    }

    public static String trimString(String str) {
        str = str.trim();
        return str;
    }

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
