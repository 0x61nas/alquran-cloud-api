package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.quran.edition.Edition;

public class Page {
    private final short number;
    private final Ayah[] ayahs;
    private final Surah[] surahs;
    private final Edition edition;

    private Page() {
        this.number = 0;
        this.ayahs = new Ayah[0];
        this.surahs = new Surah[0];
        this.edition = null;
    }

    public short getNumber() {
        return number;
    }

    public Ayah[] getAyahs() {
        return ayahs;
    }

    public Surah[] getSurahs() {
        return surahs;
    }

    public Edition getEdition() {
        return edition;
    }
}
