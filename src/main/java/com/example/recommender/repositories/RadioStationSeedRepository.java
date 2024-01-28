package com.example.recommender.repositories;

import com.example.recommender.beans.RadioStationSeed;
import com.example.recommender.beans.User;
import org.springframework.data.repository.CrudRepository;

public interface RadioStationSeedRepository extends CrudRepository<User, Long> {

    RadioStationSeed findById(long id);
}
