package com.example.recommender.beans;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="RADIO_STATIONS")
public class RadioStation {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "RADIO_STATION_SEQ")
    @SequenceGenerator(sequenceName = "radio_station_seq", allocationSize = 1, name = "RADIO_STATION_SEQ")
    private long id;

    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "id")
    private User user;
    private String role = "";
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "radioStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RadioStationSong> songs;

    @OneToMany(mappedBy = "radioStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RadioStationSeed> seeds;

    public RadioStation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
