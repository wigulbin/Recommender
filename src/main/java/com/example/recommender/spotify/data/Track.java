package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonIgnoreProperties
public class Track{
    private Album album;

    private List<Artist> artists = new ArrayList<>();

    @JsonProperty("available_markets")
    private List<String> availableMarket = new ArrayList<>();
    @JsonProperty("disc_number")
    private int discNumber;
    @JsonProperty("duration_ms")
    private int durationMs;
    private boolean explicit;
    @JsonProperty("external_ids")
    private ExternalID externalID;
    @JsonProperty("external_urls")
    private ExternalUrl externalUrl;
    private String href = "";
    private String id = "";
    @JsonProperty("is_playable")
    private boolean playable;
    @JsonProperty("restrictions")
    Restriction restriction;
    private String name = "";
    private int popularity;
    @JsonProperty("preview_url")
    private String previewURL = "";
    @JsonProperty("track_number")
    private int trackNumber;
    private String type;
    private String uri = "";
    @JsonProperty("is_local")
    private boolean local;

    public Track() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + ", " + album.getName() + " by " + artists.stream().map(Artist::getName).collect(Collectors.joining(", "));
    }

    public String getArtistNames(){
        return artists.stream().map(Artist::getName).collect(Collectors.joining(", "));
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<String> getAvailableMarket() {
        return availableMarket;
    }

    public void setAvailableMarket(List<String> availableMarket) {
        this.availableMarket = availableMarket;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public int getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(int durationMs) {
        this.durationMs = durationMs;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public ExternalID getExternalID() {
        return externalID;
    }

    public void setExternalID(ExternalID externalID) {
        this.externalID = externalID;
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

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
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

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }
}
