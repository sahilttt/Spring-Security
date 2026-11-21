package com.securityexample.security.config;

import com.securityexample.security.entity.Employee;
import com.securityexample.security.entity.Order;
import com.securityexample.security.entity.OrderStatus;
import com.securityexample.security.entity.User;
import com.securityexample.security.repository.EmployeeRepository;
import com.securityexample.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public DataInitializer(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (employeeRepository.count() == 0) {
            log.info("Seeding initial employee data into H2 database...");
            employeeRepository.saveAll(List.of(
                    new Employee("Alice Johnson", 29, "Engineering", "alice.johnson@example.com"),
                    new Employee("Bob Smith", 34, "Product", "bob.smith@example.com"),
                    new Employee("Catherine Davis", 41, "Human Resources", "catherine.davis@example.com"),
                    new Employee("David Miller", 26, "Marketing", "david.miller@example.com"),
                    new Employee("Elena Rostova", 38, "Finance", "elena.rostova@example.com")
            ));
            log.info("Successfully seeded 5 employees.");
        }

        if (userRepository.count() == 0) {
            log.info("Seeding initial users and orders into H2 database...");

            User user1 = new User("John Doe", "john.doe@example.com");
            user1.addOrder(new Order("Mechanical Keyboard", 1, new BigDecimal("129.99"), OrderStatus.DELIVERED));
            user1.addOrder(new Order("Wireless Mouse", 2, new BigDecimal("59.98"), OrderStatus.SHIPPED));

            User user2 = new User("Jane Smith", "jane.smith@example.com");
            user2.addOrder(new Order("27-inch 4K Monitor", 1, new BigDecimal("399.99"), OrderStatus.PLACED));
            user2.addOrder(new Order("USB-C Docking Station", 1, new BigDecimal("89.50"), OrderStatus.DELIVERED));

            User user3 = new User("Robert Brown", "robert.brown@example.com");
            user3.addOrder(new Order("Noise-Cancelling Headphones", 1, new BigDecimal("249.00"), OrderStatus.PLACED));

            userRepository.saveAll(List.of(user1, user2, user3));
            log.info("Successfully seeded 3 users and 5 linked orders.");
        }
    }
}
