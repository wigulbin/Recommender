package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public class Devices {
    List<Device> devices = new ArrayList<>();

    public Devices() {
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
