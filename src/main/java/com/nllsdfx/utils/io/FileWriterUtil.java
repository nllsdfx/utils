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

package com.nllsdfx.utils.io;


import com.nllsdfx.utils.log.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Util to write files.
 */
public final class FileWriterUtil {

    private static Log log = Log.getLogger(FileWriterUtil.class);

    private FileWriterUtil() {
    }

    /**
     * Standard file write operation. This method
     * works as if the {@link StandardOpenOption#CREATE CREATE}
     *
     * @param file    path to file.
     * @param content content to be written.
     * @return path of the written file, or {@code null} if
     * exception occurred.
     */
    public static Path writeFile(final Path file, final String content) {

        Path path = null;

        try {
            path = Files.write(file, content.getBytes());
        } catch (IOException ex) {
            log.error(ex);
        }

        return path;
    }
}
