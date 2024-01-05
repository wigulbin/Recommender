package com.example.recommender.controllers;

import com.example.recommender.api.SpotifyClient;
import com.example.recommender.beans.Track;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class WebController {
    @GetMapping("/index")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
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
}
