package com.example.recommender.controllers;

import com.example.recommender.spotify.data.SearchResult;
import com.example.recommender.spotify.logic.SpotifyClient;
import com.example.recommender.beans.Album;
import com.example.recommender.beans.Artist;
import com.example.recommender.beans.Track;
//import com.example.recommender.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.Map;

@SessionAttributes({"client"})
@Controller
public class WebController {
    private static final Logger log = LoggerFactory.getLogger(WebController.class);

//    @Autowired
//    UserRepository userRepository;

    @GetMapping("/")
    public String landingPage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "landing";
    }

    @GetMapping("/login")
    public RedirectView loginWithSpotify(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        List<String> scopeList = List.of("app-remote-control", "streaming", "playlist-read-private", "playlist-read-collaborative", "user-read-playback-position", "user-top-read", "user-read-recently-played", "user-library-read", "user-read-email");
        String scopes = String.join(" ", scopeList);
        String state = "129030983124089u";  // this will be a random string that we generate and store. we then send it to spotify with the request and make sure that it matches when spotify sends us a request back
        String encodedRedirect = SpotifyClient.getEncodedRedirectURL();

        String authURL = String.format("https://accounts.spotify.com/authorize?response_type=%s&client_id=%s&scope=%s&redirect_uri=%s&state=%s&show_dialog=%s", "code", SpotifyClient.getClientid(), scopes, encodedRedirect, state, "true");

        return new RedirectView(authURL);  // we redirect the user to the spotify login screen with the state, scopes, etc.
    }

    @GetMapping("/callback")

    public ModelAndView callback(@RequestParam(name="code", required=false, defaultValue="") String code,  // code passed back from spotify
                                 @RequestParam(name="state", required=false, defaultValue="") String state,  // when this comes back from spotify it should match the state that we created and saved earlier
                                 @RequestParam(name="error", required=false, defaultValue="") String error,  // if we get any errors
                                 ModelMap model) {
        System.out.println("Code = " + code);
        System.out.println("State = " + state);

        SpotifyClient client = new SpotifyClient(code);
        model.addAttribute("client", client);

        return new ModelAndView("redirect:/getProfile", model);
    }

    @GetMapping("/getProfile")
    public RedirectView getProfile(Model model) throws IOException, URISyntaxException, InterruptedException {
        if(model.getAttribute("client") instanceof SpotifyClient client) {
            client.findProfile();
        }
        return new RedirectView("/search");
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

        return "search";
    }

    @PostMapping("/search")
    public String postSearch(@RequestParam(name="query", required=true, defaultValue="") String query,
                             @RequestParam(name="types", required=true, defaultValue="") String types,
                             Model model) throws IOException, URISyntaxException, InterruptedException {
        SpotifyClient client = (SpotifyClient)model.getAttribute("client");

        SearchResult results = client.searchApiForTrack(query);

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

    @GetMapping("/test")
    public String getInfo() {
        return "indexTest";
    }

    @PostMapping("/testSubmit")
    public RedirectView postInfo(@RequestParam(name = "OurInput", required = false, defaultValue = "") String ourInput) {
        return new RedirectView("/test?input=" + ourInput);
    }
}

