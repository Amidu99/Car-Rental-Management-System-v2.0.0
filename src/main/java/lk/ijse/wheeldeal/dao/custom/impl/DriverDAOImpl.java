package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.custom.DriverDAO;
import lk.ijse.wheeldeal.entity.Driver;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {

    @Override
    public List<Driver> getAll() throws SQLException {
        List<Driver> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM driver");
        while (resultSet.next()) {
            data.add(new Driver(
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
    public List<Driver> getAllAvailableDrivers(LocalDate rideDate) throws SQLException {
        List<Driver> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM driver WHERE Availability = 'Available'");
        while (resultSet.next()) {
            String driverID = resultSet.getString(1);
            String name = resultSet.getString(2);
            String location = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String availability = resultSet.getString(5);

            data.add(new Driver(driverID, name, location, tel, availability));
        }
        return data;
    }

    @Override
    public String generateNextID() throws SQLException {
        String sql = "SELECT DriverID FROM driver ORDER BY DriverID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()){
            String id = resultSet.getString(1);
            int newId = Integer.parseInt(id.replace("D", "")) + 1;
            return String.format("D%03d", newId);
        }
        return "D001";
    }

    @Override
    public Driver search(String driverID) throws SQLException {
        String sql = "SELECT * FROM driver WHERE DriverID = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, driverID);
        if(resultSet.next()){
            Driver driver = new Driver(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            return driver;
        }
        return null;
    }

    @Override
    public boolean add(Driver driver) throws SQLException {
        String sql = "INSERT INTO driver ( DriverID, Name, Location, Tel, Availability ) VALUES ( ?, ?, ?, ?, ? )";
        return CrudUtil.execute(sql, driver.getDriverID(), driver.getName(), driver.getLocation(), driver.getTel(), driver.getAvailability());
    }

    @Override
    public boolean update(Driver driver) throws SQLException {
        String sql = "UPDATE driver set Name = ?, Location = ?, Tel = ?, Availability = ? WHERE DriverID = ?";
        return CrudUtil.execute(sql, driver.getName(), driver.getLocation(), driver.getTel(), driver.getAvailability(), driver.getDriverID());
    }

    @Override
    public boolean delete(String driverId) throws SQLException {
        String sql = "DELETE FROM driver WHERE DriverID = ?";
        return CrudUtil.execute(sql, driverId);
    }
}