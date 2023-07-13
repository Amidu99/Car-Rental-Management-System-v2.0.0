package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.Membership;
import java.sql.SQLException;
import java.util.List;

public interface MembershipDAO extends CrudDAO<Membership> {
    List<String> getCodes() throws SQLException;
    double getDiscount(String membCode) throws SQLException;
}