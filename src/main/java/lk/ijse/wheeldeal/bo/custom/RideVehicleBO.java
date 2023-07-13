package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.RideVehicleDTO;
import lk.ijse.wheeldeal.dto.VehicleDTO;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RideVehicleBO extends SuperBO {
    List<VehicleDTO> getAllAvailableVehicles(LocalDate rideDate) throws SQLException;
    InputStream getVehicleImage(String vehiNo) throws SQLException, ClassNotFoundException;
    boolean addRide(RideVehicleDTO rideVehicleDTO) throws SQLException, ClassNotFoundException;
}