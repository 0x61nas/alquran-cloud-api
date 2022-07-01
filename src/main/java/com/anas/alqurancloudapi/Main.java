package com.anas.alqurancloudapi;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(final String[] args) throws IOException {
        // System.out.println(QuranAPI.getSurah(1));
        // System.out.println(QuranAPI.getAyah(2));

        // System.out.println("\n\n");
        // System.out.println(QuranAPI.getRandomSurah());
        // System.out.println(QuranAPI.getRandomSurah("en.asad"));

        System.out.println("\n\n");

        System.out.println(QuranAPI.getSurah(1, "ar.alafasy"));

        System.out.println("\n\n");

        // System.out.println(QuranAPI.getRandomAyah());

//        System.out.println("\n\n");

        // System.out.println(Arrays.toString(QuranAPI.getEditions()));

        // System.out.println(Arrays.toString(QuranAPI.search("Abraham")));
    }
}
