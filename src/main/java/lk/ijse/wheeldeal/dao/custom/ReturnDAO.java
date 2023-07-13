package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.Return;
import java.sql.SQLException;
import java.util.List;

public interface ReturnDAO extends CrudDAO<Return> {
    List<Return> getLast20Returns() throws SQLException;
    String getDate(String returnNo) throws SQLException;
    boolean isReturned(String returnNo) throws SQLException;
    String getLastReturnNo() throws SQLException;
    String getMonthlyIncome(String year, String month) throws SQLException;
    String getAnnualIncome(String year) throws SQLException;
}