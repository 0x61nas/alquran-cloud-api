package com.anas.alqurancloudapi.api;

import java.io.File;
import java.io.IOException;

public class CacheHelper {
    public static File getCacheFile(String endPoint) throws IOException {
        final var preFix = endPoint.split("/")[0];
        var file = new File("." + preFix + ".json");
        while (file.exists()) {
            file = new File("." + preFix + (int) (Math.random() * 10000) + ".json");
        }
        return file;
    }
}
