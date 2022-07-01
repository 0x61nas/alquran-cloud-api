package com.anas.alqurancloudapi.quran.edition;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The direction of the text in the edition.
 * Right-to-left or left-to-right.
 */
public enum Direction {
    @JsonProperty("rtl")
    RTL,
    @JsonProperty("ltr")
    LTR,
}
