package com.example.recommender.controllers;

import com.example.recommender.api.AccessToken;
import com.example.recommender.api.SpotifyClient;
import com.example.recommender.api.User;
import com.example.recommender.beans.Album;
import com.example.recommender.beans.Artist;
import com.example.recommender.beans.Track;
import com.example.recommender.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SessionAttributes({"client"})
@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String landingPage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "landing";
    }

    @GetMapping("/login")
    public RedirectView loginWithSpotify(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        List<String> scopeList = List.of("app-remote-control", "streaming", "playlist-read-private", "playlist-read-collaborative", "user-read-playback-position", "user-top-read", "user-read-recently-played", "user-library-read", "user-read-email");
        String scopes = String.join(" ", scopeList);
        String state = "129030983124089u";
        String encodedRedirect = SpotifyClient.getEncodedRedirectURL();
        String authURL = String.format("https://accounts.spotify.com/authorize?response_type=%s&client_id=%s&scope=%s&redirect_uri=%s&state=%s&show_dialog=%s", "code", SpotifyClient.getClientid(), scopes, encodedRedirect, state, "true");

        return new RedirectView(authURL);
    }

    @GetMapping("/callback")
    public ModelAndView callback(@RequestParam(name="code", required=false, defaultValue="") String code,
                                 @RequestParam(name="state", required=false, defaultValue="") String state,
                                 @RequestParam(name="error", required=false, defaultValue="") String error,
                                 ModelMap model) {
        System.out.println("Code = " + code);
        System.out.println("State = " + state);

        SpotifyClient client = new SpotifyClient(code);
        model.addAttribute("client", client);

        return new ModelAndView("redirect:/search", model);
    }

    @GetMapping("/index")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/track")
    public String getTrack(@RequestParam(name="trackid", required=false, defaultValue="") String trackid, Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = new SpotifyClient("");
        Track track = client.findTrack(trackid);

        model.addAttribute("track", track);
        return "track";
    }

    @GetMapping("/search")
    public String getSearch(@RequestParam(name="trackid", required=false, defaultValue="") String trackid, Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = (SpotifyClient)model.getAttribute("client");

        return "search";
    }

    @PostMapping("/search")
    public String postSearch(@RequestParam(name="query", required=true, defaultValue="") String query,
                             @RequestParam(name="query", required=true, defaultValue="") String types,
                             Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = (SpotifyClient)model.getAttribute("client");

        Map<String, Object> results = client.searchApi(query);

        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "results";
    }

    @GetMapping("/album")
    public String getAlbum(@RequestParam(name="albumid", defaultValue = "") String albumid, Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = new SpotifyClient("");
        Album album = client.findAlbum(albumid);

        model.addAttribute("album", album);
        return "album";
    }

    @GetMapping("/artist")
    public String getArtist(@RequestParam(name="artistid", required=false, defaultValue="") String artistid, Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = new SpotifyClient("");
        Artist artist = client.findArtist(artistid);

        model.addAttribute("artist", artist);
        return "artist";
    }

    @GetMapping("/testUser")
    public String testUser(@RequestParam(name="artistid", required=false, defaultValue="") String artistid, Model model) throws IOException, URISyntaxException, InterruptedException {
        User user = new User("test", "user");
        userRepository.save(user);

        model.addAttribute("user", user);
        return "user";
    }


}
