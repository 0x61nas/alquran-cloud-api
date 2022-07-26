package com.anas.alqurancloudapi.search;

import com.anas.alqurancloudapi.Ayah;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author: <a href="https://github.com/anas-elgarhy">Anas Elgarhy</a>
 * @date: 7/26/22
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    private final Ayah[] matches;

    public SearchResult() {
        matches = null;
    }

    public Ayah[] getMatches() {
        return matches;
    }
}
