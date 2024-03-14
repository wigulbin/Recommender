//package com.example.recommender.beans;
//
//import jakarta.persistence.*;
//
//@Entity(name = "App_UsersCopy")
//public class UsersCopy {
//    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "USER_SEQ")
//    @SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, name = "USER_SEQ")
//    private Long id;
//    private String user_name;
//    private String created_at;
//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<RadioStation> radioStations;
//
//    protected UsersCopy() {}
//
//    public UsersCopy(String user_name, String created_at) {
//        this.user_name = user_name;
//        this.created_at = created_at;
//    }
//
//    @Override
//    public String toString() {
//        return String.format(
//                "UsersCopy[]"
//        )
//    }
//
//
//
//
//
//}
