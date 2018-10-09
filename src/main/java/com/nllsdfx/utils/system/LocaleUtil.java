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

package com.nllsdfx.utils.system;

import java.util.Locale;

/**
 * Utils to work with locales.
 *
 * @author nllsdfx
 */
public final class LocaleUtil {

    private static final Locale LOCALE = new Locale(getOSLanguage(), getOSCountry());

    private LocaleUtil() {
    }

    /**
     * Gets OS country. Returns upper case code, e.g. RU (language_COUNTRY)
     *
     * @return upper case code, e.g. RU (language_COUNTRY)
     */
    public static String getOSCountry() {
        return System.getProperty("user.country");
    }

    /**
     * Gets OS language. Returns lower case code, e.g. ru (language_COUNTRY)
     *
     * @return lower case code, e.g. ru (language_COUNTRY)
     */
    public static String getOSLanguage() {
        return System.getProperty("user.language");
    }

    /**
     * Returns OS locale in form language_COUNTRY (en_US e.g.)
     *
     * @return OS locale
     * @see Locale
     */
    public static Locale getOSLocale() {
        return LOCALE;
    }


}
