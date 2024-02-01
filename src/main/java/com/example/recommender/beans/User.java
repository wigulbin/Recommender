package com.example.recommender.beans;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name="APPLICATION_USERS")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, name = "USER_SEQ")
    private Long id;
    private String firstName;
    private String lastName;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<RadioStation> getRadioStations() {
        return radioStations;
    }

    public void setRadioStations(List<RadioStation> radioStations) {
        this.radioStations = radioStations;
    }
}