package com.example.recommender.spotify.logic;

import com.example.recommender.api.Common;
import com.example.recommender.beans.Album;
import com.example.recommender.beans.SpotifyProfile;
import com.example.recommender.beans.Track;
import com.example.recommender.beans.Artist;
import com.example.recommender.spotify.data.SearchResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotifyClient {
    private AccessToken token = AccessToken.createToken(clientid, clientSecret);


    private static String clientid = "";
    private static String clientSecret = "";
    private static final String REDIRECT_URL = "http://localhost:8080/callback";

    static {
        try {
            String path = Common.getResourcePath("keys.json");
            Map<String, Object> keyMap = Common.parseJsonStringForMap(Files.readString(Path.of(path)));

            clientid = (String) keyMap.get("clientid");
            clientSecret = (String) keyMap.get("clientSecret");
        }
        catch (Exception e){}
    }

     public SpotifyClient(String code)
     {
         this.token = AccessToken.createToken(code);
     }


    public Track findTrack(String trackID) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.spotify.com/v1/tracks/" + trackID)).header("Authorization", "Bearer " + token.getAccessToken()).GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataJson = objectMapper.readValue(postResponse.body(), new TypeReference<HashMap<String, Object>>() {});

        return new Track(dataJson);
    }
    public List<Track> findTracks() { return null;}

    public Artist findArtist(String artistID) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.spotify.com/v1/artists/" + artistID)).header("Authorization", "Bearer " + token.getAccessToken()).GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataJson = objectMapper.readValue(postResponse.body(), new TypeReference<HashMap<String, Object>>() {});

        return new Artist(dataJson);
    }
    public List<Artist> findArtist() { return null;}

    public SearchResult searchApiForTrack(String query) throws IOException, InterruptedException, URISyntaxException{
        //TODO URL Encode query
        return searchApi(query, "track");
    }

    public SearchResult searchApi(String query, String typeString) throws IOException, InterruptedException, URISyntaxException{
        //TODO URL Encode query
        HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.spotify.com/v1/search?q=" + URLEncoder.encode(query) + "&type=" + typeString)).header("Authorization", "Bearer " + token.getAccessToken()).GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(postResponse.body(), SearchResult.class);
    }

    public Album findAlbum(String albumID) throws IOException, InterruptedException, URISyntaxException{
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/albums/" + albumID))
                .header("Authorization", "Bearer " + token.getAccessToken())
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataJson = objectMapper.readValue(postResponse.body(), new TypeReference<Map<String, Object>>() {});

        return new Album(dataJson);
    }

    public SpotifyProfile findProfile() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/me"))
                .header("Authorization", "Bearer " + token.getAccessToken())
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());


        return new SpotifyProfile();
    }

    public static String getClientid() {
        return clientid;
    }

    protected static String getClientSecret() {
        return clientSecret;
    }

    public static String getRedirectURL()
    {
        return REDIRECT_URL;
    }

    public static String getEncodedRedirectURL()
    {
        return URLEncoder.encode(REDIRECT_URL, StandardCharsets.UTF_8);
    }

}
