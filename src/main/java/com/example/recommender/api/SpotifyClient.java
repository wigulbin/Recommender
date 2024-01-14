package com.example.recommender.api;

import com.example.recommender.beans.Album;
import com.example.recommender.beans.Track;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotifyClient {
    private AccessToken token = AccessToken.createToken(clientid, clientSecret);


    private static String clientid = "";
    private static String clientSecret = "";

    static {
        try {
            String path = Common.getResourcePath("keys.json");
            Map<String, Object> keyMap = Common.parseJsonStringForMap(Files.readString(Path.of(path)));

            clientid = (String) keyMap.get("clientid");
            clientSecret = (String) keyMap.get("clientSecret");
        }
        catch (Exception e){}
    }

     public SpotifyClient()
     {

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


    public Map<String, Object> searchApi(String query) throws IOException, InterruptedException, URISyntaxException{
        //TODO URL Encode query
        HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.spotify.com/v1/search?q=" + query + "&type=album,track,artist")).header("Authorization", "Bearer " + token.getAccessToken()).GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> dataJson = objectMapper.readValue(postResponse.body(), new TypeReference<HashMap<String, Object>>() {});
        return dataJson;
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
}
