package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
    private String id = "";
    @JsonProperty("is_active")
    private boolean active;
    @JsonProperty("is_private_session")
    private boolean privateSession;
    @JsonProperty("is_restricted")
    private boolean restricted;
    private String name = "";
    private String type = "";
    @JsonProperty("volume_percent")
    private int volumePercent;
    @JsonProperty("supports_volume")
    private boolean supportsVolume;

    public Device() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPrivateSession() {
        return privateSession;
    }

    public void setPrivateSession(boolean privateSession) {
        this.privateSession = privateSession;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
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

    public int getVolumePercent() {
        return volumePercent;
    }

    public void setVolumePercent(int volumePercent) {
        this.volumePercent = volumePercent;
    }

    public boolean isSupportsVolume() {
        return supportsVolume;
    }

    public void setSupportsVolume(boolean supportsVolume) {
        this.supportsVolume = supportsVolume;
    }
}
