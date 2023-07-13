package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.custom.RideVehicleDAO;
import lk.ijse.wheeldeal.entity.RideVehicle;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RideVehicleDAOImpl implements RideVehicleDAO {

    @Override
    public List<String> getVehiNos(String rideNo) throws SQLException {
        List<String> vehiNos = new ArrayList<>();
        String sql = "SELECT VehiNo FROM ride_vehicle_details WHERE RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rideNo);
        while(resultSet.next()) {
            vehiNos.add(resultSet.getString(1));
        }
        return vehiNos;
    }


    @Override
    public List<RideVehicle> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RideVehicle search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(RideVehicle rideVehicle) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO ride_vehicle_details ( RideNo, VehiNo, RideDate ) VALUES ( ?, ?, ? )";
        return CrudUtil.execute(sql, rideVehicle.getRideNo(), rideVehicle.getVehiNO(), rideVehicle.getRideDate());
    }

    @Override
    public boolean update(RideVehicle dto) throws SQLException, ClassNotFoundException {
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
    public boolean isAvailableOnDate(String vehiNo, LocalDate rideDate) throws SQLException {
        boolean isAvailable = true;
        String sql = "SELECT * FROM ride_vehicle_details WHERE VehiNo = ? AND RideDate = ?";
        ResultSet resultSet = CrudUtil.execute(sql, vehiNo, rideDate);
        if(resultSet.next()){
            isAvailable = false;
        }
        return isAvailable;
    }

    @Override
    public String getVehicle(String rideNo) throws SQLException {
        String sql = "SELECT * FROM ride_vehicle_details WHERE RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rideNo);
        if(resultSet.next()) {
            return resultSet.getString(2);
        }
        return null;
    }

    @Override
    public boolean isAvailable(String rideNo) throws SQLException {
        String sql = "SELECT * FROM ride_vehicle_details WHERE RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rideNo);
        return resultSet.next();
    }

    @Override
    public int getCount(String rideNo) throws SQLException {
        String sql = "SELECT * FROM ride_vehicle_details WHERE RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rideNo);
        int count=0;
        while (resultSet.next()) {
            count++;
        }
        return count;
    }

    @Override
    public boolean addDistance(RideVehicle rideVehicle) throws SQLException {
        String sql = "UPDATE ride_vehicle_details set Distance = ? WHERE VehiNo = ? AND RideNo = ?";
        return CrudUtil.execute(sql, rideVehicle.getDistance(), rideVehicle.getVehiNO(), rideVehicle.getRideNo());
    }

    @Override
    public double getDistance(String rideNo, String vehiNo) throws SQLException {
        String sql = "SELECT Distance FROM ride_vehicle_details WHERE VehiNo = ? AND RideNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, vehiNo, rideNo);
        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;
    }
}