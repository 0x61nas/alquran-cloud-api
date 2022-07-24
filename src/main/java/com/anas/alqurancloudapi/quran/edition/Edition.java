package com.anas.alqurancloudapi.quran.edition;

import java.io.Serializable;

/**
 * It represents an edition of the Quran
 */
public class Edition implements Serializable {
    private final String name;
    private final String identifier;
    private final String language;
    private final String englishName;
    private final EditionFormat format;
    private final EditionType type;
    private final Direction direction;

    // For JSON deserialization
    protected Edition() {
        this(null);
    }

    /**
     * Constructor for the Edition class.
     * @param identifier The identifier of the edition, e.g. "ar.alafasy".
     */
    public Edition(final String identifier) {
        this.identifier = identifier;
        this.name = null;
        this.language = null;
        this.englishName = null;
        this.format = EditionFormat.values()[0];
        this.type = EditionType.values()[0];
        this.direction = Direction.values()[0];
    }

    /**
     * This function returns the original name of the edition.
     *
     * @return The name of the edition.
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns the identifier of the edition.
     *
     * @return The identifier of the edition.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * This function returns the language of the edition.
     *
     * @return The language of the edition.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This function returns the english name of the edition.
     *
     * @return The english name of the edition.
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * This function returns the format of the edition (text, audio), audio includes the audio and the text.
     *
     * @return The format of the edition.
     */
    public EditionFormat getFormat() {
        return format;
    }

    /**
     * This function returns the type of the edition (quran, translation, transliteration, tafsir).
     *
     * @return The type of the edition.
     */
    public EditionType getType() {
        return type;
    }

    /**
     * This function returns the direction of the text in the edition.
     * Right-to-left or left-to-right.
     *
     * @return The direction of the text in the edition.
     */
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
