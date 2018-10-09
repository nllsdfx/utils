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

import com.nllsdfx.utils.io.FileUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SystemPropertyTest extends PropsTest {


    private static String propPath;

    @BeforeClass
    public static void before() {
        propPath = System.getProperty("user.home") + File.separator + "teeeeeeestPropssss.properties";
        assert FileUtil.createFile(propPath) != null;
        property = new SystemProperty(propPath);
    }


    @Test
    public void get() {
        assert property.set("test", "true");

        assert property.get(null) == null;
        assert property.get(" ") == null;
        assert property.get("test").equals("true");

    }


    @Test
    public void clear() {
        property.set("fffffffff", "ffffffffffffff");
        assert property.get("fffffffff") != null;
        assert property.clear();
        assert property.get("fffffffff") == null;
    }


    @AfterClass
    public static void after() {
        assert FileUtil.deleteFile(propPath);
        assert Files.notExists(Paths.get(propPath));
    }

    @Override
    public void isEmpty() {
        assert property.set("asdfasfads", "sfsd");
        assert property.clear();
        assert property.get("asdfasfads") == null && property.isEmpty();
    }
}