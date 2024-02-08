package com.example.recommender.beans;

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

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RadioStation> radioStations;


    protected User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {return userName; }

    public void setUserName(String user_name) {this.userName = user_name;}

    public List<RadioStation> getRadioStations() {
        return radioStations;
    }

    public void setRadioStations(List<RadioStation> radioStations) {
        this.radioStations = radioStations;
    }
}