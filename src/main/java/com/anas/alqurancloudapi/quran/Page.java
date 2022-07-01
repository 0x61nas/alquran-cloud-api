package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.quran.edition.Edition;

/**
 * It represents the page of the Quran.
 */
public class Page {
    private final short number;
    private final Ayah[] ayahs;
    private final Surah[] surahs;
    private final Edition edition;

    // For JSON deserialization
    private Page() {
        this.number = 0;
        this.ayahs = null;
        this.surahs = null;
        this.edition = null;
    }

    /**
     * This function returns the number of the page in the Quran.
     *
     * @return The number of the page in the Quran.
     */
    public short getNumber() {
        return number;
    }

    /**
     * This function returns an array of Ayahs in the page.
     *
     * @return An array of Ayah objects.
     */
    public Ayah[] getAyahs() {
        return ayahs;
    }

    /**
     * This function returns an array of Surahs in the page.
     *
     * @return An array of Surah objects.
     */
    public Surah[] getSurahs() {
        return surahs;
    }

    /**
     * This function returns the edition of the page.
     *
     * @return The edition of the page.
     */
    public Edition getEdition() {
        return edition;
    }
}
