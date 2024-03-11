package com.example.recommender.spotify.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public class SpotifyProfile {

    @JsonProperty("country")
    private String country;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("explicit_content")
    private ExplicitContent explicitContent;
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    @JsonProperty("followers")
    private Followers followers;

    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    private String id;
    @JsonProperty("images")
    private List<ImageObject> images = new ArrayList<>();
    @JsonProperty("product")
    private String product ="No product found.";
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;

    public SpotifyProfile() {}

//    @Override
//    public String toString() {
//        return String.format(
//                "User[id='%s', displayName='%s', product='%s', ]",
//                id, displayName, );
//    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImageObject> getImage() {
        return images;
    }

    public void setImage(List<ImageObject> image) {
        this.images = image;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
