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
     */
    boolean set(String key, String value);


    /**
     * Writes map of key-value pairs to a property.
     * If value of a key is null, does nothing.
     *
     * @param map key-value pairs to set to a property.
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
