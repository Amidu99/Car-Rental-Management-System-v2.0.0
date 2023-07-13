package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.DriverDTO;
import lk.ijse.wheeldeal.dto.RideDriverDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RideDriverBO extends SuperBO {
    List<DriverDTO> getAllAvailableDrivers(LocalDate rideDate) throws SQLException;
    boolean addRide(RideDriverDTO rideDriverDTO) throws SQLException, ClassNotFoundException;
}
