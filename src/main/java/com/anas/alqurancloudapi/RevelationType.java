package com.anas.alqurancloudapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RevelationType {
    @JsonProperty("Meccan")
    MECCAN("مكية"),
    @JsonProperty("Medinan")
    MEDINAN("مدنية")
    ;
    private final String arabicName;

    RevelationType(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getArabicName() {
        return arabicName;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
