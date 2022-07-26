package com.anas.alqurancloudapi.quran.edition;

import com.anas.alqurancloudapi.Mapper;
import com.anas.alqurancloudapi.api.Requester;
import com.anas.alqurancloudapi.quran.Ayah;
import com.anas.alqurancloudapi.quran.Surah;
import com.anas.alqurancloudapi.search.SearchResult;

import java.io.IOException;

/**
 * It represents an edition of the Quran
 */
public class Edition implements Mapper {
    private final String name;
    private final String identifier;
    private final String language;
    private final String englishName;
    private final EditionFormat format;
    private final EditionType type;
    private final Direction direction;

    // For JSON deserialization
    public Edition() {
        this(null);
    }

    /**
     * Constructor for the Edition class.
     * @param identifier The identifier of the edition, e.g. "ar.alafasy".
     */
    public Edition(final String identifier) {
        super();
        this.identifier = identifier;
        this.name = null;
        this.language = null;
        this.englishName = null;
        this.format = EditionFormat.values()[0];
        this.type = EditionType.values()[0];
        this.direction = Direction.values()[0];
    }

    /**
     * This function returns the original name of the edition.
     *
     * @return The name of the edition.
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns the identifier of the edition.
     *
     * @return The identifier of the edition.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * This function returns the language of the edition.
     *
     * @return The language of the edition.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This function returns the english name of the edition.
     *
     * @return The english name of the edition.
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * This function returns the format of the edition (text, audio), audio includes the audio and the text.
     *
     * @return The format of the edition.
     */
    public EditionFormat getFormat() {
        return format;
    }

    /**
     * This function returns the type of the edition (quran, translation, transliteration, tafsir).
     *
     * @return The type of the edition.
     */
    public EditionType getType() {
        return type;
    }

    /**
     * This function returns the direction of the text in the edition.
     * Right-to-left or left-to-right.
     *
     * @return The direction of the text in the edition.
     */
    public Direction getDirection() {
        return direction;
    }

    // api
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
        final var inputStream = Requester.sendRequest("edition" +
                (format != null ? "?format=" + format.name().toLowerCase() : "") +
                (type != null ? (format != null ? "&" : "?") + "type=" + type.name().toLowerCase() : "") +
                (language != null ? (format != null || type != null ? "&" : "?") + "language=" + language : ""));
        return mapper.readValue(inputStream, Edition[].class);
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
        return mapper.readValue(Requester.sendRequest("edition/language"), String[].class);
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

    // Search

    /**
     * Search for a word or phrase in the Quran in the specific edition and specific surah.
     *
     * @param keyword     The keyword or phrase you want to search for.
     * @param surahNumber The surah number to search in. If you want to search in all surahs, set this to -1.
     * @param edition     The edition of the Quran you want to search in, default is english edition.
     * @return An array of Ayah objects.
     * @throws IOException If there is an error while connecting to the server or if the server returns an error, or can't create the temporary file.
     */
    public  static Ayah[] search(final String keyword,
                                 final int surahNumber,
                                 final Edition edition) throws IOException {
        // Checking if the keyword is null or empty.
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        final var inputStream = Requester.sendRequest("search/" +
                keyword.replace(" ", "%20") + "/" +
                (surahNumber - 1 > -1 ? surahNumber : "all") + "/" +
                (edition != null ? "/" + edition.getIdentifier() : "en"));
        final var o = mapper.readValue(inputStream, SearchResult.class);
        return o.getMatches();
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

    @Override
    public String toString() {
        return "Edition{" + "name=" + name + ", identifier=" + identifier + ", language=" + language
                + ", englishName=" + englishName + ", format=" + format + ", type=" + type + ", direction=" + direction
                + '}';
    }
}
