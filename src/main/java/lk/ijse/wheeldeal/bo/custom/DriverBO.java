package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.DriverDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DriverBO extends SuperBO {
    List<DriverDTO> getAllDriver() throws SQLException, ClassNotFoundException;
    List<DriverDTO> getAllAvailableDrivers(LocalDate rideDate) throws SQLException;
    DriverDTO searchDriver(String driverID) throws SQLException, ClassNotFoundException;
    boolean addDriver(DriverDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateDriver(DriverDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteDriver(String driverId) throws SQLException, ClassNotFoundException;
    String generateNextID() throws SQLException, ClassNotFoundException;
}