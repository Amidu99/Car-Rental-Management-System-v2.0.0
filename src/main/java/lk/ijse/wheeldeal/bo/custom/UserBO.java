package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.UserDTO;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUser() throws SQLException, ClassNotFoundException;
    UserDTO searchUser(String id) throws SQLException, ClassNotFoundException;
    boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    String generateNextID() throws SQLException, ClassNotFoundException;
    String getPasswordHint(String userID) throws SQLException;
    boolean isAvailableUser(String empId) throws SQLException;
    boolean isAdmin(String password) throws SQLException;
    boolean verifyLogin(String username, String password) throws SQLException;
}