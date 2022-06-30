package com.anas.alqurancloudapi.quran;

public class Surah {
    private final int number;
    private final String name;
    private final String englishName;
    private final String englishNameTranslation;
    private final RevelationType revelationType;
    private final Ayah[] ayahs;
    public static final Surah Unknown;

    static {
        Unknown = new Surah();
    }
    private Surah() {
        this.number = 0;
        this.name = "Unknown";
        this.englishName = "Unknown";
        this.englishNameTranslation = "Unknown";
        this.revelationType = RevelationType.values()[0];
        this.ayahs = new Ayah[0];
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }

    public RevelationType getRevelationType() {
        return revelationType;
    }

    public Ayah[] getAyahs() {
        return ayahs;
    }

    public short numberOfAyahs() {
        return (short) ayahs.length;
    }
}
