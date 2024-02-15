package com.example.recommender.repositories;

import com.example.recommender.tables.RadioStationSeed;
import org.springframework.data.repository.CrudRepository;

public interface RadioStationSeedRepository extends CrudRepository<RadioStationSeed, Long> {

    RadioStationSeed findById(long id);
}
