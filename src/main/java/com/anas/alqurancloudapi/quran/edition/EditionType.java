package com.anas.alqurancloudapi.quran.edition;

import com.fasterxml.jackson.annotation.JsonProperty;

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
