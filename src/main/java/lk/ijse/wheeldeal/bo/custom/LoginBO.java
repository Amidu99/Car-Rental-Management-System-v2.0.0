package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.UserDTO;
import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean verifyLogin(String username, String password) throws SQLException;
    boolean checkEmployee(String empId) throws SQLException;
    String getEmployeeName(String empId) throws SQLException;
    String getEmployeeRole(String empId) throws SQLException;
    boolean isAvailableUser(String empId) throws SQLException;
    String generateNextUserID() throws SQLException, ClassNotFoundException;
    boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    String getPasswordHint(String userID) throws SQLException;
}