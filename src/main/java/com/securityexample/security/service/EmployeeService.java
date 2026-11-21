package com.securityexample.security.service;

import com.securityexample.security.dto.EmployeeRequestDTO;
import com.securityexample.security.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(EmployeeRequestDTO request);

    Employee updateEmployee(Long id, EmployeeRequestDTO request);

    void deleteEmployee(Long id);

    List<Employee> getEmployeesByDepartment(String department);

    Employee getEmployeeByEmail(String email);
}
