package com.example.recommender.spotify.data;

import java.util.ArrayList;
import java.util.List;

public class SeedResults {
    private List<Seed> seeds = new ArrayList<>();
    private List<Track> tracks = new ArrayList<>();

    public SeedResults() {
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
