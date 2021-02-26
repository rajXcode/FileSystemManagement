package com.technologi.techtest1.utils;/*
 * @created 22/02/2021 - 13:54
 * @project FileSystemManagement
 * @author rajender
 * rajender created on 25/02/2021 inside the package - com.technologi.techtest1.utils
 */

/**
 * @OSUtils detect operating system name
 */
public class OSUtils {
    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    }

    private static OS os = null;

    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            } else if (operSys.contains("sunos")) {
                os = OS.SOLARIS;
            }
        }
        return os;
    }
}