package org.example.repository;

import org.example.model.Employee;

import java.util.List;

public interface EmployeeReps {
    int count();
    Employee findById(Long id);
    List<Employee> findAll();
    int insert(Employee employee);
    int update(Employee employee);
    int delete(Long id);

}
