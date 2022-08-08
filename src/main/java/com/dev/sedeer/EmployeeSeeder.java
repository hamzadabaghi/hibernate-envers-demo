package com.dev.sedeer;

import com.dev.entity.Company;
import com.dev.entity.Employee;
import com.dev.repo.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class EmployeeSeeder implements CommandLineRunner {

    private final CompanyRepo companyRepo;
    private final AuditReader auditReader;

    @Override
    public void run(String... args) {

        /* Persisting data - insertion*/
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

        /* apply a modification */
        Company cp = companyRepo.findById(1L).get();
        cp.setName("Leyton");
        companyRepo.save(cp);

        Date revisionDate;
        /* get revisions */
        List<Number> revisions = auditReader.getRevisions(Company.class, 1L);
        for (Number rev : revisions) {
            revisionDate = auditReader.getRevisionDate(rev);
            System.out.println(revisionDate);
        }


    }
}
