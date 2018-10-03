package com.nllsdfx.utils.commons;

import java.util.Arrays;

/**
 * Useful tools for string class.
 */
public final class StringUtil {

    private StringUtil() {
    }

    public static boolean isBlank(final String string) {
        return string == null || string.trim().isEmpty();
    }

    /**
     * Tests whether ANY of the given strings is blank.
     *
     * @param strings string to test
     * @return true if at least one of the given strings
     * is blank or if array of strings is empty,
     * false otherwise.
     */
    public static boolean isBlank(final String... strings) {
        return strings.length == 0
                || Arrays.stream(strings).anyMatch(StringUtil::isBlank);
    }

    /**
     * Tests whether all of strings are blank.
     *
     * @param strings string to test
     * @return true and only true if ALL of the
     * given strings are blank.
     */
    public static boolean allBlank(final String... strings) {
        return strings.length == 0
                || Arrays.stream(strings).allMatch(StringUtil::isBlank);
    }

    /**
     * Checks whether given string is numeric.
     * String can be null. Spaces are not trimmed.
     *
     * @param string string to check
     * @return true if string is numeric,
     * false otherwise.
     */
    public static boolean isNumeric(final String string) {
        return !isBlank(string) && string.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Converts first letter of the string to
     * lower case.
     *
     * @param string string to convert the first letter of
     * @return the same string but with the first letter
     * in lower case.
     * @throws NullPointerException if string is null
     */
    public static String firstToLowerCase(final String string) {
        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }
}
