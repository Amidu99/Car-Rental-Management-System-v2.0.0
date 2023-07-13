package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.custom.EmployeeDAO;
import lk.ijse.wheeldeal.entity.Employee;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
        while (resultSet.next()) {
            data.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    @Override
    public boolean isAdmin(String empID) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmpID = ?";
        ResultSet resultSet = CrudUtil.execute(sql, empID);
        if(resultSet.next()){
            String jobRole = resultSet.getString(3);
            return jobRole.equalsIgnoreCase("owner") || jobRole.equalsIgnoreCase("manager");
        }
        return false;
    }

    @Override
    public Employee search(String empID) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmpID = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, empID);
        if(resultSet.next()){
            System.out.println("result awa");
            Employee employee = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            System.out.println("return");
            return employee;
        }
        return null;
    }

    @Override
    public boolean add(Employee e) throws SQLException {
        String sql = "INSERT INTO employee ( EmpID, Name, JobRole, Tel ) VALUES ( ?, ?, ?, ? )";
        return CrudUtil.execute(sql, e.getEmpID(), e.getName(), e.getJobRole(), e.getTel());
    }

    @Override
    public boolean update(Employee e) throws SQLException {
        String sql = "UPDATE employee SET Name = ?, JobRole = ?, Tel = ? WHERE EmpID = ?";
        return CrudUtil.execute(sql, e.getName(), e.getJobRole(), e.getTel(), e.getEmpID());
    }

    @Override
    public boolean delete(String empId) throws SQLException {
        String sql = "DELETE FROM employee WHERE EmpID = ?";
        return CrudUtil.execute(sql, empId);
    }

    @Override
    public boolean checkEmployee(String empId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmpID = ?";
        ResultSet resultSet = CrudUtil.execute(sql, empId);
        return resultSet.next();
    }

    @Override
    public String getEmployeeName(String empId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmpID = ?";
        ResultSet resultSet = CrudUtil.execute(sql,empId);
        if(resultSet.next()) {
            return resultSet.getString(2);
        }
        return null;
    }

    @Override
    public String getEmployeeRole(String empId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmpID = ?";
        ResultSet resultSet = CrudUtil.execute(sql,empId);
        if(resultSet.next()) {
            return resultSet.getString(3);
        }
        return null;
    }

    @Override
    public String generateNextID() throws SQLException {
        String sql = "SELECT EmpID FROM employee ORDER BY EmpID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            String id = resultSet.getString(1);
            int newId = Integer.parseInt(id.replace("E", "")) + 1;
            return String.format("E%03d", newId);
        }
        return "E001";
    }
}