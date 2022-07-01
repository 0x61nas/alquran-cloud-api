package com.anas.alqurancloudapi.api;

import java.io.File;
import java.io.IOException;

public class CacheHelper {
    private CacheHelper() {
    }
    /**
     * It takes a string, splits it on the first "/" character, and then creates a file with the first part of the string
     * as the name, and the extension ".json". If the file already exists, it adds a random number to the end of the file
     * name
     *
     * @param endPoint The string to be split
     * @return A file object.
     */
    public static File getCacheFile(String endPoint) throws IOException {
        final var preFix = endPoint.split("/")[0];
        var file = new File("." + preFix + ".json");
        while (file.exists()) {
            file = new File("." + preFix + (int) (Math.random() * 10000) + ".json");
        }
        return file;
    }
}
