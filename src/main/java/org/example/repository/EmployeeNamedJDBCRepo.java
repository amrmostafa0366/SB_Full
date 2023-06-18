package org.example.repository;

import org.example.mapper.EmployeeMapper;
import org.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.config.JdbcNamespaceHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class EmployeeNamedJDBCRepo implements EmployeeReps{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return 0;//jdbcTemplate.queryForObject("SELECT count(*) FROM employees;",Integer.class);
    }

    @Override
    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT id,name,salary FROM employees WHERE id=:id;",
                new MapSqlParameterSource("id",id)
                ,new EmployeeMapper());

    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT id,name,salary FROM employees",new EmployeeMapper());
    }

    @Override
    public int insert(Employee employee) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",employee.getId());
        map.addValue("name",employee.getName());
        map.addValue("salary",employee.getSalary());
        return jdbcTemplate.update("INSERT INTO employees (id,name,salary) VALUES(:id,:name,:salary)",map);
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("update employees set name= :name , salary = :salary",
                new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete from employees where id = :id",
                new MapSqlParameterSource("id",id));
    }
}
