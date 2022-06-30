package com.anas.alqurancloudapi.quran;

public enum RevelationType {
    MECCAN("مكية"),
    MEDINAN("مدنية")
    ;
    private final String arabicName;

    private RevelationType(String arabicName) {
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
