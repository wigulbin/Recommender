package com.example.recommender.beans;

import java.util.Map;

public class Album {
    String id = "";
    String name = "";

    public Album(Map<String, Object> dataMap) {
        this.id = dataMap.getOrDefault("id", "").toString();
        this.name = dataMap.getOrDefault("name", "").toString();
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }
}
