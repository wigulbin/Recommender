package com.example.recommender.controllers;

import com.example.recommender.repositories.RadioStationRepository;
import com.example.recommender.repositories.RadioStationSeedRepository;
import com.example.recommender.repositories.UserRepository;
import com.example.recommender.spotify.data.SpotifyProfile;
import com.example.recommender.spotify.logic.SpotifyClient;
import com.example.recommender.beans.Album;
import com.example.recommender.beans.Artist;
import com.example.recommender.beans.Track;
//import com.example.recommender.repositories.UserRepository;
import com.example.recommender.tables.RadioStation;
import com.example.recommender.tables.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@SessionAttributes({"client", "results", "currentUser"})
@Controller
public class WebController {
    private static final Logger log = LoggerFactory.getLogger(WebController.class);


    UserRepository userRepository;
    RadioStationSeedRepository radioStationSeedRepository;
    RadioStationRepository radioStationRepository;

    public WebController(UserRepository userRepository, RadioStationSeedRepository radioStationSeedRepository, RadioStationRepository radioStationRepository){
        this.userRepository = userRepository;
        this.radioStationSeedRepository = radioStationSeedRepository;
        this.radioStationRepository = radioStationRepository;
    }

    @GetMapping("/")
    public String landingPage() {
        return "landing";
    }

    @GetMapping("/login")
    public RedirectView loginWithSpotify() {
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
    /*
    TODO:
    X 1. Call find profile from client
    X    - Maps to SpotifyProfile object (ideally this class is in the spotify.data package)
    X2. Create an User object based on the profile
    X    - Determine which fields we need
        - See if anything needs to be added/removed from the User table
    3. Save User object
        - Ensure that object saves to database without error
    4. Set User object to the model/session to be used in other places
        - See @SessionAttributes({"client", "results"}) annotation, field for user will need to be added
    */
    @GetMapping("/getProfile")
    public RedirectView getProfile(Model model) throws IOException, URISyntaxException, InterruptedException {
        if(model.getAttribute("client") instanceof SpotifyClient client) {
            SpotifyProfile newProf = client.findProfile();
            User newUser = new User();
            newUser.mimicProfile(newProf); // sets necessary fields based on profile
            System.out.println(newProf.getDisplayName());

            System.out.println("User attributes: ");
            System.out.println(newUser.getId());
            System.out.println(newUser.getUserName());
            System.out.println(newUser.getImageUrl());
            System.out.println(newUser.getImageHeight());
            System.out.println(newUser.getImageWidth());
            System.out.println(newUser.getProduct());
            userRepository.save(newUser);
            model.addAttribute("currentUser", newUser);

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

    /*
    TODO:
    1. Create Radio Station object
        - RadioStation.java + RadioStationSeed.java
        - What fields do we need besides track id to create these?
        - Will also need RadioStationSong.java to save seed track into + additional tracks from Sam's app
        - These will be dependent on the RadioStation id, Seed + Track are linked to that table
        - Will also need User.java information to set the user id for these
    2. Save objects to be retrieved later
    3. Redirect to radio station screen
    4. Sam's App (Dont worry about for now):
        - Send seed track to Sam's application (post request?)
        - Receive list of tracks to start with (get request?)

    TODO:
    1. take in seed trackId and radioStation name from form
    2. create radiostation object with seedTrackName and stationName
    3. save radioStation
    4. create radioStationSong object

     */

    @PostMapping("/selectSeed")
    public ModelAndView selectSeed(@RequestParam(name="trackid") String trackid, ModelMap model){

        model.addAttribute("trackid", trackid);
        return new ModelAndView("redirect:/radioStation/" + trackid, model);
    }

    @GetMapping("/radioStation/{id}")
    public String viewRadioStation(@PathVariable(name="id") String trackid, Model model){
        model.addAttribute("trackid", trackid);

        return "radio";
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

    @PostMapping("/getRadioStationDetails")
    public RadioStation getRadioStationDetails(
                                               @RequestParam(name = "stationName", defaultValue = "", required = true) String stationName,
                                               @RequestParam(name = "trackId", defaultValue = "", required = true) long trackId,
                                               Model model){
        LocalDateTime createdAt = LocalDateTime.now();

        return new RadioStation(stationName, trackId, createdAt, model);
    }
}

