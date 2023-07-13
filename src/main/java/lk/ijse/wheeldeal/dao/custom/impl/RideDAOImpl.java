package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.custom.RideDAO;
import lk.ijse.wheeldeal.entity.Ride;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RideDAOImpl implements RideDAO {

    @Override
    public String generateNextID() throws SQLException {
        String sql = "SELECT RideNo FROM ride ORDER BY RideNo DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            int newId = Integer.parseInt(id.replace("R", "")) + 1;
            return String.format("R%03d", newId);
        }
        return "R001";
    }


    @Override
    public Ride getLastRide() throws SQLException {
        String sql = "SELECT * FROM ride ORDER BY RideNo DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()){
            return new Ride(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    @Override
    public List<Ride> getAllPending() throws SQLException {
        List<Ride> data = new ArrayList<>();
        String sql = "SELECT * FROM ride WHERE Status = 'Pending'";
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Ride(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    @Override
    public boolean add(Ride ride) throws SQLException {
        String sql = "INSERT INTO ride ( RideNo, CustID, RideDate, Status ) VALUES ( ?, ?, ?, ? )";
        return CrudUtil.execute(sql, ride.getRideNo(), ride.getCustID(), ride.getRideDate(), ride.getStatus());
    }

    @Override
    public boolean update(Ride ride) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String rideNo) throws SQLException {
        String sql = "DELETE FROM ride WHERE RideNo = ?";
        return CrudUtil.execute(sql, rideNo);
    }

    @Override
    public String getCustomerID(String rideNo) throws SQLException {
        String sql = "SELECT * FROM ride WHERE RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rideNo);
        if (resultSet.next()) {
            return resultSet.getString(2);
        }
        return null;
    }

    @Override
    public List<Ride> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Ride search(String rideNo) throws SQLException {
        String sql = "SELECT * FROM ride WHERE RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rideNo);
        if(resultSet.next()){
            return new Ride(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    @Override
    public List<String> getPendingRideNos() throws SQLException {
        List<String> nos = new ArrayList<>();
        String sql = "SELECT * FROM ride WHERE Status = 'Pending'";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()) {
            nos.add(resultSet.getString(1));
        }
        return nos;
    }

    @Override
    public boolean updateStatus(String rideNo) throws SQLException {
        String sql = "UPDATE ride set Status = 'Returned' WHERE RideNo = ?";
        return CrudUtil.execute(sql, rideNo);
    }
}