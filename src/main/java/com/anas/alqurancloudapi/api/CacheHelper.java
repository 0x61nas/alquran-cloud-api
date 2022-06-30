package com.anas.alqurancloudapi.api;

import java.io.File;
import java.io.IOException;

public class CacheHelper {
    public static File getCacheFile(String endPoint) throws IOException {
        var file = new File(endPoint.split("/")[0] + ".json");
        while (file.exists()) {
            file = new File(endPoint + (Math.random() * 10000) + ".json");
        }
        return file;
    }
}
