package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.quran.edition.Edition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ayah implements Serializable {
    private final short number;
    private final String text;
    @JsonProperty("audio")
    private final String audioUrl;
    @JsonProperty("audioSecondary")
    private final String[] secondaryAudioUrls;
    private final Edition edition;
    private final Surah surah;
    private final short numberInSurah;
    @JsonProperty("juz")
    private final short juzNumber;
    @JsonProperty("manzil")
    private final short manzilNumber;
    @JsonProperty("page")
    private final short pageNumber;
    @JsonProperty("ruku")
    private final short rukuNumber;
    private final short hizbQuarter;
    private final boolean sajdah;

    // For JSON deserialization
    public Ayah() {
        this.number = 0;
        this.text = null;
        this.audioUrl = null;
        this.secondaryAudioUrls = null;
        this.edition = null;
        this.surah = null;
        this.numberInSurah = 0;
        this.juzNumber = 0;
        this.manzilNumber = 0;
        this.pageNumber = 0;
        this.rukuNumber = 0;
        this.hizbQuarter = 0;
        this.sajdah = false;
    }

    /**
     * This function returns the number of the ayah in the Quran.
     *
     * @return The number of the ayah in the Quran.
     */
    public short getNumber() {
        return number;
    }

    /**
     * This function returns the text of the ayah.
     *
     * @return The text of the ayah.
     */
    public String getText() {
        return text;
    }

    /**
     * This function returns the edition of the ayah.
     *
     * @return The edition of the ayah.
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     * This function returns the main audio file url of the ayah, null if there is no audio.
     *
     * @return The main audio file url of the ayah, null if there is no audio.
     */
    public String getAudioUrl() {
        return audioUrl;
    }

    /**
     * This function returns the secondary audio URLs array of the ayah, null if there is no audio.
     * Secondary audio URLs are used for the audio of the ayah in the different resolutions (128, 64).
     *
     * @return The secondary audio URLs array of the ayah, null if there is no audio.
     */
    public String[] getSecondaryAudioUrls() {
        return secondaryAudioUrls;
    }

    /**
     * This function returns the surah object, maybe returns null if the ayah is part of surah
     *
     * @return The surah object is being returned.
     */
    public Surah getSurah() {
        return surah;
    }

    /**
     * This function returns the number of the ayah in the surah.
     *
     * @return The number of the ayah in the surah.
     */
    public short getNumberInSurah() {
        return numberInSurah;
    }

    /**
     * This function returns the juz number of the ayah.
     *
     * @return The juz number of the ayah.
     */
    public short getJuzNumber() {
        return juzNumber;
    }

    /**
     * This function returns the manzil number of the ayah.
     *
     * @return The manzil number of the ayah.
     */
    public short getManzilNumber() {
        return manzilNumber;
    }

    /**
     * This function returns the page number of the ayah.
     *
     * @return The page number of the ayah.
     */
    public short getPageNumber() {
        return pageNumber;
    }

    /**
     * This function returns the ruku number of the ayah.
     *
     * @return The ruku number of the ayah.
     */
    public short getRukuNumber() {
        return rukuNumber;
    }

    /**
     * > This function returns the hizb quarter number of the ayah.
     *
     * @return The hizb quarter number of the ayah.
     */
    public short getHizbQuarter() {
        return hizbQuarter;
    }

    /**
     * This function returns a boolean value indicating whether the ayah is sajdah or not.
     *
     * @return The boolean value indicating whether the ayah is sajdah or not.
     */
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
                ", juz=" + juzNumber +
                ", manzil=" + manzilNumber +
                ", page=" + pageNumber +
                ", ruku=" + rukuNumber +
                ", hizbQuarter=" + hizbQuarter +
                ", sajdah=" + sajdah +
                '}';
    }
}
