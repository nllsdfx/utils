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


import com.nllsdfx.utils.commons.StringUtil;
import com.nllsdfx.utils.log.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class consists of all needed common file operations,
 * such as copy, delete, createProps etc.
 */
public final class FileUtil {

    private static Log log = Log.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * Created dir with the given dirPath.
     * If dir exists it won't be created and false will be returned.
     *
     * @param dirPath directory path where to createProps a dir.
     * @return true, if dir was created, false otherwise.
     */
    public static boolean createDir(final String dirPath) {

        if (StringUtil.isBlank(dirPath)) {
            log.debug("DirPath is blank");
            return false;
        }

        Path path = Paths.get(dirPath);

        Path file = null;

        if (Files.notExists(path)) {
            try {
                file = Files.createDirectories(path);
            } catch (IOException e) {
                log.error(e);
                return false;
            }
        }

        return file != null && Files.exists(path);
    }

    /**
     * @see #createDir(String)
     */
    public static boolean createDir(Path path) {
        return createDir(path.toString());
    }

    /**
     * Creates tmp directory in the given destination and
     * returns it.
     *
     * @param destination the director where to create tmp dir
     * @return Path of tmp dir or {@code null}
     * if can't create tmp dir.
     */
    public static Path createTMPDir(final String destination) {

        if (!isDir(destination)) {
            return null;
        }

        Path dir = Paths.get(destination);

        try {

            if (Files.notExists(dir)) {
                Files.createDirectories(dir);
            }

            return Files.createTempDirectory(dir, null);
        } catch (IOException e) {
            log.error(e);
            return null;
        }

    }

    /**
     * Tries to createProps file (not a directory).
     *
     * @param absolutePath absolute path where file is desired to be.
     * @return Path of the file only and only if it was created, null otherwise.
     */
    public static Path createFile(final String absolutePath) {

        if (StringUtil.isBlank(absolutePath) || isDir(absolutePath)) {
            return null;
        }

        int index = absolutePath.lastIndexOf(File.separator);

        Path dir = Paths.get(absolutePath.substring(0, index));

        Path file = Paths.get(absolutePath);

        try {

            if (Files.exists(file)) {
                return file;
            }

            if (Files.notExists(dir)) {
                Files.createDirectories(dir);
            }

            return Files.createFile(file);

        } catch (IOException ex) {
            log.error(ex);
            return null;
        }

    }

    /**
     * Creates a .tmp file (not a directory).
     *
     * @param destination absolute path where file is desired to be.
     * @return Path of the created file if it was created, null otherwise.
     */
    public static Path createTMPFile(final String destination) {

        if (!isDir(destination)) {
            return null;
        }

        Path dir = Paths.get(destination);

        try {

            if (Files.notExists(dir)) {
                Files.createDirectories(dir);
            }

            return Files.createTempFile(Paths.get(destination), null, null);

        } catch (IOException e) {
            log.error(e);
            return null;
        }

    }

    /**
     * Returns whether given path is dir path or not.
     * If directory doesn't exist and the given path doesn't
     * end with {@link File#separator} than the method returns false.
     *
     * @param path path of file
     * @return true, only and only if path is a dir path.
     */
    public static boolean isDir(final String path) {

        if (StringUtil.isBlank(path)) {
            return false;
        }

        int index = path.lastIndexOf("/");

        if (index == -1) {
            index = path.lastIndexOf("\\");
        }

        if (index != -1 && index == path.length() - 1) {

            // /filename.extension/ (not a directory)
            String str = path.substring(0, index);

            if (str.contains(File.separator)) {
                str = str.substring(str.lastIndexOf(File.separator));
            }

            if (str.contains(".")) {
                return false;
            }
        }

        return path.endsWith(File.separator)
                || path.endsWith("/")
                || Files.isDirectory(Paths.get(path));
    }

