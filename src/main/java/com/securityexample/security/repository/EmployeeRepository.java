package com.securityexample.security.repository;

import com.securityexample.security.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartmentIgnoreCase(String department);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
