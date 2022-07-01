package com.anas.alqurancloudapi;

import com.anas.alqurancloudapi.api.Requester;
import com.anas.alqurancloudapi.quran.Ayah;
import com.anas.alqurancloudapi.quran.Constants;
import com.anas.alqurancloudapi.quran.Page;
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
     * @throws IOException If an error occurs while communicating with the API.
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
                (language != null ?  (format != null || type != null ? "&" : "?") +"language=" + language : ""));
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

    public static String[] getAllEditionsLanguages() throws IOException {
        final var jsonFile = Requester.sendRequest("edition/language");
        final var languages = mapper.readValue(jsonFile, String[].class);
        jsonFile.delete();
        return languages;
    }

    /**
     * It takes a surah number and an edition, and returns a Surah object
     *
     * @param surahNumber The number of the surah.
     * @param edition     The edition of the Quran that you want to get the surah from.
     * @return A Surah object.
     */
    public static Surah getSurah(final int surahNumber, final Edition edition) throws IOException {
        // Checking if the surah number is valid.
        if (surahNumber < 1 || surahNumber > Constants.SURAS_COUNT) {
            throw new IllegalArgumentException("Surah number must be between 1 and " + Constants.SURAS_COUNT);
        }
        final var jsonFile = Requester.sendRequest("surah/" + surahNumber
                + (edition != null ? "/" + edition.getIdentifier() : ""));
        final var o = mapper.readValue(jsonFile, Surah.class);
        // It deletes the temporary file that was created by the `Requester` class.
        jsonFile.delete();
        return o;   // Surah object
    }

    /**
     * This function returns a Surah object for the given surah number and edition
     *
     * @param surahNumber       The number of the surah you want to get.
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return A Surah object
     */
    public static Surah getSurah(final int surahNumber, final String editionIdentifier) throws IOException {
        return getSurah(surahNumber, new Edition(editionIdentifier));
    }

    /**
     * This function returns a Surah object for the given surah number in arabic edition.
     *
     * @param surahNumber The number of the surah you want to get.
     * @return A Surah object
     */
    public static Surah getSurah(final int surahNumber) throws IOException {
        return getSurah(surahNumber, (Edition) null);
    }

    /**
     * Get a random surah from the Quran
     *
     * @param edition The edition of the Quran you want to get the surah from.
     * @return A random surah from the Quran.
     */
    public static Surah getRandomSurah(final Edition edition) throws IOException {
        return getSurah((int) (Math.random() * Constants.SURAS_COUNT), edition);
    }

    /**
     * This function returns a random surah from the Quran
     *
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return A Surah object
     */
    public static Surah getRandomSurah(final String editionIdentifier) throws IOException {
        return getRandomSurah(new Edition(editionIdentifier));
    }

    /**
     * This function returns a random surah from the Quran in arabic edition
     *
     * @return A random surah from the Quran.
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
     */
    public static Ayah getAyah(final int ayahNumber, final Edition edition) throws IOException {
        // Checking if the surah number is valid.
        if (ayahNumber < 1 || ayahNumber > Constants.AYAHS_COUNT_WITH_BISMILLAH) {
            throw new IllegalArgumentException("Ayah number must be between 1 and " + Constants.AYAHS_COUNT_WITH_BISMILLAH);
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
     */
    public static Ayah getAyah(final int ayahNumber, final String editionIdentifier) throws IOException {
        return getAyah(ayahNumber, new Edition(editionIdentifier));
    }

    /**
     * This function returns an Ayah object for the given ayah number
     *
     * @param ayahNumber The ayah number you want to get, numbered from 1 to 6348.
     * @return An Ayah object
     */
    public static Ayah getAyah(final int ayahNumber) throws IOException {
        return getAyah(ayahNumber, (Edition) null);
    }

    /**
     * Get a random ayah from the Quran
     *
     * @param edition The edition of the Quran you want to get the ayah from.
     * @return A random ayah from the Quran.
     */
    public static Ayah getRandomAyah(final Edition edition) throws IOException {
        return getAyah((int) (Math.random() * Constants.AYAHS_COUNT_WITH_BISMILLAH), edition);
    }

    /**
     * This function returns a random Ayah from the Quran
     *
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return A random ayah from the Quran.
     */
    public static Ayah getRandomAyah(final String editionIdentifier) throws IOException {
        return getRandomAyah(new Edition(editionIdentifier));
    }

    /**
     * This function returns a random Ayah from the Quran
     *
     * @return A random ayah from the Quran.
     */
    public static Ayah getRandomAyah() throws IOException {
        return getRandomAyah((Edition) null);
    }


    /**
     * It returns a `Page` object that contains the information of the page number that was passed as an argument
     *
     * @param pageNumber The page number of the Quran.
     * @param edition    The edition of the Quran you want to get the page from.
     * @return A Page object.
     */
    public static Page getPage(final int pageNumber, final Edition edition) throws IOException {
        // Checking if the surah number is valid.
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page number must be greater than 0");
        }
        final var jsonFile = Requester.sendRequest("page/" + pageNumber
                + (edition != null ? "/" + edition.getIdentifier() : ""));
        final var o = mapper.readValue(jsonFile, Page.class);
        // It deletes the temporary file that was created by the `Requester` class.
        jsonFile.delete();
        return o;   // Page object
    }

    /**
     * This function returns a page from the Quran for the given page number and arabic edition
     *
     * @param pageNumber The page number of the page you want to get.
     * @return A Page object
     */
    public static Page getPage(final int pageNumber) throws IOException {
        return getPage(pageNumber, (Edition) null);
    }

    /**
     * "This function returns a Page object for the given page number and edition."
     *
     * @param pageNumber        The page number of the page you want to get.
     * @param editionIdentifier The identifier of the edition you want to get the page from.
     * @return A Page object
     */
    public static Page getPage(final int pageNumber, final String editionIdentifier) throws IOException {
        return getPage(pageNumber, new Edition(editionIdentifier));
    }

    /*public static Page getRandomPage(final Edition edition) throws IOException {
        return getPageNumber((int) (Math.random() * edition.getPagesNumber()), edition);
    }*/

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
     * @param keyword The keyword or phrase you want to search for.
     * @param surahNumber The surah number to search in. If you want to search in all surahs, set this to -1.
     * @param edition The edition of the Quran you want to search in, default is english edition.
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
     * @param surah The surah number you want to search in.
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
     * @param keyword The keyword to search for.
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
     * @param surah The surah you want to search in.
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
     * @param keyword The keyword to search for.
     * @param surahNumber The surah number to search in. If you want to search in all surahs, pass in 0.
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
     * @param keyword The keyword to search for.
     * @param surah The surah you want to search in.
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
