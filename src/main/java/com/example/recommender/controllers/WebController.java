package com.example.recommender.controllers;

import com.example.recommender.api.SpotifyClient;
import com.example.recommender.beans.Track;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
public class WebController {
    @GetMapping("/")
    public String landingPage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "landing";
    }
    @GetMapping("/index")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
    @GetMapping("/track")
    public String getTrack(@RequestParam(name="trackid", required=false, defaultValue="") String trackid, Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = new SpotifyClient();
        Track track = client.findTrack(trackid);

        model.addAttribute("track", track);
        return "track";
    }
    @GetMapping("/search")
    public String getSearch(@RequestParam(name="trackid", required=false, defaultValue="") String trackid, Model model) throws IOException, URISyntaxException, InterruptedException {

        return "search";
    }
    @PostMapping("/search")
    public String postSearch(@RequestParam(name="query", required=true, defaultValue="") String query,
                             @RequestParam(name="query", required=true, defaultValue="") String types,
                             Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = new SpotifyClient();
        Map<String, Object> results = client.searchApi(query);

        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "results";
    }

    @GetMapping("album")
    public String getAlbum(@RequestParam(name="albumid", defaultValue = "") String albumid, Model model){
        SpotifyClient client - new SpotifyClient();

    }


}
