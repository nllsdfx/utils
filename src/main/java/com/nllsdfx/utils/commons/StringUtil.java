/*
 *     Copyright (C) 2018  Nllsdfx Utils
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.nllsdfx.utils.commons;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Useful tools for string class.
 */
public final class StringUtil {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

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
        return !isBlank(string) && NUMERIC_PATTERN.matcher(string).matches();
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
        char[] c = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }


    /**
     * Converts string to hexadecimal format.
     *
     * @param text text to convert
     * @return hexadecimal representation of string.
     */
    public static String toHexadecimal(final String text) {

        if (isBlank(text)) {
            return null;
        }

        byte[] myBytes = text.getBytes(StandardCharsets.UTF_8);

        return DatatypeConverter.printHexBinary(myBytes);
    }
}
