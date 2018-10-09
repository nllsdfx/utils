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

package com.nllsdfx.utils.property;

import java.util.Map;

public interface Property {

    /**
     * Returns value of a property if exists.
     *
     * @param key key to use to find a value.
     * @return value matches key, or {@code null} if key doesn't exist.
     */
    String get(String key);

    /**
     * Writes key-value pair to a property.
     * If key or value is blank, does nothing.
     *
     * @param key   key to write.
     * @param value value to write.
     * @return true if writing was successful, false otherwise
     */
    boolean set(String key, String value);


    /**
     * Writes map of key-value pairs to a property.
     * If value of a key is null, does nothing.
     *
     * @param map key-value pairs to set to a property.
     * @return true if writing was successful, false otherwise.
     */
    boolean set(Map<String, String> map);


    /**
     * Erases properties form all keys and values.
     *
     * @return true if operation was successful.
     */
    boolean clear();

    boolean isEmpty();

}
