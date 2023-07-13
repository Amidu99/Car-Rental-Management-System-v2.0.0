package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.Employee;
import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {
    boolean isAdmin(String empID) throws SQLException;
    boolean checkEmployee(String empId) throws SQLException;
    String getEmployeeName(String empId) throws SQLException;
    String getEmployeeRole(String empId) throws SQLException;
}