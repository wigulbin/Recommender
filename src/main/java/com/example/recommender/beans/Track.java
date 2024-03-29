package com.example.recommender.beans;

import java.util.Map;

public class Track {
    private String id = "";
    private String name = "";
    private int popularity;
    private String previewUrl = "";

    public Track(Map<String, Object> dataMap) {
        this.id = dataMap.getOrDefault("id", "").toString();
        this.name = dataMap.getOrDefault("name", "").toString();
        this.popularity = Integer.parseInt(dataMap.getOrDefault("popularity", "0").toString());
        Object var3 = dataMap.get("preview_url");
        if (var3 instanceof String previewString) {
            this.previewUrl = previewString;
        }
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPopularity() {
        return this.popularity;
    }

    public String getPreviewUrl() {
        return this.previewUrl;
    }

}
