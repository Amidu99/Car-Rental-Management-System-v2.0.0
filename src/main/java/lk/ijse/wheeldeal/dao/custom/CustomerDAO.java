package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    double getMembCount(String code) throws SQLException;
    String getCustomerName(String custID) throws SQLException;
    String getCustomerMemb(String custID) throws SQLException;
    List<String> getCustomerIDs() throws SQLException;
}