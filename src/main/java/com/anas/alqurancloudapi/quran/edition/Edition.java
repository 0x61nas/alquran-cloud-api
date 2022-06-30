package com.anas.alqurancloudapi.quran.edition;

public class Edition {
    private final String name;
    private final String identifier;
    private final String language;
    private final String englishName;
    private final EditionFormat format;
    private final EditionType type;
    private final Direction direction;
    public final static Edition Unknown;

    static {
        Unknown = new Edition();
    }

    protected Edition() {
        this("Unknown");
    }

    public Edition(final String identifier) {
        this.identifier = identifier;
        this.name = "Unknown";
        this.language = "Unknown";
        this.englishName = "Unknown";
        this.format = EditionFormat.values()[0];
        this.type = EditionType.values()[0];
        this.direction = Direction.values()[0];
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLanguage() {
        return language;
    }

    public String getEnglishName() {
        return englishName;
    }

    public EditionFormat getFormat() {
        return format;
    }

    public EditionType getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Edition{" + "name=" + name + ", identifier=" + identifier + ", language=" + language
                + ", englishName=" + englishName + ", format=" + format + ", type=" + type + ", direction=" + direction
                + '}';
    }
}
