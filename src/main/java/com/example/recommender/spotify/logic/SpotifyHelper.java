package com.example.recommender.spotify.logic;

import com.example.recommender.spotify.data.Tracks;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpotifyHelper {

    public static int getTotalPages(Tracks tracks){
        BigDecimal total = new BigDecimal(tracks.getTotal());
        BigDecimal limit = new BigDecimal(tracks.getLimit());
        return total.divide(limit, RoundingMode.CEILING).intValue();
    }
    public static int getCurrentPage(Tracks tracks){
        BigDecimal limit = new BigDecimal(tracks.getLimit());
        BigDecimal offset = new BigDecimal(tracks.getOffset());

        return  offset.divide(limit, RoundingMode.CEILING).intValue() + 1;
    }
}
