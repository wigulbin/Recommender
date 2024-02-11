package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Stub {
    @JsonProperty("external_urls")
    private ExternalUrl externalUrl;
    private String href = "";
    private String id = "";
    private String name = "";
    private String type = "";
    private String uri = "";

    public Stub() {
    }

    @Override
    public String toString() {
        return "Stub " +
                "name='" + name + '\'' +
                "type='" + type + '\'';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
