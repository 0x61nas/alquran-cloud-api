package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.quran.edition.Edition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ayah {
    private final short number;
    private final String text;
    private final Edition edition;
    private final Surah surah;
    private final short numberOfSurah;
    private final short juz;
    private final short manzil;
    private final short page;
    private final short ruku;
    private final short hizbQuarter;
    private final boolean sajdah;

    private Ayah() {
        this.number = 0;
        this.text = "";
        this.edition = Edition.Unknown;
        this.surah = Surah.Unknown;
        this.numberOfSurah = 0;
        this.juz = 0;
        this.manzil = 0;
        this.page = 0;
        this.ruku = 0;
        this.hizbQuarter = 0;
        this.sajdah = false;
    }

    public short getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public Edition getEdition() {
        return edition;
    }

    public Surah getSurah() {
        return surah;
    }

    public short getNumberOfSurah() {
        return numberOfSurah;
    }

    public short getJuz() {
        return juz;
    }

    public short getManzil() {
        return manzil;
    }

    public short getPage() {
        return page;
    }

    public short getRuku() {
        return ruku;
    }

    public short getHizbQuarter() {
        return hizbQuarter;
    }

    public boolean isSajdah() {
        return sajdah;
    }
}
