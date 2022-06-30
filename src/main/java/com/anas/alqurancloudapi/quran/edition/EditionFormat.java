package com.anas.alqurancloudapi.quran.edition;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EditionFormat {
    @JsonProperty("text")
    TEXT,
    @JsonProperty("audio")
    AUDIO,
}
