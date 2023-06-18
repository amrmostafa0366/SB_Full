package org.example.mapper;

import org.example.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override

    public Employee mapRow(ResultSet rs,int rowNumber) throws SQLException {
        return new Employee(rs.getLong("id"),rs.getString("name"),rs.getDouble("salary"));
    }
}
