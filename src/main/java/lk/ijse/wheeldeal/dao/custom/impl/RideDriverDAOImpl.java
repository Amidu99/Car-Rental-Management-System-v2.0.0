package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.custom.RideDriverDAO;
import lk.ijse.wheeldeal.entity.RideDriver;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RideDriverDAOImpl implements RideDriverDAO {
    @Override
    public List<RideDriver> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RideDriver search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(RideDriver dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean isNotAvailableOnDate(String driverID, LocalDate rideDate) throws SQLException {
        boolean isNotAvailable = true;
        String sql = "SELECT * FROM ride_driver_details WHERE DriverID = ? AND RideDate = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, driverID, rideDate);
        if(resultSet.next()){
            isNotAvailable = false;
        }
        return isNotAvailable;
    }

    @Override
    public boolean add(RideDriver rideDriver) throws SQLException {
        String sql = "INSERT INTO ride_driver_details ( RideNo, DriverID, RideDate ) VALUES ( ?, ?, ? )";
        return CrudUtil.execute(sql, rideDriver.getRideNo(), rideDriver.getDriverID(), rideDriver.getRideDate());
    }

    @Override
    public boolean isAvailable(String rideNo) throws SQLException {
        String sql = "SELECT * FROM ride_driver_details WHERE RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rideNo);
        return resultSet.next();
    }
}
