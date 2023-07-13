package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.LoginBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.EmployeeDAO;
import lk.ijse.wheeldeal.dao.custom.UserDAO;
import lk.ijse.wheeldeal.dto.UserDTO;
import lk.ijse.wheeldeal.entity.User;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean verifyLogin(String username, String password) throws SQLException {
        return userDAO.verifyLogin(username, password);
    }

    @Override
    public boolean checkEmployee(String empId) throws SQLException {
        return employeeDAO.checkEmployee(empId);
    }

    @Override
    public String getEmployeeName(String empId) throws SQLException {
        return employeeDAO.getEmployeeName(empId);
    }

    @Override
    public String getEmployeeRole(String empId) throws SQLException {
        return employeeDAO.getEmployeeRole(empId);
    }

    @Override
    public boolean isAvailableUser(String empId) throws SQLException {
        return userDAO.isAvailableUser(empId);
    }

    @Override
    public String generateNextUserID() throws SQLException, ClassNotFoundException {
        return userDAO.generateNextID();
    }

    @Override
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException{
        return userDAO.add(new User(dto.getUserID(), dto.getUserName(), dto.getPassword(), dto.getPassHint(), dto.getEmpID()));
    }

    @Override
    public String getPasswordHint(String userID) throws SQLException {
        return userDAO.getPasswordHint(userID);
    }
}