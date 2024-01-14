package com.example.recommender.beans;

import com.example.recommender.api.AccessToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Artist {

    private String id = "";
    private String name = "";
    private int popularity;
    private Object genres;

    public Artist(Map<String, Object> dataMap) {
        this.id = dataMap.getOrDefault("id", "").toString();
        this.name = dataMap.getOrDefault("name", "").toString();
        this.popularity = Integer.parseInt(dataMap.getOrDefault("popularity", "0").toString());
        Object var3 = dataMap.get("genres");
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

    public Object getGeneres() {
        return this.genres;
    }
}
