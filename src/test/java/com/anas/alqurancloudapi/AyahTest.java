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
@DisplayName("Ayah class tests")
class AyahTest {
    private final static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(AyahTest.class.getName());
    }

    public static int[] generateRandomAyahsNumbers() {
        final var randomNumbers = new int[(int) (Math.random() * 10)];
        for (var i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = (int) (Math.random() * Constants.AYAHS_COUNT);
        }
        return randomNumbers;
    }

    @ParameterizedTest
    @MethodSource("generateRandomAyahsNumbers")
    @DisplayName("Test the get ayah with specific number and edition")
    void testGetAyahWithSpecificAyahNumberAndEdition(final int ayahNumber) {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getAyah(ayahNumber, Edition.getRandomEdition());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @ParameterizedTest
    @MethodSource("generateRandomAyahsNumbers")
    @DisplayName("Test the get ayah with specific number and edition identifier")
    void testGetAyahWithSpecificEditionIdentifier(final int ayahNumber) {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getAyah(ayahNumber, Edition.getRandomEdition().getIdentifier());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @ParameterizedTest
    @MethodSource("generateRandomAyahsNumbers")
    @DisplayName("Test the get ayah with specific number")
    void testGetAyah(final int ayahNumber) {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getAyah(ayahNumber);
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random ayah with specific edition")
    void testGetRandomAyahWithSpecificEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getRandomAyah(Edition.getRandomEdition());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random ayah with specific edition identifier")
    void testGetRandomAyahWithSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getRandomAyah(Edition.getRandomEdition().getIdentifier());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random ayah function")
    void testGetRandomAyah() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getRandomAyah();
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @Test
    @DisplayName("Test the get ayah from specific surah and edition")
    void testGetAyahFromSurahWithSpecificEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getAyahFromSurah(Surahs.AL_BAQARA, 2, Edition.getRandomEdition());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @Test
    @DisplayName("Test the get ayah from specific surah and edition identifier")
    void testGetAyahFromSurahWithSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getAyahFromSurah(Surahs.AL_BAQARA, 2, Edition.getRandomEdition().getIdentifier());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @Test
    @DisplayName("Test the get ayah from specific surah")
    void testGetAyahFromSurah() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getAyahFromSurah(Surahs.AL_BAQARA, 2);
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random ayah from specific surah and edition")
    void getRandomAyahFromSurahWithSpecificEdition() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getRandomAyahFromSurah(Surahs.AL_BAQARA, Edition.getRandomEdition());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random ayah from specific surah and edition identifier")
    void testGetRandomAyahFromSurahWithSpecificEditionIdentifier() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getRandomAyahFromSurah(Surahs.AL_BAQARA,
                    Edition.getRandomEdition().getIdentifier());
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }

    @RepeatedTest(4)
    @DisplayName("Test the get random ayah from specific surah")
    void testGetRandomAyahFromSurah() {
        Assertions.assertDoesNotThrow(() -> {
            final var ayah = Ayah.getRandomAyahFromSurah(Surahs.AL_BAQARA);
            Assertions.assertNotNull(ayah);
            LOGGER.info("Ayah: " + ayah);
        });
    }
}