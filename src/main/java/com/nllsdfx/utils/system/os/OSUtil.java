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

package com.nllsdfx.utils.system.os;

/**
 * Handful utils to work with OS.
 */
public final class OSUtil {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    private OSUtil() {
    }

    /**
     * Checks whether user's OS is Windows.
     * @return true if OS is Windows, false otherwise.
     */
    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    /**
     * Checks whether user's OS is Mac OS.
     * @return true if OS is Mac OS, false otherwise.
     */
    public static boolean isMac() {
        return (OS.contains("mac"));
    }

    /**
     * Checks whether user's OS is Unix.
     * @return true if OS is Unix, false otherwise.
     */
    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }

    /**
     * Gets and returns OSType.
     *
     * @return OSType or {@code null} if
     * OSType is not detected.
     */
    public static OSType getOSType() {

        if (isWindows()) {
            return OSType.WINDOWS;
        }

        if (isUnix()) {
            return OSType.UNIX;
        }

        if (isMac()) {
            return OSType.MAC;
        }

        return null;
    }

}
