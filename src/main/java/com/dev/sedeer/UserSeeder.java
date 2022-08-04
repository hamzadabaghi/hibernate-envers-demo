package com.dev.sedeer;

import com.dev.entity.Student;
import com.dev.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepo userRepo;


    @Override
    public void run(String... args) throws Exception {
        userRepo.saveAll(List.of(new Student(null,"hamza","hamza"),
                                 new Student(null,"Ossama","Ossama")));
    }
}
