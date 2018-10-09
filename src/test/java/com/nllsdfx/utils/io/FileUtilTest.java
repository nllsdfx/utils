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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.Assert.*;

public class FileUtilTest {

    private static String tmpPath;


    @BeforeClass
    public static void before() {
        tmpPath = System.getProperty("user.home") + File.separator + "/tmp/";
    }

    @Test
    public void createDir() {

        assertFalse(FileUtil.createDir(""));
        assertTrue(FileUtil.createDir("dirTxt.txt"));
        assertTrue(FileUtil.createDir(tmpPath + "dirTxt.txt/dir"));

        FileUtil.deleteDir(tmpPath);

        assertTrue(FileUtil.createDir(tmpPath));
        // ok we can createProps dirs with dots inside the name
        assertTrue(FileUtil.createDir(tmpPath + "dirTxt.txt/dir/"));
        assertTrue(FileUtil.createDir(tmpPath + "/dirTest"));
        assertFalse(FileUtil.createDir(tmpPath + "/dirTest/"));


    }

    @Test
    public void createFile() throws Exception {

        assertNull(FileUtil.createFile(""));
        assertNull(FileUtil.createFile(null));
        assertNull(FileUtil.createFile(tmpPath));

        Path path = FileUtil.createFile(tmpPath + "fileTxt.txt");

        assertNotNull(path);
        assertTrue(Files.exists(path));
        assertNotNull(FileUtil.createFile(path.toString()));

        assertNotNull(FileUtil.createFile(tmpPath + "fileTxt"));

    }

    @Test
    public void createTMPFile() throws Exception {

        assertNull(FileUtil.createTMPFile(null));
        assertNull(FileUtil.createTMPFile(" "));

        assertNotNull(FileUtil.createTMPFile(tmpPath));
    }

    @Test
    public void isDir() throws Exception {
        assertFalse(FileUtil.isDir(""));
        assertFalse(FileUtil.isDir(null));
        assertFalse(FileUtil.isDir("dir"));
        assertFalse(FileUtil.isDir("dir.txt/"));
        assertFalse(FileUtil.isDir("txt\\dir.txt/"));

        assertTrue(FileUtil.isDir("dir/"));
        assertTrue(FileUtil.isDir("/dir/"));
        assertFalse(FileUtil.isDir("/dir"));
        assertTrue(FileUtil.isDir(System.getProperty("user.home")));
    }

    @Test
    public void deleteDir() {

        assertFalse(FileUtil.deleteDir(""));
        assertFalse(FileUtil.deleteDir(null));

        Path notExistsDir = Paths.get("/sfafasfasfasdfayfafjhafafa/");

        assertTrue(Files.notExists(notExistsDir));
        assertFalse(FileUtil.deleteDir(notExistsDir.toString()));
        assertFalse(FileUtil.deleteDir("text.txt"));
        assertFalse(FileUtil.deleteDir("text"));

        FileUtil.createDir(tmpPath);

        assertTrue(FileUtil.deleteDir(tmpPath));
        assertTrue(Files.notExists(Paths.get(tmpPath)));

    }

    @Test
    public void deleteFile() {
        assertFalse(FileUtil.deleteFile(null));
        assertFalse(FileUtil.deleteFile(""));
        assertFalse(FileUtil.deleteFile(tmpPath));

        Path path = FileUtil.createFile(tmpPath + "aaaaaaaaaa");
        assertTrue(path != null);
        assertTrue(FileUtil.createDir(tmpPath + "dirdir/"));
        assertFalse(FileUtil.deleteFile(tmpPath + "dirdir"));
        assertTrue(FileUtil.deleteFile(path.toAbsolutePath().toString()));

    }

    @Test
    public void findDir() {
        assertNull(FileUtil.findDirectory(null, null));
        assertNull(FileUtil.findDirectory(null, ""));
        assertNull(FileUtil.findDirectory("", ""));

        assert FileUtil.createDir(tmpPath + "findDir/");
        assertNotNull(FileUtil.findDirectory("findDir", tmpPath));

    }

    @Test
    public void tmpDir() {
        assertNull(FileUtil.createTMPDir(null));
        assertNull(FileUtil.createTMPDir(""));
        assertNull(FileUtil.createTMPDir("go"));

        assertNotNull(FileUtil.createTMPDir(tmpPath));
    }

    @Test
    public void copyDir() {
        assertFalse(FileUtil.copyDir(Paths.get(tmpPath), Paths.get("")));
        assertFalse(FileUtil.copyDir(null, null));
        assertTrue(FileUtil.copyDir(Paths.get(tmpPath), Paths.get(tmpPath + "dirTest")));
    }

    @AfterClass
    public static void after() throws Exception {

        Path path = Paths.get(tmpPath);

        if (Files.exists(path) && !FileUtil.deleteDir(tmpPath)) {

            System.out.println("Tmp dir was no deleted!!!");
            System.out.println("Trying to clear...");

            Files.walk(path, FileVisitOption.FOLLOW_LINKS)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .peek(System.out::println)
                    .forEach(File::delete);
        }

        assertTrue(FileUtil.deleteDir("dirTxt.txt"));

        assertTrue(Files.notExists(path));

    }

}