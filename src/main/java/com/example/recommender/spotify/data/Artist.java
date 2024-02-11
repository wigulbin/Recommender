package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public class Artist {
    @JsonProperty("external_urls")
    private ExternalUrl externalUrl;
    private Followers followers;
    private List<String> genres = new ArrayList<>();
    private String href = "";
    private String id = "";
    private List<Image> images;
    private String name = "";
    private int popularity;
    private String type = "";
    private String uri = "";

    public Artist() {
    }

    public ExternalUrl getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(ExternalUrl externalUrl) {
        this.externalUrl = externalUrl;
    }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
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
}
