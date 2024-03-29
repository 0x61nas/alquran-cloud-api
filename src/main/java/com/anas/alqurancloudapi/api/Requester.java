package com.anas.alqurancloudapi.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * It sends a request to the API and saves the response to a file
 */
public class Requester {
    private static final String BASE_URL;
    private static final int connectTimeout;

    static {
        BASE_URL = "http://api.alquran.cloud/v1/";
        connectTimeout = 10000;
    }

    /**
     * It sends a GET request to the API, remove the ignored properties, and saves the response to a file and returns it.
     *
     * @param endPoint The endpoint to send the request to.
     * @return A input stream of the response.
     * @throws IOException If an error occurs while sending the request or saving the response to a file.
     */
    public static InputStream sendRequest(final String endPoint) throws IOException {
        final var connection = (HttpURLConnection) new URL(BASE_URL + endPoint).openConnection();
        connection.setConnectTimeout(connectTimeout);
        connection.setRequestMethod("GET");

        // Skip the limit number of connections n_a
        byte i = 0;
        do {
            connection.connect();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        } while (connection.getResponseCode() == 429 && i++ < 3);

        // It checks if the response code is not 200, then it throws an exception.
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error: " + connection.getResponseCode());
        }

        // read the response
        final var sb = new StringBuilder();
        final var reader = connection.getInputStream();
        int c;
        while ((c = reader.read()) != -1) {
            sb.append((char) c);
        }
        reader.close();
        var response = sb.toString();
        // It removes the ignored properties from the response.
        response = response.substring(response.indexOf("\"data\":") + 7, response.length() - 1);

        return new ByteArrayInputStream(response.getBytes());
    }

    /**
     * > This function returns the base URL of the API
     *
     * @return The base URL of the API.
     */
    public static String getBaseUrl() {
        return BASE_URL;
    }
}
