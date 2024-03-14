package com.example.recommender.repositories;

import com.example.recommender.spotify.data.SpotifyProfile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<SpotifyProfile, Long> {
    SpotifyProfile findById(long id);
}
