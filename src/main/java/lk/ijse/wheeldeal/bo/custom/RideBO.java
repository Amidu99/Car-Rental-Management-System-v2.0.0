package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.RideDTO;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface RideBO extends SuperBO {
    List<RideDTO> getAll() throws SQLException, ClassNotFoundException;
    RideDTO searchRide(String no) throws SQLException, ClassNotFoundException;
    boolean addRide(RideDTO dto) throws SQLException, ClassNotFoundException;
    boolean update(RideDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteRide(String no) throws SQLException, ClassNotFoundException;
    String generateNextRideID() throws SQLException, ClassNotFoundException;
    RideDTO getLastRide() throws SQLException;
    List<RideDTO> getAllPending() throws SQLException;
    double getCustomerDiscount(String rideNo) throws SQLException;
    List<String> getPendingRideNos() throws SQLException;
    boolean updateStatus(String rideNo) throws SQLException;
    List<String> getCustomerIDs() throws SQLException;
    String getCustomerName(String custID) throws SQLException;
    String getCustomerMemb(String custID) throws SQLException;
    String getVehicle(String rideNo) throws SQLException;
    String getVehicleModel(String vehiNo) throws SQLException;
    InputStream getImage(String vehiNo) throws SQLException, ClassNotFoundException;
    boolean isAvailableVehicle(String rideNo) throws SQLException;
    boolean isAvailableDriver(String rideNo) throws SQLException;
}