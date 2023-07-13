package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.User;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    String getPasswordHint(String userID) throws SQLException;
    boolean isAvailableUser(String empId) throws SQLException;
    boolean isAdmin(String password) throws SQLException;
    boolean verifyLogin(String username, String password) throws SQLException;
}