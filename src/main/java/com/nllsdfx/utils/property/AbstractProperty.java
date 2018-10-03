package com.nllsdfx.utils.property;


import com.nllsdfx.utils.commons.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

abstract class AbstractProperty implements Property {

    final String fileName;

    final FixedProperties properties;


    AbstractProperty(String fileName) {

        if (StringUtil.isBlank(fileName)) {
            throw new NullPointerException("Specify not null fileName for property.");
        }

        this.fileName = fileName;
        properties = new FixedProperties();
        init();
    }

    protected void init() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(is);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public final String get(String key) {

        if (StringUtil.isBlank(key))
            return null;

        return properties.getProperty(key);
    }

    @Override
    public boolean set(String key, String value) {

        if (StringUtil.isBlank(key, value))
            return false;

        properties.setProperty(key, value);

        return writeProps();
    }

    @Override
    public boolean set(Map<String, String> map) {
        map.forEach(properties::setProperty);
        return writeProps();
    }

    @Override
    public boolean clear() {
        properties.clear();
        return properties.isEmpty();
    }

    @Override
    public boolean isEmpty() {
        return properties.isEmpty();
    }

    private boolean writeProps() {
        try (OutputStream os = Files.newOutputStream(Paths.get(fileName), StandardOpenOption.APPEND)) {
            properties.store(os, null);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
