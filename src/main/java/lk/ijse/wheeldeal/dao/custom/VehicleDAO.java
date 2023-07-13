package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.Vehicle;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    List<Vehicle> getAllAvailableVehicles(LocalDate rideDate) throws SQLException;
    double getVehiCostPerKM(String vehiNo) throws SQLException;
    String getVehicleModel(String vehiNo) throws SQLException;
    double getVehicleCount(String type) throws SQLException;
    InputStream getImage(String vehiNo) throws SQLException;
    boolean isEmpty(String vehiNo) throws SQLException;
    boolean addImage(FileInputStream fis, String vehiNo) throws SQLException;
    boolean updateImage(FileInputStream fis, String vehiNo) throws SQLException;
}