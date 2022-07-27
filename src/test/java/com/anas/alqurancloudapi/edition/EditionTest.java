package com.anas.alqurancloudapi.edition;

import com.anas.alqurancloudapi.consts.Surahs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author: <a href="https://github.com/anas-elgarhy">Anas Elgarhy</a>
 * @date: 7/26/22
 */
@DisplayName("Edition class tests")
class EditionTest {
    private final static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(EditionTest.class.getName());
    }

    @Test
    @DisplayName("Test the get edition with specific format and language and type")
    void testGetEditionsWithSpecificFormatAndLangAndType() {
        Assertions.assertDoesNotThrow(() -> {
            final var arabicEditions = Edition.getEditions(EditionFormat.AUDIO,
                    "ar", EditionType.VERSEBYVERSE);
            Assertions.assertNotNull(arabicEditions);
            Assertions.assertNotEquals(0, arabicEditions.length);
            LOGGER.info("Arabic editions, audio, versebyverse: " + Arrays.toString(arabicEditions));

            final var englishEditions = Edition.getEditions(EditionFormat.TEXT,
                    "en", EditionType.VERSEBYVERSE);
            Assertions.assertNotNull(englishEditions);
            Assertions.assertNotEquals(0, englishEditions.length);
            LOGGER.info("English editions, audio, versebyverse: " + Arrays.toString(englishEditions));
        });
    }

    @Test
    @DisplayName("Test the get edition with specific format and and type")
    void testGetEditionsWithSpecificFormatAndType() {
        Assertions.assertDoesNotThrow(() -> {
            final var editions = Edition.getEditions(EditionFormat.AUDIO, EditionType.VERSEBYVERSE);
            Assertions.assertNotNull(editions);
            Assertions.assertNotEquals(0, editions.length);
            LOGGER.info("Editions, audio, versebyverse: " + Arrays.toString(editions));
        });
    }

    @Test
    @DisplayName("Test the get edition with specific format")
    void testGetEditionsWithSpecificFormat() {
        Assertions.assertDoesNotThrow(() -> {
            final var editions = Edition.getEditions(EditionFormat.TEXT);
            Assertions.assertNotNull(editions);
            Assertions.assertNotEquals(0, editions.length);
            LOGGER.info("Editions, text: " + Arrays.toString(editions));
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"ar", "en", "id"})
    @DisplayName("Test the get edition with specific language")
    void testGetEditionsWithSpecificLanguage(final String language) {
        Assertions.assertDoesNotThrow(() -> {
            final var editions = Edition.getEditions(language);
            Assertions.assertNotNull(editions);
            Assertions.assertNotEquals(0, editions.length);
            LOGGER.info(language + " editions: " + Arrays.toString(editions));
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("Test the get edition with specific type")
    void testGetEditionsWithSpecificType(final int index) {
        Assertions.assertDoesNotThrow(() -> {
            final var editionType = EditionType.values()[index];
            final var varseEditions = Edition.getEditions(editionType);
            Assertions.assertNotNull(varseEditions);
            Assertions.assertNotEquals(0, varseEditions.length);
            LOGGER.info("Editions, " + editionType + " : " + Arrays.toString(varseEditions));
        });
    }

    @Test
    @DisplayName("Test the get all editions function")
    void testGetEditionsWithoutAnyFilter() {
        Assertions.assertDoesNotThrow(() -> {
            final var editions = Edition.getEditions();
            Assertions.assertNotNull(editions);
            Assertions.assertNotEquals(0, editions.length);
            LOGGER.info("All editions: " + Arrays.toString(editions));
        });
    }

    @Test
    @DisplayName("Test the get all editions languages function")
    void getAllEditionsLanguages() {
        Assertions.assertDoesNotThrow(() -> {
            final var languages = Edition.getAllEditionsLanguages();
            Assertions.assertNotNull(languages);
            Assertions.assertNotEquals(0, languages.length);
            LOGGER.info("All editions languages: " + Arrays.toString(languages));
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random language function")
    void getRandomLanguage() {
        Assertions.assertDoesNotThrow(() -> {
            final var language = Edition.getRandomLanguage();
            Assertions.assertNotNull(language);
            Assertions.assertFalse(language.isBlank());
            LOGGER.info("Random language: " + language);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"ar", "en", "fr"})
    @DisplayName("Test the get random edition with specific format and language and type function")
    void testGetRandomEditionWithSpecificFormatAndLangAndType(final String language) {
        Assertions.assertDoesNotThrow(() -> {
            final var arabicEdition = Edition.getRandomEdition(EditionFormat.AUDIO, language,
                    EditionType.VERSEBYVERSE);
            Assertions.assertNotNull(arabicEdition);
            LOGGER.info("Random " + language + " edition, audio, versebyverse: " + arabicEdition.getName());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"ar", "en", "fr"})
    @DisplayName("Test the get random edition with specific format and language function")
    void testGetRandomEditionWithSpecificFormatAndLang(final String language) {
        Assertions.assertDoesNotThrow(() -> {
            final var audioEdition = Edition.getRandomEdition(EditionFormat.AUDIO, language);
            Assertions.assertNotNull(audioEdition);
            LOGGER.info("Random " + language + " edition, audio: " + audioEdition.getName());

            final var textEdition = Edition.getRandomEdition(EditionFormat.TEXT, language);
            Assertions.assertNotNull(textEdition);
            LOGGER.info("Random " + language + " edition, text: " + textEdition.getName());
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    @DisplayName("Test the get random edition with specific format function")
    void testGetRandomEditionWithSpecificFormat(final int index) {
        final var editionFormat = EditionFormat.values()[index];
        Assertions.assertDoesNotThrow(() -> {
            final var edition = Edition.getRandomEdition(editionFormat);
            Assertions.assertNotNull(edition);
            LOGGER.info("Random " + editionFormat.toString().toLowerCase() + " edition: " + edition.getName());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"ar", "en", "de"})
    @DisplayName("Test the get random edition with specific language function")
    void testGetRandomEditionWithSpecificLanguage(final String language) {
        Assertions.assertDoesNotThrow(() -> {
            final var randomEdition = Edition.getRandomEdition(language);
            Assertions.assertNotNull(randomEdition);
            LOGGER.info("Random " + language + " edition: " + randomEdition.getName());
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random edition function")
    void testGetRandomEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var randomEdition = Edition.getRandomEdition();
            Assertions.assertNotNull(randomEdition);
            LOGGER.info("Random edition: " + randomEdition.getName());
        });
    }

    @Test
    @DisplayName("Test the search with specific surah and edition function")
    void testSearchInSpecificSurahAndEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayas = Edition.search("Abraham", Surahs.AL_BAQARA.getNumber(), "en.pickthall");
            Assertions.assertNotNull(ayas);
            Assertions.assertNotEquals(0, ayas.length);
            LOGGER.info("Abraham in surah al-baqara, en.pickthall: " + Arrays.toString(ayas));

            final var ayas2 = Edition.search("I seek refuge with the Sustainer of men", Surahs.AN_NAAS.getNumber(), "en.asad");
            Assertions.assertNotNull(ayas2);
            Assertions.assertNotEquals(0, ayas2.length);
            LOGGER.info(Arrays.toString(ayas2));
        });
    }

    @Test
    @DisplayName("Test the search method in all quran")
    void testSearch() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayas = Edition.search("Abraham");
            Assertions.assertNotNull(ayas);
            Assertions.assertNotEquals(0, ayas.length);
            LOGGER.info("Abraham: " + Arrays.toString(ayas));
        });
    }
}
