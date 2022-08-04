package com.dev.sedeer;

import com.dev.entity.Employee;
import com.dev.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EmployeeSeeder implements CommandLineRunner {

    private final EmployeeRepo employeeRepo;

    @Override
    public void run(String... args) {

        employeeRepo.saveAll(List.of(new Employee(null,"Hamza","Soukaina","Fez",null),
                new Employee(null,"Omar","Casa","Casa",null) ));
    }
}
