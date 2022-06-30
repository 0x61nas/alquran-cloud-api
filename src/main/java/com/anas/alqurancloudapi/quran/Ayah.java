package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.quran.edition.Edition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ayah {
    private final short number;
    private final String text;
    @JsonProperty("audio")
    private final String audioUrl;
    @JsonProperty("audioSecondary")
    private final String[] secondaryAudioUrls;
    private final Edition edition;
    private final Surah surah;
    private final short numberInSurah;
    private final short juz;
    private final short manzil;
    private final short page;
    private final short ruku;
    private final short hizbQuarter;
    private final boolean sajdah;

    private Ayah() {
        this.number = 0;
        this.text = null;
        this.audioUrl = null;
        this.secondaryAudioUrls = null;
        this.edition = null;
        this.surah = null;
        this.numberInSurah = 0;
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

    public String getAudioUrl() {
        return audioUrl;
    }

    public String[] getSecondaryAudioUrls() {
        return secondaryAudioUrls;
    }

    public Surah getSurah() {
        return surah;
    }

    public short getNumberInSurah() {
        return numberInSurah;
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

    @Override
    public String toString() {
        return "Ayah{" +
                "number=" + number +
                ", text='" + text + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", secondaryAudioUrls=" + Arrays.toString(secondaryAudioUrls) +
                ", edition=" + edition +
                ", surah=" + surah +
                ", numberOfSurah=" + numberInSurah +
                ", juz=" + juz +
                ", manzil=" + manzil +
                ", page=" + page +
                ", ruku=" + ruku +
                ", hizbQuarter=" + hizbQuarter +
                ", sajdah=" + sajdah +
                '}';
    }
}
