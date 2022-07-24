package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.quran.edition.Edition;

import java.io.Serializable;
import java.util.Map;

/**
 * It represents the page or juz of the Quran.
 */
public class QuranCollection implements Serializable {
    private final short number;
    private final Ayah[] ayahs;
    private final Map<String, Surah> surahs;
    private final Edition edition;

    // For JSON deserialization
    public QuranCollection() {
        this.number = 0;
        this.ayahs = null;
        this.surahs = null;
        this.edition = null;
    }

    /**
     * This function returns the number of this collection in the Quran.
     *
     * @return The number of this collection in the Quran.
     */
    public short getNumber() {
        return number;
    }

    /**
     * This function returns an array of Ayahs in this collection.
     *
     * @return An array of Ayah objects.
     */
    public Ayah[] getAyahs() {
        return ayahs;
    }

    /**
     * This function returns an array of Surahs in this collection.
     *
     * @return An array of Surah objects.
     */
    public Map<String, Surah> getSurahs() {
        return surahs;
    }

    /**
     * This function returns the edition of this collection.
     *
     * @return The edition of the page.
     */
    public Edition getEdition() {
        return edition;
    }
}
