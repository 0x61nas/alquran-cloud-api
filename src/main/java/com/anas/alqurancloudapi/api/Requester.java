package com.anas.alqurancloudapi.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Requester {
    private static final String BASE_URL;
    private static final int connectTimeout;

    static {
        BASE_URL = "http://api.alquran.cloud/";
        connectTimeout = 10000;
    }

    public static File sendRequest(final String endPoint) throws IOException {
        final var connection = (HttpURLConnection) new URL(BASE_URL + endPoint).openConnection();
        connection.setConnectTimeout(connectTimeout);
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error: " + connection.getResponseCode());
        }

        final var sb = new StringBuilder();
        final var reader = connection.getInputStream();
        int c;
        while ((c = reader.read()) != -1) {
            sb.append((char) c);
        }
        reader.close();
        var response = sb.toString();
        response = response.substring(response.indexOf("\"data\":") + 7, response.length() - 1);

        final var file = CacheHelper.getCacheFile(endPoint);
        final var writer = new FileWriter(file);
        writer.write(response);
        writer.close();

        return file;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
