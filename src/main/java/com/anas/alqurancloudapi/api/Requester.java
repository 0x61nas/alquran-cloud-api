package com.anas.alqurancloudapi.api;

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

    public static String sendRequest(final String endPoint) throws IOException {
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
        return sb.toString();
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Requester.sendRequest("ayah/8"));
    }

}
