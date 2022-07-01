package com.anas.alqurancloudapi.quran;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Represents a surah in the Quran.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Surah {
    private final int number;
    private final String name;
    private final String englishName;
    private final String englishNameTranslation;
    private final RevelationType revelationType;
    private final Ayah[] ayahs;

    // For JSON deserialization
    private Surah() {
        this.number = 0;
        this.name = null;
        this.englishName = null;
        this.englishNameTranslation = null;
        this.revelationType = RevelationType.values()[0];
        this.ayahs = null;
    }

    /**
     * This function returns the number of the surah in the Quran.
     *
     * @return The number of the surah in the Quran.
     */
    public int getNumber() {
        return number;
    }

    /**
     * This function returns the name of the surah, usually in Arabic but often in  the edition's language.
     *
     * @return The name of the surah.
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns the english name of the surah.
     *
     * @return The english name of the surah.
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * This function returns the english translation of the surah name.
     *
     * @return The english translation of the surah name.
     */
    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }

    /**
     * This function returns the revelation type of the surah.
     *
     * @return The revelation type of the surah.
     */
    public RevelationType getRevelationType() {
        return revelationType;
    }

    /**
     * This function returns an array containing the ayahs of the surah.
     *
     * @return An array of Ayah objects.
     */
    public Ayah[] getAyahs() {
        return ayahs;
    }

    /**
     * This function returns the number of ayahs in the surah
     *
     * @return The number of ayahs in the surah.
     */
    public int numberOfAyahs() {
        return ayahs.length;
    }

    @Override
    public String toString() {
        return "Surah{" + "number=" + number + ", name=" + name + ", englishName=" + englishName
                + ", englishNameTranslation=" + englishNameTranslation + ", revelationType=" + revelationType
                + ", ayahs=" + Arrays.toString(ayahs) + '}';
    }
}
