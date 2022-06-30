package com.anas.alqurancloudapi;

import com.anas.alqurancloudapi.api.Requester;
import com.anas.alqurancloudapi.quran.Ayah;
import com.anas.alqurancloudapi.quran.Constants;
import com.anas.alqurancloudapi.quran.Surah;
import com.anas.alqurancloudapi.quran.edition.Edition;
import com.anas.alqurancloudapi.quran.edition.EditionFormat;
import com.anas.alqurancloudapi.quran.edition.EditionType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class QuranAPI {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }
    private QuranAPI() {
    }

    public static Edition[] getEditions(final EditionFormat format,
                                        final String language,
                                        final EditionType type) throws IOException {
        if (language != null && (language.length() != 2)) {
            throw new IllegalArgumentException("Language must be 2 characters long");
        }
        final var jsonFile = Requester.sendRequest("/edition" +
                (format != null ? "?format=" + format.name().toLowerCase() : "") +
                (type != null ? "?type=" + type.name().toLowerCase() : "") +
                (language != null ? "?language=" + language : ""));
        final var editions = mapper.readValue(jsonFile, Edition[].class);
        jsonFile.delete();
        return editions;
    }

    public static Edition[] getEditions(final EditionFormat format,
                                        final EditionType type) throws IOException {
        return getEditions(format, null, type);
    }

    public static Edition[] getEditions(final EditionFormat format) throws IOException {
        return getEditions(format, null, null);
    }

    public static Edition[] getEditions(final String language) throws IOException {
        return getEditions(null, language, null);
    }

    public static Edition[] getEditions(final EditionType type) throws IOException {
        return getEditions(null, type);
    }

    public static Edition[] getEditions() throws IOException {
        return getEditions((EditionType) null);
    }

    /**
     * It takes a surah number and an edition, and returns a Surah object
     *
     * @param surahNumber The number of the surah.
     * @param edition The edition of the Quran that you want to get the surah from.
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
     * > This function returns a Surah object for the given surah number and edition
     *
     * @param surahNumber The number of the surah you want to get.
     * @param editionIdentifier The identifier of the edition you want to use.
     * @return A Surah object
     */
    public static Surah getSurah(final int surahNumber, final String editionIdentifier) throws IOException {
        return getSurah(surahNumber, new Edition(editionIdentifier));
    }

    public static Surah getSurah(final int surahNumber) throws IOException {
        return getSurah(surahNumber, (Edition) null);
    }

    public static Surah getRandomSurah(final Edition edition) throws IOException {
        return getSurah((int) (Math.random() * Constants.SURAS_COUNT), edition);
    }

    public static Surah getRandomSurah(final String editionIdentifier) throws IOException {
        return getRandomSurah(new Edition(editionIdentifier));
    }

    public static Surah getRandomSurah() throws IOException {
        return getRandomSurah((Edition) null);
    }

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

    public static Ayah getAyah(final int ayahNumber, final String editionIdentifier) throws IOException {
        return getAyah(ayahNumber, new Edition(editionIdentifier));
    }

    public static Ayah getAyah(final int ayahNumber) throws IOException {
        return getAyah(ayahNumber, (Edition) null);
    }

    public static Ayah getRandomAyah(final Edition edition) throws IOException {
        return getAyah((int) (Math.random() * Constants.AYAHS_COUNT_WITH_BISMILLAH), edition);
    }

    public static Ayah getRandomAyah(final String editionIdentifier) throws IOException {
        return getRandomAyah(new Edition(editionIdentifier));
    }

    public static Ayah getRandomAyah() throws IOException {
        return getRandomAyah((Edition) null);
    }
}
