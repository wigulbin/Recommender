package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ExternalUrl {
    private String spotify = "";

    public ExternalUrl() {
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }
}
