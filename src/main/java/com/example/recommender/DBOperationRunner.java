package com.example.recommender;

import com.example.recommender.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBOperationRunner {
    @Autowired
    UserRepository uRepo;

    public void run(String... args) {
        uRepo.saveAll(
                Arrays.asList(

                )
        ); // https://javatechonline.com/saving-data-into-database-using-spring-boot-data-jpa-step-by-step-tutorial/
    }
}
