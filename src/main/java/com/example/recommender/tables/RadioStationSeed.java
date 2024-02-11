//package com.example.recommender.tables;
//
//import jakarta.persistence.*;
//
//@Entity(name="RADIO_STATION_SEEDS")
//public class RadioStationSeed {
//    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "RS_SEED_SEQ")
//    @SequenceGenerator(sequenceName = "rs_seed_seq", allocationSize = 1, name = "RS_SEED_SEQ")
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name="radioStationId", referencedColumnName = "id")
//    private RadioStation radioStation;
//
//    private String detailType = "";
//    private String detailValue = "";
//
//    public RadioStationSeed() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public RadioStation getRadioStation() {
//        return radioStation;
//    }
//
//    public void setRadioStation(RadioStation radioStation) {
//        this.radioStation = radioStation;
//    }
//
//    public String getDetailType() {
//        return detailType;
//    }
//
//    public void setDetailType(String detailType) {
//        this.detailType = detailType;
//    }
//
//    public String getDetailValue() {
//        return detailValue;
//    }
//
//    public void setDetailValue(String detailValue) {
//        this.detailValue = detailValue;
//    }
//}
