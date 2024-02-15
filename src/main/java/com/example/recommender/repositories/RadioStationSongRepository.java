package com.example.recommender.repositories;

import com.example.recommender.tables.RadioStationSong;
import org.springframework.data.repository.CrudRepository;

public interface RadioStationSongRepository extends CrudRepository<RadioStationSong, Long> {

    RadioStationSong findById(long id);
}
