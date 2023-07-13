package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.entity.Customer;
import lk.ijse.wheeldeal.util.CrudUtil;
import lk.ijse.wheeldeal.dao.custom.CustomerDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer search(String custID) throws SQLException {
        String sql = "SELECT * FROM customer WHERE CustID = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, custID);
        if(resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
        while (resultSet.next()) {
            data.add(new Customer(
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
    public String generateNextID() throws SQLException{
        String sql = "SELECT CustID FROM customer ORDER BY CustID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()){
            String id = resultSet.getString(1);
            int newId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C%03d", newId);
        }
        return "C001";
    }

    @Override
    public List<String> getCustomerIDs() throws SQLException {
        List<String> codes = new ArrayList<>();
        String sql = "SELECT CustID FROM customer";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public boolean add(Customer c) throws SQLException {
        String sql = "INSERT INTO customer ( CustID, ID, Name, Tel, MembCode ) VALUES ( ?, ?, ?, ?, ? )";
        return CrudUtil.execute(sql, c.getCustID(), c.getID(), c.getName(), c.getTel(), c.getMembCode());
    }

    @Override
    public boolean update(Customer c) throws SQLException {
        String sql = "UPDATE customer set ID = ?, Name = ?, Tel = ?, MembCode = ? WHERE CustID = ?";
        return CrudUtil.execute(sql, c.getID(), c.getName(), c.getTel(), c.getMembCode(), c.getCustID());
    }

    @Override
    public boolean delete(String custId) throws SQLException {
        String sql = "DELETE FROM customer WHERE CustID = ?";
        return CrudUtil.execute(sql, custId);
    }

    @Override
    public String getCustomerMemb(String custID) throws SQLException {
        String sql = "SELECT MembCode FROM customer WHERE CustID = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, custID);
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getCustomerName(String custID) throws SQLException {
        String sql = "SELECT Name FROM customer WHERE CustID = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, custID);
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public double getMembCount(String code) throws SQLException {
        String sql = "SELECT * FROM customer WHERE MembCode = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, code);
        double count = 0;
        while(resultSet.next()) {
            count++;
        }
        return count;
    }
}