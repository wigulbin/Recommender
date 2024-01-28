package com.example.recommender.repositories;

import com.example.recommender.beans.RadioStation;
import com.example.recommender.beans.User;
import org.springframework.data.repository.CrudRepository;

public interface RadioStationRepository extends CrudRepository<RadioStation, Long> {

    RadioStation findById(long id);
}
