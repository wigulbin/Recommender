package com.example.recommender.tables;

import jakarta.persistence.*;

@Entity(name="RADIO_STATION_SONGS")
@IdClass(RadioStationSongKey.class)
public class RadioStationSong {
    @Id
    private long trackId;
    @Id
    @ManyToOne
    @JoinColumn(name="RADIO_STATION_ID", referencedColumnName = "id")
    private RadioStation radioStation;

    private boolean skipped;
    private int percentCompleted;
    private boolean liked;
    private boolean disliked;
    private boolean seed;

    public RadioStationSong() {}


    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public boolean isSeed() {
        return seed;
    }

    public void setSeed(boolean seed) {
        this.seed = seed;
    }

    public RadioStation getRadioStation() {
        return radioStation;
    }

    public void setRadioStation(RadioStation radioStation) {
        this.radioStation = radioStation;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public int getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(int percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isDisliked() {
        return disliked;
    }

    public void setDisliked(boolean disliked) {
        this.disliked = disliked;
    }
}
