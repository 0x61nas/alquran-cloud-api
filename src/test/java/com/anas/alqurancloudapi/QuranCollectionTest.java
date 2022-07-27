package com.anas.alqurancloudapi;

import com.anas.alqurancloudapi.edition.Edition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author: <a href="https://github.com/anas-elgarhy">Anas Elgarhy</a>
 * @date: 7/27/22
 */
class QuranCollectionTest {
    private final static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(QuranCollectionTest.class.getName());
    }

    @Test
    @DisplayName("Test the get page with specific number and edition")
    void testGetPageWithSpecificNumberAndEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var page = QuranCollection.getPage(1, new Edition("ar.alafasy"));
            Assertions.assertNotNull(page);
            Assertions.assertNotNull(page.getAyahs());
            Assertions.assertNotNull(page.getSurahs());
            LOGGER.info("Ayahs in the page: " + Arrays.toString(page.getAyahs()));
            LOGGER.info("Surahs in the page: " + page.getSurahs());
        });
    }

    @Test
    @DisplayName("Test the get page with specific number")
    void testGetPage() {
        Assertions.assertDoesNotThrow(() -> {
            final var page = QuranCollection.getPage(1);
            Assertions.assertNotNull(page);
            Assertions.assertNotNull(page.getAyahs());
            Assertions.assertNotNull(page.getSurahs());
            LOGGER.info("Ayahs in the page: " + Arrays.toString(page.getAyahs()));
            LOGGER.info("Surahs in the page: " + page.getSurahs());
        });
    }

    @Test
    @DisplayName("Test the get page with specific number and edition identifier")
    void testGetPageWithSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var page = QuranCollection.getPage(1, "ar.alafasy");
            Assertions.assertNotNull(page);
            Assertions.assertNotNull(page.getAyahs());
            Assertions.assertNotNull(page.getSurahs());
            LOGGER.info("Ayahs in the page: " + Arrays.toString(page.getAyahs()));
            LOGGER.info("Surahs in the page: " + page.getSurahs());
        });
    }

    @Test
    @DisplayName("Test the get juz with specific number and edition and offset and limit")
    void testGetJuzWithSpecificEditionAndOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var juz = QuranCollection.getJuz(1, new Edition("ar.alafasy"), 0, 10);
            Assertions.assertNotNull(juz);
            Assertions.assertNotNull(juz.getAyahs());
            Assertions.assertNotNull(juz.getSurahs());
            LOGGER.info("Ayahs in the juz: " + Arrays.toString(juz.getAyahs()));
            LOGGER.info("Surahs in the juz: " + juz.getSurahs());
        });
    }

    @Test
    @DisplayName("Test the get juz with specific number and edition identifier and offset and limit")
    void testGetJuzWithSpecificEditionIdentifierAndOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var juz = QuranCollection.getJuz(1, "ar.alafasy", 0, 10);
            Assertions.assertNotNull(juz);
            Assertions.assertNotNull(juz.getAyahs());
            Assertions.assertNotNull(juz.getSurahs());
            LOGGER.info("Ayahs in the juz: " + Arrays.toString(juz.getAyahs()));
            LOGGER.info("Surahs in the juz: " + juz.getSurahs());
        });
    }

    @Test
    @DisplayName("Test the get juz with specific number and edition")
    void testGetJuzWithSpecificEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var juz = QuranCollection.getJuz(1, new Edition("ar.alafasy"));
            Assertions.assertNotNull(juz);
            Assertions.assertNotNull(juz.getAyahs());
            Assertions.assertNotNull(juz.getSurahs());
            LOGGER.info("Ayahs in the juz: " + Arrays.toString(juz.getAyahs()));
            LOGGER.info("Surahs in the juz: " + juz.getSurahs());
        });
    }

    @Test
    @DisplayName("Test the get juz with specific number and edition identifier")
    void testGetJuzWithSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var juz = QuranCollection.getJuz(1, "ar.alafasy");
            Assertions.assertNotNull(juz);
            Assertions.assertNotNull(juz.getAyahs());
            Assertions.assertNotNull(juz.getSurahs());
            LOGGER.info("Ayahs in the juz: " + Arrays.toString(juz.getAyahs()));
            LOGGER.info("Surahs in the juz: " + juz.getSurahs());
        });
    }

    @Test
    @DisplayName("Test the get juz with specific number")
    void testGetJuz() {
        Assertions.assertDoesNotThrow(() -> {
            final var juz = QuranCollection.getJuz(1);
            Assertions.assertNotNull(juz);
            Assertions.assertNotNull(juz.getAyahs());
            Assertions.assertNotNull(juz.getSurahs());
            LOGGER.info("Ayahs in the juz: " + Arrays.toString(juz.getAyahs()));
            LOGGER.info("Surahs in the juz: " + juz.getSurahs());
        });
    }
}