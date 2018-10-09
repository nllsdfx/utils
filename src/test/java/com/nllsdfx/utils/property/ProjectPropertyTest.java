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

import com.nllsdfx.utils.exception.NotImplementedException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

public class ProjectPropertyTest extends PropsTest {


    private static String propPath;

    @BeforeClass
    public static void before() {
        propPath = "appTest.properties";
        property = new ProjectProperty(propPath);
    }


    @Test
    public void get() {

        assert property.get(null) == null;
        assert property.get(" ") == null;
        assert property.get("test").equals("true");

    }

    @Override
    @Test(expected = NotImplementedException.class)
    public void getAndSet() throws Exception {
        property.set("fds", "");
    }

    @Override
    public void isEmpty() {
        assert !property.isEmpty();
    }

    @Override
    @Test(expected = NotImplementedException.class)
    public void set() {
        property.set("gg", "ggg");
        property.set(new HashMap<>());
    }

    @Test(expected = NotImplementedException.class)
    public void clear() {
        property.clear();
    }


}