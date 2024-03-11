package com.example.recommender.tables;

import com.example.recommender.spotify.data.SpotifyProfile;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="APPLICATION_USERS")
public class User {
    @Id
    private String id;
    private String userName = "";
    private String product = "";
    private String imageUrl = "";
    private int imageHeight;
    private int imageWidth;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // what is this for?
    private List<RadioStation> radioStations;

    public User() {}

    public User(String id, String userName, String product, String imageUrl, int imageHeight, int imageWidth, List<RadioStation> radioStations) {
        this.id = id;
        this.userName = userName;
        this.product = product;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.radioStations = radioStations;
    }

    public void mimicProfile(SpotifyProfile profile) {
        this.id = profile.getId();
        this.userName = profile.getDisplayName();
        this.product = profile.getProduct(); // ended up null when I first ran it, gotta check for that somewhere
        this.imageUrl = profile.getImage().getFirst().getUrl();
        this.imageHeight = profile.getImage().getFirst().getHeight();
        this.imageWidth = profile.getImage().getFirst().getWidth();
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, userName='%s']",
                id, userName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }
}