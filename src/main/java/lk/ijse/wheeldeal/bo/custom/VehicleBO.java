package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.VehicleDTO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {
    String generateNextVehicleNo() throws SQLException, ClassNotFoundException;
    List<VehicleDTO> getAllVehicles() throws SQLException, ClassNotFoundException;
    boolean addVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;
    boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;
    boolean deleteVehicle(String vehiNo) throws SQLException, ClassNotFoundException;
    VehicleDTO getVehicle(String vehiNo) throws SQLException, ClassNotFoundException;
    boolean isEmpty(String vehiNo) throws SQLException;
    InputStream getImage(String vehiNo) throws SQLException, ClassNotFoundException;
    boolean addImage(FileInputStream fis, String vehiNo) throws SQLException;
    boolean updateImage(FileInputStream fis, String vehiNo) throws SQLException;
}