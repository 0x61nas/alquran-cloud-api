package com.anas.alqurancloudapi;

import com.anas.alqurancloudapi.api.Requester;
import com.anas.alqurancloudapi.consts.Surahs;
import com.anas.alqurancloudapi.quran.Ayah;
import com.anas.alqurancloudapi.consts.Constants;
import com.anas.alqurancloudapi.quran.QuranCollection;
import com.anas.alqurancloudapi.quran.Surah;
import com.anas.alqurancloudapi.quran.edition.Edition;
import com.anas.alqurancloudapi.quran.edition.EditionFormat;
import com.anas.alqurancloudapi.quran.edition.EditionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class QuranAPI {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    private QuranAPI() {
    }

    /**
     * Get all available editions from the Quran for a given language and edition format(text or audio) and edition type.
     *
     * @param format   The format of the edition (text or audio).
     * @param language The language of the edition. Must be a 2 character language code, e.g. en, ar, fr, etc.
     * @param type     The type of edition you want to get.
     * @return An array containing all available editions.
     * @throws IOException If an error occurs while communicating with the API, or if an error in argument is passed.
     */
    public static Edition[] getEditions(final EditionFormat format,
                                        final String language,
                                        final EditionType type) throws IOException {
        if (language != null && (language.length() != 2)) {
            throw new IllegalArgumentException("Language must be 2 characters long");
        }
        final var jsonFile = Requester.sendRequest("edition" +
                (format != null ? "?format=" + format.name().toLowerCase() : "") +
                (type != null ? (format != null ? "&" : "?") + "type=" + type.name().toLowerCase() : "") +
                (language != null ? (format != null || type != null ? "&" : "?") + "language=" + language : ""));
        final var editions = mapper.readValue(jsonFile, Edition[].class);
        jsonFile.delete();
        return editions;
    }

    /**
     * Get all editions of a given format and type.
     *
     * @param format The format of the edition (text or audio).
     * @param type   The type of edition you want to get.
     * @return An array containing all available editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition[] getEditions(final EditionFormat format,
                                        final EditionType type) throws IOException {
        return getEditions(format, null, type);
    }

    /**
     * Get all editions of a given format.
     *
     * @param format The format of the edition (text or audio).
     * @return An array containing all available editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition[] getEditions(final EditionFormat format) throws IOException {
        return getEditions(format, null, null);
    }

    /**
     * Get all editions of a given format and type.
     *
     * @param language The language of the edition. Must be a 2 character language code, e.g. en, ar, fr, etc.
     * @return An array containing all available editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition[] getEditions(final String language) throws IOException {
        return getEditions(null, language, null);
    }

    /**
     * Get all editions of a given format and type.
     *
     * @param type The type of edition you want to get.
     * @return An array containing all available editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition[] getEditions(final EditionType type) throws IOException {
        return getEditions(null, type);
    }

    /**
     * This function returns an array of all available editions.
     *
     * @return An array of Edition objects.
     */
    public static Edition[] getEditions() throws IOException {
        return getEditions((EditionType) null);
    }

    /**
     * It returns an array of all available languages.
     *
     * @return An array of strings containing all the languages of the editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static String[] getAllEditionsLanguages() throws IOException {
        final var jsonFile = Requester.sendRequest("edition/language");
        final var languages = mapper.readValue(jsonFile, String[].class);
        jsonFile.delete();
        return languages;
    }

    /**
     * It returns an random language from the list of all available languages.
     *
     * @return A random language from the list of all languages.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static String getRandomLanguage() throws IOException {
        final var allLanguages = getAllEditionsLanguages();
        return allLanguages[(int) (Math.random() * allLanguages.length)];
    }

    /**
     * It returns a random edition from the list of all available editions, with a given format and language and type.
     * <b>The parameters are optional.</b>
     *
     * @param format      The format of the edition (text or audio).
     * @param language    The language of the edition. Must be a 2 character language code, e.g. en, ar, fr, etc.
     * @param editionType The type of edition you want to get.
     * @return A random edition from the list of all editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition getRandomEdition(final EditionFormat format,
                                           final String language,
                                           final EditionType editionType) throws IOException {
        final var allEditions = getEditions(format, language, editionType);
        return allEditions[(int) (Math.random() * allEditions.length)];
    }

    /**
     * It returns a random edition from the list of all available editions, with a given format and language.
     *
     * @param format   The format of the edition you want to get (text or audio).
     * @param language The language of the edition you want to get (2 character language code, e.g. en, ar, fr, etc.).
     * @return A random edition from the list of all editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition getRandomEdition(final EditionFormat format,
                                           final String language) throws IOException {
        return getRandomEdition(format, language, null);
    }

    /**
     * It returns a random edition from the list of all available editions, with a given format.
     *
     * @param format The format of the edition you want to get (text or audio).
     * @return A random edition from the list of all editions.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition getRandomEdition(final EditionFormat format) throws IOException {
        return getRandomEdition(format, null, null);
    }

    /**
     * It returns a random edition from the list of all available editions with a given language.
     * :"""
     *
     * @param language The language of the edition you want to get (2 character language code, e.g. en, ar, fr, etc.).
     * @return A random edition from the list of all editions.
     */
    public static Edition getRandomEdition(String language) throws IOException {
        return getRandomEdition(null, language, null);
    }

    /**
     * It returns a random edition from the list of all available editions.
     *
     * @return A random edition of the book.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static Edition getRandomEdition() throws IOException {
        return getRandomEdition(null, null, null);
    }

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

        final var jsonFile = Requester.sendRequest("surah/" + surahNumber +
                (edition != null ? "/" + edition.getIdentifier() : "") +
                (offset > -1 ? "?offset=" + offset : "") +
                (limit > -1 ? ((offset > -1 ? "&" : "?") + "limit=") + limit : ""));
        final var o = mapper.readValue(jsonFile, Surah.class);
        // It deletes the temporary file that was created by the `Requester` class.
        jsonFile.delete();
        return o;   // Surah object
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
        final var jsonFile = Requester.sendRequest("ayah/" + ayahNumber
                + (edition != null ? "/" + edition.getIdentifier() : ""));
        final var o = mapper.readValue(jsonFile, Ayah.class);
        // It deletes the temporary file that was created by the `Requester` class.
        jsonFile.delete();
        return o;   // Ayah object
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

    /**
     * It returns a `Page` object that contains the information of the page number that was passed as an argument
     *
     * @param pageNumber The page number of the Quran.
     * @param edition    The edition of the Quran you want to get the page from.
     * @return A Quran collection object that contains the information of the page.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static QuranCollection getPage(final int pageNumber, final Edition edition) throws IOException {
        // Checking if the surah number is valid.
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page number must be greater than 0");
        }
        final var jsonFile = Requester.sendRequest("page/" + pageNumber
                + (edition != null ? "/" + edition.getIdentifier() : ""));
        final var o = mapper.readValue(jsonFile, QuranCollection.class);
        // It deletes the temporary file that was created by the `Requester` class.
        jsonFile.delete();
        return o;   // Page object
    }

    /**
     * This function returns a page from the Quran for the given page number and arabic edition
     *
     * @param pageNumber The page number of the page you want to get.
     * @return A Quran collection object that contains the information of the page.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static QuranCollection getPage(final int pageNumber) throws IOException {
        return getPage(pageNumber, (Edition) null);
    }

    /**
     * "This function returns a Page object for the given page number and edition."
     *
     * @param pageNumber        The page number of the page you want to get.
     * @param editionIdentifier The identifier of the edition you want to get the page from.
     * @return A Quran collection object that contains the information of the page.
     * @throws IOException If an error occurs while communicating with the API.
     */
    public static QuranCollection getPage(final int pageNumber, final String editionIdentifier) throws IOException {
        return getPage(pageNumber, new Edition(editionIdentifier));
    }

    /*public static Page getRandomPage(final Edition edition) throws IOException {
        return getPageNumber((int) (Math.random() * edition.getPagesNumber()), edition);
    }*/

    /**
     * It returns a `QuranCollection` object that contains a list of `Ayah` and `Surah` objects that are in the juz
     *
     * @param juzNumber The juz number that you want to get must be between 1 and 30.
     * @param edition   The edition of the Quran you want to get the juz from.
     * @param offset    the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit     the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify a limit.
     * @return A `QuranCollection` object contains the juz information.
     */
    public static QuranCollection getJuz(final int juzNumber,
                                         final Edition edition,
                                         final int offset,
                                         final int limit) throws IOException {
        // Checking if the surah number is valid.
        if (juzNumber < 1 || juzNumber > 30) {
            throw new IllegalArgumentException("Juz number must be greater than 0 and less than 31");
        }
        final var jsonFile = Requester.sendRequest("juz/" + juzNumber +
                (edition != null ? "/" + edition.getIdentifier() : "") +
                (offset > -1 ? "?offset=" + offset : "") +
                (limit > -1 ? ((offset > -1 ? "&" : "?") + "limit=" + limit) : ""));
        final var o = mapper.readValue(jsonFile, QuranCollection.class);
        // It deletes the temporary file that was created by the `Requester` class.
        jsonFile.delete();
        return o;   // Page object
    }

    /**
     * It returns a `QuranCollection` object that contains a list of `Ayah` and `Surah` objects that are in the juz
     *
     * @param juzNumber The juz number that you want to get must be between 1 and 30.
     * @param editionIdentifier The identifier of the edition you want to get the juz from.
     * @param offset    the number of start (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify an offset.
     * @param limit     the number of end (ayah number - 1), if you want to get a range of ayahs, -1 if you don't want to specify a limit.
     * @return A `QuranCollection` object contains the juz information.
     */
    public static QuranCollection getJuz(final int juzNumber,
                                         final String editionIdentifier,
                                         final int offset,
                                         final int limit) throws IOException {
        return getJuz(juzNumber, new Edition(editionIdentifier), offset, limit);
    }

    /**
     * It returns a `QuranCollection` object that contains a list of `Ayah` and `Surah` objects that are in the juz
     *
     * @param juzNumber The juz number that you want to get must be between 1 and 30.
     * @param edition The edition of the Quran you want to get the juz from.
     * @return A `QuranCollection` object contains the juz information.
     */
    public static QuranCollection getJuz(final int juzNumber,
                                         final Edition edition) throws IOException {
        return getJuz(juzNumber, edition, -1, -1);
    }

    /**
     * It returns a `QuranCollection` object that contains a list of `Ayah` and `Surah` objects that are in the juz
     *
     * @param juzNumber The juz number that you want to get must be between 1 and 30.
     * @param editionIdentifier The identifier of the edition you want to get the juz from.
     * @return A `QuranCollection` object contains the juz information.
     */
    public static QuranCollection getJuz(final int juzNumber,
                                         final String editionIdentifier) throws IOException {
        return getJuz(juzNumber, new Edition(editionIdentifier));
    }

    /**
     * It returns a `QuranCollection` object that contains a list of `Ayah` and `Surah` objects that are in the juz
     *
     * @param juzNumber The juz number that you want to get must be between 1 and 30.
     * @return A `QuranCollection` object contains the juz information.
     */
    public static QuranCollection getJuz(final int juzNumber) throws IOException {
        return getJuz(juzNumber, (Edition) null);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class SearchResult {
        public Ayah[] matches;

        public SearchResult() {
            matches = null;
        }

        public Ayah[] getMatches() {
            return matches;
        }
    }

    /**
     * Search for a word or phrase in the Quran in the specific edition and specific surah.
     *
     * @param keyword     The keyword or phrase you want to search for.
     * @param surahNumber The surah number to search in. If you want to search in all surahs, set this to -1.
     * @param edition     The edition of the Quran you want to search in, default is english edition.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public static Ayah[] search(final String keyword,
                                final int surahNumber,
                                final Edition edition) throws IOException {
        // Checking if the keyword is null or empty.
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        final var jsonFile = Requester.sendRequest("search/" +
                keyword.replace(" ", "%20") + "/" +
                (surahNumber - 1 > -1 ? surahNumber : "all") + "/" +
                (edition != null ? "/" + edition.getIdentifier() : "en"));
        final var o = mapper.readValue(jsonFile, SearchResult.class);
        // It deletes the temporary file that was created by the `Requester` class.
        jsonFile.delete();
        return o.matches;
    }

    /**
     * Search for a keyword or phrase in the Quran in the specific edition and specific surah.
     *
     * @param keyword The keyword or phrase you want to search for.
     * @param surah   The surah number you want to search in.
     * @param edition The edition of the Quran you want to search in.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public static Ayah[] search(final String keyword,
                                final Surah surah,
                                final Edition edition) throws IOException {
        return search(keyword, surah.getNumber(), edition);
    }

    /**
     * Search for a keyword in a specific surah
     *
     * @param keyword     The keyword to search for.
     * @param surahNumber The surah number to search in.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public static Ayah[] search(final String keyword,
                                final int surahNumber) throws IOException {
        return search(keyword, surahNumber, (Edition) null);
    }

    /**
     * Search for a keyword in a specific surah
     *
     * @param keyword The keyword to search for.
     * @param surah   The surah you want to search in.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public static Ayah[] search(final String keyword,
                                final Surah surah) throws IOException {
        return search(keyword, surah.getNumber());
    }

    /**
     * This function searches the in the Quran for a keyword or phrase in english edition.
     *
     * @param keyword The keyword to search for.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public static Ayah[] search(final String keyword) throws IOException {
        return search(keyword, -1);
    }

    /**
     * Search for a keyword in a specific surah of a specific edition
     *
     * @param keyword           The keyword to search for.
     * @param surahNumber       The surah number to search in. If you want to search in all surahs, pass in 0.
     * @param editionIdentifier The identifier of the edition you want to search in.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public static Ayah[] search(final String keyword,
                                final int surahNumber,
                                final String editionIdentifier) throws IOException {
        return search(keyword, surahNumber, new Edition(editionIdentifier));
    }

    /**
     * Search for a keyword in a specific surah of a specific edition
     *
     * @param keyword           The keyword to search for.
     * @param surah             The surah you want to search in.
     * @param editionIdentifier The identifier of the edition you want to search in.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public static Ayah[] search(final String keyword,
                                final Surah surah,
                                final String editionIdentifier) throws IOException {
        return search(keyword, surah.getNumber(), new Edition(editionIdentifier));
    }
}
