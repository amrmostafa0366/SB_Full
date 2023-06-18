package org.example.controller;

import org.example.model.Employee;
import org.example.repository.EmployeeReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeReps employeeReps;

    @GetMapping("/count")
    public int countEmployees(){
        return employeeReps.count();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable(value = "id") Long id){
        return employeeReps.findById(id);
    }
    @GetMapping("")
    public List<Employee> findById(){
        return employeeReps.findAll();
    }
}
