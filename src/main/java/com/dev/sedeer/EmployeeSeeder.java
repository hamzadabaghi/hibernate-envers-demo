package com.dev.sedeer;

import com.dev.entity.Company;
import com.dev.entity.Employee;
import com.dev.entity.envers.CustomRevisionEntity;
import com.dev.repo.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
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
        Company company1 = new Company();
        company1.setName("E Corp");
        company1.setCity("New York City");
        company1.setStreet(null);

        Company company2 = new Company();
        company2.setName("Google");
        company2.setCity("USA");
        company2.setStreet(null);

        Set<Employee> employees_c1 = new HashSet<>();
        Set<Employee> employees_c2 = new HashSet<>();

        Employee employee1 = new Employee();
        employee1.setCompany(company1);
        employee1.setLastName("Spencer");
        employee1.setStreet("High Street 123");
        employee1.setCity("Newark");
        employees_c1.add(employee1);

        Employee employee2 = new Employee();
        employee2.setCompany(company2);
        employee2.setLastName("Ralbern");
        employee2.setStreet("57th Street");
        employee2.setCity("New York City");
        employees_c2.add(employee2);

        company1.setEmployees(employees_c1);
        company2.setEmployees(employees_c2);
        companyRepo.save(company1);
        companyRepo.save(company2);

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

        /* get username of the user */
        CustomRevisionEntity revision = auditReader.findRevision(CustomRevisionEntity.class,
                1);
        String username = revision.getUsername();
        System.out.println(username);

        /* get the entity by id and rev id : the state of a company at a given revision */
        Company comp = auditReader.find(Company.class, 1L, 2);
        String name = comp.getName();
        String street = comp.getStreet();
        System.out.println(name);
        System.out.println(street);

        /* audit query : advanced queries */
        AuditQuery query = auditReader.createQuery().forEntitiesAtRevision(Employee.class, 2);
        query.add(AuditEntity.relatedId("company").eq(2L));
        for (Employee e : (List<Employee>) query.getResultList()) {
            System.out.println(e.getId() + ": " + e.getLastName());
        }





    }
}
