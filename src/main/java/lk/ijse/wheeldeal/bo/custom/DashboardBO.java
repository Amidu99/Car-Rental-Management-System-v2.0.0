package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.VehicleDTO;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DashboardBO extends SuperBO {
    double getMembCount(String code) throws SQLException;
    double getVehicleCount(String type) throws SQLException;
    List<VehicleDTO> getAllAvailableVehicles(LocalDate rideDate) throws SQLException;
    boolean isAdmin(String password) throws SQLException;
    InputStream getImage(String vehiNo) throws SQLException,ClassNotFoundException;
}