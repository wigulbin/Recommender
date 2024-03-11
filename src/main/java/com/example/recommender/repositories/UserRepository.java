package com.example.recommender.repositories;

import com.example.recommender.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {


    User findById(long id); // this is a method with no body where spring does a query or database action based off the name of the method
    // https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
}
