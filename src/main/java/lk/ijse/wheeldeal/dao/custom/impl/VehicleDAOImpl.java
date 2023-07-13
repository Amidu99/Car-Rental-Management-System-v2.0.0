package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.RideVehicleDAO;
import lk.ijse.wheeldeal.dao.custom.VehicleDAO;
import lk.ijse.wheeldeal.entity.Vehicle;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public List<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        List<Vehicle> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM vehicle");
        while (resultSet.next()) {
            data.add(new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));
        }
        return data;
    }

    @Override
    public Vehicle search(String vehiNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM vehicle WHERE VehiNo = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, vehiNo);
        if(resultSet.next()){
            return new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public boolean add(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO vehicle ( VehiNo, VehiType, Model, CostPerKM, Availability ) VALUES ( ?, ?, ?, ?, ? )";
        return CrudUtil.execute(sql, vehicle.getVehiNo(), vehicle.getVehiType(), vehicle.getModel(), vehicle.getCostPerKM(), vehicle.getAvailability());
    }

    @Override
    public boolean update(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE vehicle set VehiType = ?, Model = ?, CostPerKM = ?, Availability = ? WHERE VehiNo = ?";
        return CrudUtil.execute(sql, vehicle.getVehiType(), vehicle.getModel(), vehicle.getCostPerKM(), vehicle.getAvailability(), vehicle.getVehiNo());
    }

    @Override
    public boolean delete(String vehiNo) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM vehicle WHERE VehiNo = ?";
        return CrudUtil.execute(sql, vehiNo);
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT VehiNo FROM vehicle ORDER BY VehiNo DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()){
            String id = resultSet.getString(1);
            int newId = Integer.parseInt(id.replace("V", "")) + 1;
            return String.format("V%03d", newId);
        }
        return "V001";
    }

    @Override
    public List<Vehicle> getAllAvailableVehicles(LocalDate rideDate) throws SQLException {
        List<Vehicle> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM vehicle WHERE Availability = 'Available'");
        while (resultSet.next()) {
            String vehiNo = resultSet.getString(1);
            String vehiType = resultSet.getString(2);
            String model = resultSet.getString(3);
            double costPerKM = resultSet.getDouble(4);
            String availability = resultSet.getString(5);
            RideVehicleDAO rideVehicleDAO = (RideVehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_VEHICLE);
            if (rideVehicleDAO.isAvailableOnDate(vehiNo, rideDate)) {
                data.add(new Vehicle(vehiNo, vehiType, model, costPerKM, availability));
            }
        }
        return data;
    }

    @Override
    public double getVehiCostPerKM(String vehiNo) throws SQLException {
        String sql = "SELECT * FROM vehicle WHERE VehiNo = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, vehiNo);
        if(resultSet.next()){
            return  resultSet.getDouble(4);
        }
        return -1;
    }

    @Override
    public String getVehicleModel(String vehiNo) throws SQLException {
        String sql = "SELECT Model FROM vehicle WHERE VehiNo = ?;";
        ResultSet resultSet = CrudUtil.execute(sql, vehiNo);
        if(resultSet.next()){
            return  resultSet.getString(1);
        }
        return null;
    }

    @Override
    public double getVehicleCount(String type) throws SQLException {
        String sql = "SELECT * FROM vehicle WHERE VehiType = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, type);
        double count = 0;
        while(resultSet.next()) {
            count++;
        }
        return count;
    }

    @Override
    public InputStream getImage(String vehiNo) throws SQLException {
        String sql ="SELECT Image FROM vehicleimage Where VehiNo = ?";
        ResultSet resultSet = CrudUtil.execute(sql, vehiNo);
        if(resultSet.next()) {
            return resultSet.getBinaryStream(1);
        }
        return null;
    }

    @Override
    public boolean isEmpty(String vehiNo) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT Image FROM vehicleimage Where VehiNo = ?", vehiNo);
        return !result.next();
    }

    @Override
    public boolean addImage(FileInputStream fis, String vehiNo) throws SQLException {
        String sql = "INSERT INTO vehicleimage (Image, VehiNo) VALUES (?, ?)";
        return CrudUtil.execute(sql, fis, vehiNo);
    }

    @Override
    public boolean updateImage(FileInputStream fis, String vehiNo) throws SQLException {
        String sql = "UPDATE vehicleimage SET Image=? WHERE VehiNo=? ";
        return CrudUtil.execute(sql, fis, vehiNo);
    }
}