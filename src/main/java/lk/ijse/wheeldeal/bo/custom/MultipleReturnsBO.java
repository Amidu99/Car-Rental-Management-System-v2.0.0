package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.ReturnDTO;
import lk.ijse.wheeldeal.dto.RideVehicleDTO;
import java.sql.SQLException;
import java.util.List;

public interface MultipleReturnsBO extends SuperBO {
    List<String> getVehiNos(String rideNo) throws SQLException;
    boolean addDistance(RideVehicleDTO rideVehicleDTO) throws SQLException;
    double getVehiCostPerKM(String vehiNo) throws SQLException;
    double getCustomerDiscount(String rideNo) throws SQLException;
    boolean addReturn(ReturnDTO returnDTO) throws SQLException, ClassNotFoundException;
    double getDistance(String rideNo, String vehiNo) throws SQLException;
}