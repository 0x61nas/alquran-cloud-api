package com.anas.alqurancloudapi.quran;

import com.anas.alqurancloudapi.Mapper;
import com.anas.alqurancloudapi.api.Requester;
import com.anas.alqurancloudapi.quran.edition.Edition;

import java.io.IOException;
import java.util.Map;

/**
 * It represents the page or juz of the Quran.
 */
public class QuranCollection implements Mapper {
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

    // api
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
        final var inputStream = Requester.sendRequest("page/" + pageNumber
                + (edition != null ? "/" + edition.getIdentifier() : ""));
        return mapper.readValue(inputStream, QuranCollection.class);   // Page object
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
        final var inputStream = Requester.sendRequest("juz/" + juzNumber +
                (edition != null ? "/" + edition.getIdentifier() : "") +
                (offset > -1 ? "?offset=" + offset : "") +
                (limit > -1 ? ((offset > -1 ? "&" : "?") + "limit=" + limit) : ""));
        return mapper.readValue(inputStream, QuranCollection.class);   // Page object
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
}
