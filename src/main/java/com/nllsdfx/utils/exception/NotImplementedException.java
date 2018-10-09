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

package com.nllsdfx.utils.exception;

/**
 * As it's not recommended to use classes from
 * sun.* packages the class was developed instead.
 */
public final class NotImplementedException extends RuntimeException {

    private NotImplementedException() {
    }

    private static class Holder {
        static final NotImplementedException EXCEPTION = new NotImplementedException();
    }

    public static NotImplementedException exception() {
        return Holder.EXCEPTION;
    }

}
