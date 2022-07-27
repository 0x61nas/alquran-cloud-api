package com.anas.alqurancloudapi;

import com.anas.alqurancloudapi.api.Requester;
import com.anas.alqurancloudapi.consts.Constants;
import com.anas.alqurancloudapi.consts.Surahs;
import com.anas.alqurancloudapi.edition.Edition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a surah in the Quran.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Surah implements Mapper {
    private final int number;
    private final String name;
    private final String englishName;
    private final String englishNameTranslation;
    private final RevelationType revelationType;
    private final Ayah[] ayahs;

    // For JSON deserialization
    public Surah() {
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

    // api
    /**
     * It takes a surah number and an edition, and returns a Surah object
     *
     * @param surahNumber The number of the surah.
     * @param edition     The edition of the Quran that you want to get the surah from.
     * @param offset      the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit       the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an limit.
     * @return A Surah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final int surahNumber,
                                 final Edition edition,
                                 final int offset,
                                 final int limit) throws IOException {
        // Checking if the surah number is valid.
        if (surahNumber < 1 || surahNumber > Constants.SURAS_COUNT) {
            throw new IllegalArgumentException("Surah number must be between 1 and " + Constants.SURAS_COUNT);
        }
        if (offset > Surahs.values()[surahNumber - 1].getNumberOfAyahs() - 1) {
            throw new IllegalArgumentException("Offset must be between 0 and " +
                    (Surahs.values()[surahNumber - 1].getNumberOfAyahs() - 1));
        }

        final var inputStream = Requester.sendRequest("surah/" + surahNumber +
                (edition != null ? "/" + edition.getIdentifier() : "") +
                (offset > -1 ? "?offset=" + offset : "") +
                (limit > -1 ? ((offset > -1 ? "&" : "?") + "limit=") + limit : ""));
        return mapper.readValue(inputStream, Surah.class);   // Surah object
    }

    /**
     * It takes a surah number and an edition and offset and limit and returns a Surah object.
     *
     * @param surahNumber       The number of the surah.
     * @param editionIdentifier The edition identifier of the edition that you want to get the surah from.
     * @param offset            the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit             the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an limit.
     * @return A Surah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final int surahNumber,
                                 final String editionIdentifier,
                                 final int offset,
                                 final int limit) throws IOException {
        return getSurah(surahNumber, new Edition(editionIdentifier), offset, limit);
    }

    /**
     * It takes a surah number and an edition, and returns a Surah object
     *
     * @param surahNumber The number of the surah.
     * @param edition The edition that you want to get the surah from.
     * @return A Surah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final int surahNumber,
                                 final Edition edition) throws IOException {
        return getSurah(surahNumber, edition, -1, -1);
    }

    /**
     * It takes a surah number and an edition, and returns a Surah object
     *
     * @param surahNumber The number of the surah.
     * @param editionIdentifier The edition identifier of the edition that you want to get the surah from.
     * @return A Surah object.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final int surahNumber,
                                 final String editionIdentifier) throws IOException {
        return getSurah(surahNumber, editionIdentifier, -1, -1);
    }

    /**
     * This function returns a Surah object for the given surah number and offset and limit, in default edition (arabic).
     *
     * @param surahNumber The number of the surah you want to get.
     * @param offset      the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit       the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify a limit.
     * @return A Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final int surahNumber,
                                 final int offset,
                                 final int limit) throws IOException {
        return getSurah(surahNumber, (Edition) null, offset, limit);
    }

    /**
     * This function returns a Surah object for the given surah number in arabic edition.
     *
     * @param surahNumber The number of the surah you want to get.
     * @return A Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final int surahNumber) throws IOException {
        return getSurah(surahNumber, -1, -1);
    }

    /**
     * This function returns a Surah object for the given surah.
     *
     * @param surah The surah you want to get.
     * @return A Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final Surahs surah) throws IOException {
        return getSurah(surah.getNumber());
    }

    /**
     * This function returns a Surah object for the given surah and edition and offset and limit.
     *
     * @param surah   The surah you want to get.
     * @param edition The edition you want to use.
     * @param offset  the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit   the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify a limit.
     * @return A Complete Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final Surahs surah,
                                 final Edition edition,
                                 final int offset,
                                 final int limit) throws IOException {
        return getSurah(surah.getNumber(), edition, offset, limit);
    }

    /**
     * This function returns a Surah object for the given surah and edition
     *
     * @param surah   The surah you want to get.
     * @param edition The edition you want to use.
     * @return A Complete Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final Surahs surah, final Edition edition) throws IOException {
        return getSurah(surah, edition, -1, -1);
    }

    /**
     * This function returns a complete Surah object for the given surah and edition identifier
     *
     * @param surah             The surah you want to get.
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return A Complete Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getSurah(final Surahs surah, final String editionIdentifier) throws IOException {
        return getSurah(surah.getNumber(), editionIdentifier);
    }


    /**
     * Get a random surah from the Quran in a given edition, and with a given offset and limit.
     *
     * @param edition The edition of the Quran you want to get the surah from.
     * @param offset  the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit   the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an limit.
     * @return A random surah from the Quran.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getRandomSurah(final Edition edition,
                                       final int offset,
                                       final int limit) throws IOException {
        if (offset > Surahs.AL_BAQARA.getNumberOfAyahs() - 1) {
            throw new IllegalArgumentException("Offset must be between 0 and " + (Surahs.AL_BAQARA.getNumberOfAyahs() - 1) + " or -1");
        }
        final var availableSurahs = Surahs.getAvailableSurahsForOffset(offset);
        return getSurah(availableSurahs[(int) (Math.random() * availableSurahs.length)], edition, offset, limit);
    }

    /**
     * Get a random surah from the Quran in a given edition.
     *
     * @param edition The edition of the Quran you want to get the surah from.
     * @return A random surah from the Quran.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getRandomSurah(final Edition edition) throws IOException {
        return getRandomSurah(edition, -1, -1);
    }

    /**
     * This function returns a random surah from the Quran in a given edition and offset and limit.
     *
     * @param editionIdentifier The identifier of the edition you want to use.
     * @param offset            the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit             the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify a limit.
     * @return A Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getRandomSurah(final String editionIdentifier,
                                       final int offset,
                                       final int limit) throws IOException {
        return getRandomSurah(new Edition(editionIdentifier), offset, limit);
    }

    /**
     * This function returns a random surah from the Quran
     *
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return A Surah object
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getRandomSurah(final String editionIdentifier) throws IOException {
        return getRandomSurah(new Edition(editionIdentifier));
    }


    /**
     * This function returns a random surah from the Quran in a  default edition (arabic), and with a given offset and limit.
     *
     * @param offset the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit  the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify a limit.
     * @return A random surah from the Quran.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getRandomSurah(final int offset, final int limit) throws IOException {
        return getRandomSurah((Edition) null, offset, limit);
    }

    /**
     * This function returns a random surah from the Quran in arabic edition
     *
     * @return A random surah from the Quran.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Surah getRandomSurah() throws IOException {
        return getRandomSurah((Edition) null);
    }

    /**
     * Search for a keyword in this surah.
     *
     * @param keyword The keyword to search for.
     * @return An array of Ayah containing the keyword.
     */
    // TODO: 7/27/22 Ignore the Arabic formation
    public Ayah[] search(final String keyword) {
        final var ayahs = new ArrayList<Ayah>();
        for (final var ayah : this.getAyahs()) {
            if (ayah.getText().contains(keyword)) {
                ayahs.add(ayah);
            }
        }
        return ayahs.toArray(new Ayah[0]);
    }

    /**
     * Get a random ayah from this surah.
     * @return random ayah from this surah.
     */
    public Ayah getRandomAyah() {
        return ayahs[(int) (Math.random() * ayahs.length)];
    }

    @Override
    public String toString() {
        return "Surah{" + "number=" + number + ", name=" + name + ", englishName=" + englishName
                + ", englishNameTranslation=" + englishNameTranslation + ", revelationType=" + revelationType
                + ", ayahs=" + Arrays.toString(ayahs) + '}';
    }
}
