package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.EmployeeDTO;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
    EmployeeDTO searchEmployee(String empID) throws SQLException, ClassNotFoundException;
    boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException;
    boolean isAdmin(String empID) throws SQLException;
    boolean checkEmployee(String empId) throws SQLException;
    String generateNextID() throws SQLException, ClassNotFoundException;
    String getEmployeeName(String empId) throws SQLException;
    String getEmployeeRole(String empId) throws SQLException;
}