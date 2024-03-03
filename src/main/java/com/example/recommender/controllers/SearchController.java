package com.example.recommender.controllers;

import com.example.recommender.beans.Album;
import com.example.recommender.beans.Artist;
import com.example.recommender.beans.Track;
import com.example.recommender.repositories.RadioStationRepository;
import com.example.recommender.repositories.RadioStationSeedRepository;
import com.example.recommender.repositories.UserRepository;
import com.example.recommender.spotify.data.SearchResult;
import com.example.recommender.spotify.logic.SpotifyClient;
import com.example.recommender.spotify.logic.SpotifyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@SessionAttributes({"client", "results"})
@Controller
public class SearchController {
    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @GetMapping("/search")
    public String getSearch(@RequestParam(name="trackid", required=false, defaultValue="") String trackid, Model model) throws IOException, URISyntaxException, InterruptedException {

        return "search";
    }

    @PostMapping("/search")
    public String postSearch(@RequestParam(name="query", required=true, defaultValue="") String query,
                             @RequestParam(name="types", required=true, defaultValue="") String types,
                             Model model) throws IOException, URISyntaxException, InterruptedException {
        if(model.getAttribute("client") instanceof SpotifyClient client){
            SearchResult results = client.searchApiForTrack(query);

            model.addAttribute("results", results);
            model.addAttribute("currentPage", SpotifyHelper.getCurrentPage(results.getTracks()));
            model.addAttribute("totalPages", SpotifyHelper.getTotalPages(results.getTracks()));
            model.addAttribute("query", query);
        }


        return "results";
    }

    @GetMapping("/search/next")
    public String getNext(Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = (SpotifyClient)model.getAttribute("client");
        SearchResult oldResults = (SearchResult)model.getAttribute("results");

        if(oldResults != null && oldResults.getTracks().getNext() != null){
            SearchResult results = client.getNextTracksFromResult(oldResults.getTracks());
            model.addAttribute("results", results);

            model.addAttribute("currentPage", SpotifyHelper.getCurrentPage(results.getTracks()));
            model.addAttribute("totalPages", SpotifyHelper.getTotalPages(results.getTracks()));
        }

        return "results";
    }

    @GetMapping("/search/prev")
    public String getPrev(Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = (SpotifyClient)model.getAttribute("client");
        SearchResult oldResults = (SearchResult)model.getAttribute("results");

        if(oldResults != null && oldResults.getTracks().getPrevious() != null){
            SearchResult results = client.getPrevTracksFromResult(oldResults.getTracks());
            model.addAttribute("results", results);

            model.addAttribute("currentPage", SpotifyHelper.getCurrentPage(results.getTracks()));
            model.addAttribute("totalPages", SpotifyHelper.getTotalPages(results.getTracks()));
        }

        return "results";
    }
}

