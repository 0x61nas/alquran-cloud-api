package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.Mapper;
import com.anas.alqurancloudapi.api.Requester;
import com.anas.alqurancloudapi.consts.Constants;
import com.anas.alqurancloudapi.consts.Surahs;
import com.anas.alqurancloudapi.quran.edition.Edition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ayah implements Mapper {
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

    // api
    /**
     * It takes an ayah number and an edition (optional) and returns an Ayah object
     *
     * @param ayahNumber The number of the ayah, numbered from 1 to 6348.
     * @param edition    The edition of the Quran that you want to get the ayah from.
     * @return An Ayah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getAyah(final int ayahNumber, final Edition edition) throws IOException {
        // Checking if the surah number is valid.
        if (ayahNumber < 1 || ayahNumber > Constants.AYAHS_COUNT) {
            throw new IllegalArgumentException("Ayah number must be between 1 and " + Constants.AYAHS_COUNT);
        }
        final var inputStream = Requester.sendRequest("ayah/" + ayahNumber
                + (edition != null ? "/" + edition.getIdentifier() : ""));
        return mapper.readValue(inputStream, Ayah.class);   // Ayah object
    }

    /**
     * This function returns an Ayah object for the given ayah number and edition
     *
     * @param ayahNumber        The ayah number you want to get, numbered from 1 to 6348.
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return An Ayah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getAyah(final int ayahNumber, final String editionIdentifier) throws IOException {
        return getAyah(ayahNumber, new Edition(editionIdentifier));
    }

    /**
     * This function returns an Ayah object for the given ayah number
     *
     * @param ayahNumber The ayah number you want to get, numbered from 1 to 6348.
     * @return An Ayah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getAyah(final int ayahNumber) throws IOException {
        return getAyah(ayahNumber, (Edition) null);
    }

    /**
     * Get a random ayah from the Quran
     *
     * @param edition The edition of the Quran you want to get the ayah from.
     * @return A random ayah from the Quran.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getRandomAyah(final Edition edition) throws IOException {
        return getAyah((int) (Math.random() * Constants.AYAHS_COUNT), edition);
    }

    /**
     * This function returns a random Ayah from the Quran
     *
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return A random ayah from the Quran.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getRandomAyah(final String editionIdentifier) throws IOException {
        return getRandomAyah(new Edition(editionIdentifier));
    }

    /**
     * This function returns a random Ayah from the Quran
     *
     * @return A random ayah from the Quran.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getRandomAyah() throws IOException {
        return getRandomAyah((Edition) null);
    }

    /**
     * This function returns an ayah from specified surah and ayah number
     *
     * @param surah      The surah you want to get the ayah from.
     * @param ayahNumber The ayah number in the surah.
     * @param edition    The edition of the Quran you want to use, (null mens default edition).
     * @return An Ayah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getAyahFromSurah(final Surahs surah,
                                        final int ayahNumber,
                                        final Edition edition) throws IOException {
        return getAyah(surah.getFactAyahNumber(ayahNumber), edition);
    }

    /**
     * This function returns an Ayah object from a Surah object, ayah number, and edition identifier
     *
     * @param surah             The surah you want to get the ayah from.
     * @param ayahNumber        The ayah number you want to get.
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return An Ayah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getAyahFromSurah(final Surahs surah,
                                        final int ayahNumber,
                                        final String editionIdentifier) throws IOException {
        return getAyahFromSurah(surah, ayahNumber, new Edition(editionIdentifier));
    }

    /**
     * Get the Ayah object from specified surah.
     *
     * @param surah      The surah you want to get the ayah from.
     * @param ayahNumber The ayah number in the surah
     * @return An Ayah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getAyahFromSurah(final Surahs surah, final int ayahNumber) throws IOException {
        return getAyah(surah.getFactAyahNumber(ayahNumber), (Edition) null);
    }

    /**
     * This function returns an Ayah object from a specified surah and edition
     *
     * @param surah   The surah you want to get the ayah from.
     * @param edition The edition of the Quran you want to use.
     * @return A random ayah from a surah.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getRandomAyahFromSurah(final Surahs surah, final Edition edition) throws IOException {
        return getAyah(surah.getFactAyahNumber((int) (Math.random() * surah.getNumberOfAyahs())), edition);
    }

    /**
     * This function returns an Ayah object from a specified surah and edition.
     *
     * @param surah             The surah you want to get a random ayah from.
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return An Ayah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getRandomAyahFromSurah(final Surahs surah, final String editionIdentifier) throws IOException {
        return getRandomAyahFromSurah(surah, new Edition(editionIdentifier));
    }

    /**
     * This function returns a random ayah from a given surah
     *
     * @param surah The surah you want to get a random ayah from.
     * @return An Ayah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Ayah getRandomAyahFromSurah(final Surahs surah) throws IOException {
        return getRandomAyahFromSurah(surah, (Edition) null);
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
