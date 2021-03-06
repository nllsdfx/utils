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