    /**
     * Deletes the file (not the dir) if it exists.
     *
     * @param absolutelyPath path to file.
     * @return only and only true, if file was deleted, false otherwise.
     */
    public static boolean deleteFile(final String absolutelyPath) {

        if (StringUtil.isBlank(absolutelyPath) || isDir(absolutelyPath)) {
            return false;
        }

        try {
            return Files.deleteIfExists(Paths.get(absolutelyPath));
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }

    /**
     * Deletes dir and everything inside this dir.
     *
     * @param path path of the dir to delete.
     * @return only and only true, if dir was deleted, false otherwise.
     */
    public static boolean deleteDir(final String path) {

        if (!isDir(path)) {
            return false;
        }

        Path root = Paths.get(path);

        if (Files.notExists(root)) {
            return false;
        }

        boolean isDeleted = false;

        try {
            isDeleted = Files.walk(root, FileVisitOption.FOLLOW_LINKS)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .filter(File::exists)
                    .allMatch(File::delete);

        } catch (IOException ex) {
            log.error(ex);
        }

        return isDeleted;
    }

    /**
     * Searches for the directory with the given name
     * starting from the given location.
     *
     * @param name     name of a dir to search
     * @param location location where to start searching
     * @return {@link Path} of the first dir if it was found,
     * null otherwise.
     */
    public static Path findDirectory(final String name, final String location) {

        if (StringUtil.isBlank(name, location)) {
            return null;
        }

        Path dir = null;

        DirectoryStream.Filter<Path> filter = file -> (Files.isDirectory(file));


        Path currentDirPath = FileSystems.getDefault().getPath(location);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirPath, filter)) {
            for (Path path : stream) {
                if (name.equals(path.getFileName().toString())) {
                    dir = path;
                    break;
                }
            }
        } catch (IOException ex) {
            log.error(ex);
        }

        return dir;
    }

    public static boolean copyFile(Path file, Path to, boolean replace) {

        if (file == null || to == null || Files.notExists(file)) {
            return false;
        }

        Path path = null;

        Path target = to.resolve(file.getFileName());

        try {

            if (replace) {
                path = Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
            } else {

                if (Files.notExists(target)) {
                    path = Files.copy(file, target);
                }
            }

        } catch (IOException e) {
            log.error(e);
        }

        return path != null;
    }

    /**
     * Copies dir and all its content to another dir.
     * It creates dirs to build path, if it doesn't exist
     * Source must be a dir
     *
     * @param source dir to copy
     * @param to     destination of copying
     * @return true if files were copied,
     * false otherwise.
     */
    public static boolean copyDir(final Path source, final Path to) {

        if ((source == null || to == null) || !isDir(source.toString())
                || StringUtil.isBlank(to.toString())) {

            return false;
        }

        try {
            Files.walkFileTree(source, new CopyDirVisitor(source, to));
        } catch (IOException e) {
            log.error(e);
            return false;
        }

        return Files.exists(to);

    }

    /**
     * Returns list of all dirs that are inside
     * dir of the given path.
     *
     * @param path path of the dir to look through,
     *             must be valid dir path
     * @return list of all paths of dirs this path dir
     * contains
     * @throws IllegalArgumentException if path is not valid dir path
     */
    public static List<Path> dirList(String path) {

        if (!isDir(path)) {
            throw new IllegalArgumentException("Path can't be blank and must be valid dir!");
        }

        List<Path> paths = new LinkedList<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {

            directoryStream.forEach(e -> {
                if (Files.isDirectory(e))
                    paths.add(e);
            });

        } catch (IOException ex) {
            log.error(ex);
            return Collections.emptyList();
        }

        return paths;

    }

    /**
     * Returns THE FIRST FOUND file path.
     *
     * @param name     file to find
     * @param location to start from
     * @return Path if found, null otherwise
     */
    public static Path findFile(String name, String location) {

        if (StringUtil.isBlank(name, location)) {
            return null;
        }

        Path filePath = null;

        try {

            Stream<Path> pathStream = Files.find(Paths.get(location), Integer.MAX_VALUE,
                    (path, basicFileAttributes) -> path.toFile().getName().matches(name));
            filePath = pathStream
                    .findFirst().orElse(null);
            pathStream.close(); // have to close otherwise stupid windows won't let us delete file.

        } catch (IOException ex) {
            log.error(ex);
        }

        return filePath;
    }

    /**
     * Returns ALL files matched with given name
     *
     * @param name     what to find
     * @param location where to start
     * @return list of found path, empty list otherwise
     */
    public static List<Path> findFiles(String name, String location) {

        List<Path> paths = null;

        try {
            paths = Files.find(Paths.get(location), Integer.MAX_VALUE,
                    (p, b) -> p.toFile().getName().matches(name)).collect(Collectors.toList());
        } catch (IOException ex) {
            log.error(ex);
        }

        return paths == null ? Collections.emptyList() : paths;
    }

    public static List<Path> listAll(String path) {

        List<Path> paths = new LinkedList<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {

            directoryStream.forEach(paths::add);

        } catch (IOException ex) {
            log.error(ex);
        }

        return paths;
    }

    public static String getFileExtensionWithDot(final String fileName) {

        if (!StringUtil.isBlank(fileName) && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        }

        return null;
    }

    public static String getFileName(final String fileName) {

        if (!StringUtil.isBlank(fileName)) {
            if (fileName.contains(".")) {
                return fileName.substring(0, fileName.lastIndexOf('.'));
            } else {
                return fileName;
            }
        }

        return null;
    }
}
