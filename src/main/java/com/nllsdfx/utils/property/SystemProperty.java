package com.nllsdfx.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Use this class to access properties outside your project,
 * or, simply saying, outside your war/jar.
 */
public final class SystemProperty extends AbstractProperty {

    public SystemProperty(String fileName) {
        super(fileName);
    }

    @Override
    protected void init() {
        try (InputStream is = Files.newInputStream(Paths.get(fileName))) {
            properties.load(is);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
