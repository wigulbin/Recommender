package com.example.recommender.repositories;

import com.example.recommender.tables.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository  extends CrudRepository<User, Long> {


    User findById(long id);
}
