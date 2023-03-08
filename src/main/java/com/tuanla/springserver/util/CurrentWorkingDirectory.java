package com.tuanla.springserver.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class CurrentWorkingDirectory {
    public static void main(String[] args) {

        printCurrentWorkingDirectory1();
        printCurrentWorkingDirectory2();
        printCurrentWorkingDirectory3();
        printCurrentWorkingDirectory4();

    }

    // System Property
    public static String printCurrentWorkingDirectory1() {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
        return userDirectory;
    }

    // Path, Java 7
    public static String printCurrentWorkingDirectory2() {
        String userDirectory = Paths.get("")
                .toAbsolutePath()
                .toString();
        System.out.println(userDirectory);
        return userDirectory;
    }

    // File("")
    public static String printCurrentWorkingDirectory3() {
        String userDirectory = new File("").getAbsolutePath();
        System.out.println(userDirectory);
        return userDirectory;
    }

    // FileSystems
    public static String printCurrentWorkingDirectory4() {
        String userDirectory = FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString();
        System.out.println(userDirectory);
        return userDirectory;
    }
}
