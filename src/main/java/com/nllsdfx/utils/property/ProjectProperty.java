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

import java.util.Map;

/**
 * Use this class to access properties inside project or,
 * simply saying, your jar/war. Set methods are not used
 * cause we can't change project files at runtime.
 */
public final class ProjectProperty extends AbstractProperty {

    public ProjectProperty(String fileName) {
        super(fileName);
    }

    @Override
    public final boolean set(String key, String value) {
        throw NotImplementedException.exception();
    }

    @Override
    public final boolean set(Map<String, String> map) {
        throw NotImplementedException.exception();
    }

    @Override
    public final boolean clear() {
        throw NotImplementedException.exception();
    }
}
