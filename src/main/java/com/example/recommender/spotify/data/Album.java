package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public class Album {

    @JsonProperty("album_type")
    private String albumType = "";
    @JsonProperty("total_tracks")
    private int totalTracks;
    @JsonProperty("available_markets")
    private List<String> availableMarkets = new ArrayList<>();
    @JsonProperty("external_urls")
    private ExternalUrl externalUrl;
    private String href = "";
    private String id = "";
    private List<Image> images = new ArrayList<>();
    private String name = "";
    @JsonProperty("release_date")
    private String releaseDate = "";
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision = "";
    @JsonProperty("restrictions")
    private Restriction restriction;
    private String type = "";
    private String uri = "";

    private List<Stub> artists = new ArrayList<>();

    public Album() {
    }

    @Override
    public String toString() {
        return "Album '" +
                 name + "'" +
                 artists;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public int getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public ExternalUrl getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(ExternalUrl externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public void setReleaseDatePrecision(String releaseDatePrecision) {
        this.releaseDatePrecision = releaseDatePrecision;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<Stub> getArtists() {
        return artists;
    }

    public void setArtists(List<Stub> artists) {
        this.artists = artists;
    }
}
