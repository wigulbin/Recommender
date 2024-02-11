//package com.example.recommender.tables;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity(name="APPLICATION_USERS")
//public class User {
//    @Id
//    private String id;
//    private String userName = "";
//    private String product = "";
//    private String imageUrl = "";
//    private int imageHeight;
//    private int imageWidth;
//
//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<RadioStation> radioStations;
//
//    public User() {
//    }
//
//    @Override
//    public String toString() {
//        return String.format(
//                "User[id=%d, userName='%s']",
//                id, userName);
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getProduct() {
//        return product;
//    }
//
//    public void setProduct(String product) {
//        this.product = product;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public int getImageHeight() {
//        return imageHeight;
//    }
//
//    public void setImageHeight(int imageHeight) {
//        this.imageHeight = imageHeight;
//    }
//
//    public int getImageWidth() {
//        return imageWidth;
//    }
//
//    public void setImageWidth(int imageWidth) {
//        this.imageWidth = imageWidth;
//    }
//}