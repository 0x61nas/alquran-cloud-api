package com.anas.alqurancloudapi.edition;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type of the edition.
 * Quran, Translation, Transliteration, Tafsir.
 */
public enum EditionType {
    @JsonProperty("quran")
    QURAN,
    @JsonProperty("translation")
    TRANSLATION,
    @JsonProperty("transliteration")
    TRANSLITERATION,
    @JsonProperty("versebyverse")
    VERSE_BY_VERSE,
    @JsonProperty("tafsir")
    TAFSIR,
}
