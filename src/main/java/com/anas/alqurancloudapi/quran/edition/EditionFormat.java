package com.anas.alqurancloudapi.quran.edition;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The format of the edition.
 * text or audio (maybe including text also).
 */
public enum EditionFormat {
    @JsonProperty("text")
    TEXT,
    @JsonProperty("audio")
    AUDIO,
}
