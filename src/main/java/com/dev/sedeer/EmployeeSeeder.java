package com.dev.sedeer;

import com.dev.entity.Company;
import com.dev.entity.Employee;
import com.dev.repo.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class EmployeeSeeder implements CommandLineRunner {

    private final CompanyRepo companyRepo;

    @Override
    public void run(String... args) {

        Company company = new Company();
        company.setName("E Corp");
        company.setCity("New York City");
        company.setStreet(null);

        Set<Employee> employees = new HashSet<>();

        Employee employee = new Employee();
        employee.setCompany(company);
        employee.setLastName("Spencer");
        employee.setStreet("High Street 123");
        employee.setCity("Newark");
        employees.add(employee);

        employee = new Employee();
        employee.setCompany(company);
        employee.setLastName("Ralbern");
        employee.setStreet("57th Street");
        employee.setCity("New York City");
        employees.add(employee);

        company.setEmployees(employees);

        companyRepo.save(company);
    }
}
