package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.EmployeeDAO;
import lk.ijse.wheeldeal.dao.custom.UserDAO;
import lk.ijse.wheeldeal.entity.User;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() throws SQLException {
        List<User> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM user");
        while (resultSet.next()) {
            data.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return data;
    }

    @Override
    public boolean add(User user) throws SQLException {
        String sql = "INSERT INTO user ( UserID, UserName, Password, PassHint, EmpID ) VALUES ( ?, ?, ?, ?, ? )";
        return CrudUtil.execute(sql, user.getUserID(), user.getUserName(), user.getPassword(), user.getPassHint(), user.getEmpID());
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE user SET UserID = ?, UserName = ?, Password = ?, PassHint = ? WHERE EmpID = ?";
        return CrudUtil.execute(sql, user.getUserID(), user.getUserName(), user.getPassword(), user.getPassHint(), user.getEmpID());
    }

    @Override
    public boolean delete(String userID) throws SQLException {
        String sql = "DELETE FROM user WHERE UserID = ?";
        return CrudUtil.execute(sql, userID);
    }

    @Override
    public User search(String userID) throws SQLException {
        String sql = "SELECT * FROM user WHERE UserID = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, userID);
        if(resultSet.next()){
            User user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            return user;
        }
        return null;
    }

    @Override
    public boolean verifyLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE UserName = ? AND Password = ?";
        ResultSet resultSet = CrudUtil.execute(sql, username, password);
        return resultSet.next();
    }

    @Override
    public boolean isAdmin(String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE Password = ?";
        ResultSet resultSet = CrudUtil.execute(sql, password);
        if(resultSet.next()) {
            String empID = resultSet.getString(5);
            EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
            return employeeDAO.isAdmin(empID);
        }
        return false;
    }

    @Override
    public boolean isAvailableUser(String empId) throws SQLException {
        String sql = "SELECT * FROM user WHERE EmpID = ?";
        ResultSet resultSet = CrudUtil.execute(sql,empId);
        return resultSet.next();
    }

    @Override
    public String generateNextID() throws SQLException {
        String sql = "SELECT UserID FROM user ORDER BY UserID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            String id = resultSet.getString(1);
            int newId = Integer.parseInt(id.replace("U", "")) + 1;
            return String.format("U%03d", newId);
        }
        return "U001";
    }

    @Override
    public String getPasswordHint(String userID) throws SQLException {
        String sql = "SELECT * FROM user WHERE UserID = ?";
        ResultSet resultSet = CrudUtil.execute(sql, userID);
        if(resultSet.next()) {
            return resultSet.getString(4);
        }
        return null;
    }
}