package com.anas.alqurancloudapi.quran.edition;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Direction {
    @JsonProperty("rtl")
    RTL,
    @JsonProperty("ltr")
    LTR,
}
