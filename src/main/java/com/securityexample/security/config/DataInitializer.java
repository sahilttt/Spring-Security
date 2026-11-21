package com.securityexample.security.config;

import com.securityexample.security.entity.Employee;
import com.securityexample.security.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final EmployeeRepository employeeRepository;

    public DataInitializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        if (employeeRepository.count() == 0) {
            log.info("Seeding initial employee data into H2 database...");

            List<Employee> initialEmployees = List.of(
                    new Employee("Alice Johnson", 29, "Engineering", "alice.johnson@example.com"),
                    new Employee("Bob Smith", 34, "Product", "bob.smith@example.com"),
                    new Employee("Catherine Davis", 41, "Human Resources", "catherine.davis@example.com"),
                    new Employee("David Miller", 26, "Marketing", "david.miller@example.com"),
                    new Employee("Elena Rostova", 38, "Finance", "elena.rostova@example.com")
            );

            employeeRepository.saveAll(initialEmployees);
            log.info("Successfully seeded {} employees.", initialEmployees.size());
        } else {
            log.info("Employee database already contains data; skipping seeding.");
        }
    }
}
