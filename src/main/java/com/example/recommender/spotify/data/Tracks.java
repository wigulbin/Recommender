package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonIgnoreProperties
public class Tracks {
    private String href = "";
    private int limit;
    private String next = "";
    private int offset;
    private String previous = "";
    private int total;
    private List<Track> items = new ArrayList<>();

    public Tracks() {
    }

    public Map<String, List<Track>> getMap(List<Track> items) {
        return items.stream().collect(Collectors.groupingBy(track -> track.getAlbum().getName()));
    }

    public Map<String, Album> getMapAlbums () {
        return items.stream()
                .map(Track::getAlbum)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toMap(Album::getName, album -> album, (album1, album2) -> album1));
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Track> getItems() {
        return items;
    }

    public void setItems(List<Track> items) {
        this.items = items;
    }
}
