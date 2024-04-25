package com.example.recommender.tables;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class RadioStationSongKey implements Serializable {
    private long trackId;
    private RadioStation radioStation;

    public RadioStationSongKey(long trackId, RadioStation radioStation) {
        this.trackId = trackId;
        this.radioStation = radioStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadioStationSongKey that = (RadioStationSongKey) o;
        return trackId == that.trackId && Objects.equals(radioStation, that.radioStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, radioStation);
    }
}