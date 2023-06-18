package org.example.repository;

import org.example.mapper.EmployeeMapper;
import org.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeJDBCRepo implements EmployeeReps{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM employees;",Integer.class);
    }

    @Override
    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT id,name,salary FROM employees WHERE id=?;",new Object[]{id},new EmployeeMapper());

    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT id,name,salary FROM employees",new EmployeeMapper());
    }

    @Override
    public int insert(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employees (id,name,salary) VALUES(?,?,?)",
                new Object [] {employee.getId(), employee.getName(), employee.getSalary()});
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("update employees set name= ? , salary = ?",
                new Object [] {employee.getName(), employee.getSalary()});
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete from employees where id = ?",
                new Object [] {id});
    }
}
