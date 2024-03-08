package com.example.recommender.controllers;

import com.example.recommender.repositories.RadioStationRepository;
import com.example.recommender.repositories.RadioStationSeedRepository;
import com.example.recommender.repositories.UserRepository;
import com.example.recommender.spotify.data.Device;
import com.example.recommender.spotify.data.Devices;
import com.example.recommender.spotify.logic.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionAttributes({"client", "results"})
@RequestMapping("/playback")
@RestController
public class PlayBackController {

    @GetMapping("/start")
    public Map<String, String> start(@RequestParam("songid") String songid,  Model model) {
        Map<String, String> response = new HashMap<>();
        if(model.getAttribute("client") instanceof SpotifyClient client) {
            client.startPlayback(songid);
        }
        return response;
    }

    @GetMapping("/pause")
    public Map<String, String> pause(@RequestParam("songid") String songid,  Model model) {
        Map<String, String> response = new HashMap<>();
        if(model.getAttribute("client") instanceof SpotifyClient client) {
            client.pausePlayback(songid);
        }
        return response;
    }

    @GetMapping("/prev")
    public Map<String, String> prev(@RequestParam("songid") String songid,  Model model) {
        Map<String, String> response = new HashMap<>();
        if(model.getAttribute("client") instanceof SpotifyClient client) {
            client.prevPlayback();
        }
        return response;
    }

    @GetMapping("/next")
    public Map<String, String> next(@RequestParam("songid") String songid,  Model model) {
        Map<String, String> response = new HashMap<>();
        if(model.getAttribute("client") instanceof SpotifyClient client) {
            client.nextPlayback();
        }
        return response;
    }

}

