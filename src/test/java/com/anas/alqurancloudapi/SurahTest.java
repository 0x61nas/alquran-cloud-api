package com.anas.alqurancloudapi;

import com.anas.alqurancloudapi.consts.Constants;
import com.anas.alqurancloudapi.consts.Surahs;
import com.anas.alqurancloudapi.edition.Edition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.logging.Logger;

/**
 * @author: <a href="https://github.com/anas-elgarhy">Anas Elgarhy</a>
 * @date: 7/27/22
 */
class SurahTest {
    private final static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(SurahTest.class.getName());
    }

    public static String[] getKeywords() {
        return new String[]{"قُلْ أَعُوذُ بِرَبِّ ٱلنَّاسِ",
                "فَقَالَ ٱلْكَٰفِرُونَ هَٰذَا شَىْءٌ عَجِيبٌ",
                "قَدْ"};
    }

    @Test
    @DisplayName("Test the get surah with specific number and edition and offset and limit")
    void testGetSurahWithTheSurahNumberAndSpecificEditionAndOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(Surahs.AL_BAQARA.getNumber(),
                    Edition.getRandomEdition(), 0, 2);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @Test
    @DisplayName("Test the get surah with specific number and edition identifier and offset")
    void testGetSurahWithTheSurahNumberAndSpecificEditionIdentifierAndOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(Surahs.AL_BAQARA.getNumber(),
                    Edition.getRandomEdition().getIdentifier(), 0, 2);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get surah with edition")
    void testGetSurahWithTheSurahNumberAndSpecificEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.values()[(int) (Math.random() * Constants.SURAS_COUNT)].getNumber(),
                    Edition.getRandomEdition());
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get surah with edition identifier")
    void testGetSurahWithTheSurahNumberAndSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.values()[(int) (Math.random() * Constants.SURAS_COUNT)].getNumber(),
                    Edition.getRandomEdition().getIdentifier());
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(2)
    @DisplayName("Test the get surah with the surah number specific offset and limit")
    void testGetSurahWithTheSurahNumberAndSpecificOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.values()[(int) (Math.random() * Constants.SURAS_COUNT)].getNumber(),
                    0, 2);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @Test
    @DisplayName("Test the get surah with specific number")
    void testGetSurahWithSurahNumber() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.values()[(int) (Math.random() * Constants.SURAS_COUNT)].getNumber());
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah.getEnglishName());
        });
    }

    @Test
    @DisplayName("Test the get surah function")
    void testGetSurah() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.values()[(int) (Math.random() * Constants.SURAS_COUNT)]);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah.getEnglishName());
        });
    }

    @Test
    @DisplayName("Test the get surah with specific offset and limit")
    void testGetSurahWithSpecificEditionAndOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.AAL_I_IMRAAN,
                    Edition.getRandomEdition(), 0, 2);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @Test
    @DisplayName("Test the get surah with specific")
    void testGetSurahWithSpecificEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.AL_FATIHA,
                    Edition.getRandomEdition());
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @Test
    @DisplayName("Test the get surah with specific edition identifier")
    void testGetSurahWithSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(
                    Surahs.AN_NAAS,
                    Edition.getRandomEdition().getIdentifier());
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random surah with specific edition and offset and limit")
    void getRandomSurahWithSpecificEditionAndOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getRandomSurah(new Edition("ar.alafasy"), 0, 2);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random surah with specific edition")
    void testGetRandomSurahWithSpecificEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getRandomSurah(new Edition("ar.alafasy"));
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random surah with specific edition identifier and offset and limit")
    void testGetRandomSurahWithSpecificEditionIdentifierAndOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getRandomSurah(
                    Edition.getRandomEdition().getIdentifier(), 1, 3);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @Test
    @DisplayName("Test the get random surah with specific edition identifier")
    void testGetRandomSurahWithSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getRandomSurah("ar.alafasy");
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random surah with specific offset and limit")
    void testGetRandomSurahWithSpecificOffsetAndLimit() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getRandomSurah(0, 2);
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random surah function")
    void testGetRandomSurah() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getRandomSurah();
            Assertions.assertNotNull(surah);
            LOGGER.info("Surah: " + surah);
        });
    }

    @ParameterizedTest
    @MethodSource("getKeywords")
    @DisplayName("Test the search in surah function")
    void search(final String keyword) {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(Surahs.QAAF, "ar.alafasy");
            final var result = surah.search(keyword);
            Assertions.assertNotNull(result);
            LOGGER.info("Surah: " + surah);
            LOGGER.info("Result number: " + result.length);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random ayah from the surah function")
    void testGetRandomAyah() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(Surahs.QAAF, "ar.alafasy");
            final var randomAyah = surah.getRandomAyah();
            Assertions.assertNotNull(randomAyah);
            LOGGER.info(randomAyah.getText());
        });
    }

    @Test
    @DisplayName("Test the get specific ayah from the surah function")
    void testGetAyah() {
        Assertions.assertDoesNotThrow(() -> {
            final var surah = Surah.getSurah(Surahs.AL_FATIHA, "ar.alafasy");
            final var ayah = surah.getAyah(0);
            Assertions.assertNotNull(ayah);
            Assertions.assertEquals("﻿بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ", ayah.getText());
            LOGGER.info(ayah.getText());
        });
    }
}