package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.UserBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.UserDAO;
import lk.ijse.wheeldeal.dto.UserDTO;
import lk.ijse.wheeldeal.entity.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    //with Factory Design Pattern
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public List<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        List<UserDTO> allUsers = new ArrayList<>();
        List<User> users = userDAO.getAll();
        for (User user : users) {
            allUsers.add(new UserDTO(user.getUserID(), user.getUserName(), user.getPassword(), user.getPassHint(), user.getEmpID()));
        }
        return allUsers;
    }

    @Override
    public UserDTO searchUser(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(id);
        return new UserDTO (user.getUserID(), user.getUserName(), user.getPassword(), user.getPassHint(), user.getEmpID());
    }

    @Override
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.add(new User(dto.getUserID(), dto.getUserName(), dto.getPassword(), dto.getPassHint(), dto.getEmpID()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(dto.getUserID(), dto.getUserName(), dto.getPassword(), dto.getPassHint(), dto.getEmpID()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return userDAO.generateNextID();
    }

    @Override
    public String getPasswordHint(String userID) throws SQLException {
        return userDAO.getPasswordHint(userID);
    }

    @Override
    public boolean isAvailableUser(String empId) throws SQLException {
        return userDAO.isAvailableUser(empId);
    }

    @Override
    public boolean isAdmin(String password) throws SQLException {
        return userDAO.isAdmin(password);
    }

    @Override
    public boolean verifyLogin(String username, String password) throws SQLException {
        return userDAO.verifyLogin(username,password);
    }
}