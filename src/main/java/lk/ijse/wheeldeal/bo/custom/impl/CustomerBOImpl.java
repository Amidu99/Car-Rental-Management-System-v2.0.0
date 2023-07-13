package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.CustomerBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.CustomerDAO;
import lk.ijse.wheeldeal.dao.custom.MembershipDAO;
import lk.ijse.wheeldeal.dto.CustomerDTO;
import lk.ijse.wheeldeal.entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    //with Factory Design Pattern
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP);

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        List<CustomerDTO> allCustomers = new ArrayList<>();
        List<Customer> customers = customerDAO.getAll();
        for (Customer c : customers){
            allCustomers.add(new CustomerDTO(c.getCustID(), c.getID(), c.getName(), c.getTel(), c.getMembCode()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getCustID(), dto.getID(), dto.getName(), dto.getTel(), dto.getMembCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustID(), dto.getID(), dto.getName(), dto.getTel(), dto.getMembCode()));
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNextID();
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(c.getCustID(), c.getID(), c.getName(), c.getTel(), c.getMembCode());
    }

    @Override
    public List<String> getMembershipCodes() throws SQLException {
        return membershipDAO.getCodes();
    }
}