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

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

class CopyDirVisitor extends SimpleFileVisitor<Path> {

    private final Path from;
    private final Path to;


    private static final StandardCopyOption COPY_OPTION = StandardCopyOption.REPLACE_EXISTING;

    CopyDirVisitor(Path from, Path to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        Path target = to.resolve(from.relativize(dir));

        if (Files.notExists(target)) {
            Files.createDirectory(target);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.copy(file, to.resolve(from.relativize(file)), COPY_OPTION);
        return FileVisitResult.CONTINUE;
    }
}
