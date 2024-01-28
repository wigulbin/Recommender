package com.example.recommender.repositories;

import com.example.recommender.beans.RadioStationSong;
import com.example.recommender.beans.User;
import org.springframework.data.repository.CrudRepository;

public interface RadioStationSongRepository extends CrudRepository<RadioStationSong, Long> {

    RadioStationSong findById(long id);
}
