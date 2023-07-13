package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.custom.ReturnDAO;
import lk.ijse.wheeldeal.db.DBConnection;
import lk.ijse.wheeldeal.entity.Return;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnDAOImpl implements ReturnDAO {

    @Override
    public List<Return> getAll() throws SQLException, ClassNotFoundException {
        List<Return> data = new ArrayList<>();
        String sql = "SELECT * FROM returns";
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Return(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));
        }
        return data;
    }

    @Override
    public Return search(String returnNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM returns WHERE ReturnNo = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, returnNo);
        if(resultSet.next()){
            Return aReturn = new Return(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            );
            return aReturn;
        }
        return null;
    }

    @Override
    public boolean add(Return r) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String sql1 = "UPDATE ride set Status = 'Returned' WHERE RideNo = ?";
            boolean isUpdated = CrudUtil.execute(sql1, r.getRideNo());

            if(isUpdated){
                String sql = "INSERT INTO returns ( ReturnNo, RideNo, Distance, Cost, Redate ) VALUES ( ?, ?, ?, ?, ? )";
                boolean isAdded = CrudUtil.execute(sql, r.getReturnNo(), r.getRideNo(), r.getDistance(), r.getCost(), r.getReDate());
                if(isAdded){
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
            assert connection != null;
            connection.rollback();
            return false;
        } finally {
            assert connection != null;
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean update(Return aReturn) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ReturnNo FROM returns ORDER BY ReturnNo DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            int newId = Integer.parseInt(id.replace("H", "")) + 1;
            return String.format("H%03d", newId);
        }
        return "H001";
    }

    @Override
    public List<Return> getLast20Returns() throws SQLException {
        List<Return> data = new ArrayList<>();
        String sql = "SELECT * FROM returns ORDER BY ReturnNo DESC LIMIT 20";
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Return(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));
        }
        return data;
    }

    @Override
    public String getDate(String returnNo) throws SQLException {
        String sql = "SELECT ReDate FROM returns WHERE ReturnNo = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, returnNo);
        String date = null;
        if(resultSet.next()){
            date = resultSet.getString(1);
        }
        return date;
    }

    @Override
    public boolean isReturned(String returnNo) throws SQLException {
        String sql = "SELECT * FROM returns WHERE ReturnNo = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, returnNo);
        return resultSet.next();
    }

    @Override
    public String getLastReturnNo() throws SQLException {
        String sql = "SELECT ReturnNo FROM returns ORDER BY ReturnNo DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getMonthlyIncome(String year, String month) throws SQLException {
        String sql = "SELECT SUM(Cost) FROM returns WHERE monthname(ReDate) = ? AND year(ReDate) = ? ";
        ResultSet resultSet = CrudUtil.execute(sql,month,year);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getAnnualIncome(String year) throws SQLException {
        String sql = "SELECT SUM(Cost) FROM returns WHERE year(ReDate) = ? ";
        ResultSet resultSet = CrudUtil.execute(sql,year);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}