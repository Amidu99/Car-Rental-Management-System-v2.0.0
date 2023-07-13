package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.*;
import java.sql.SQLException;

public interface BillBO extends SuperBO {
    ReturnDTO getReturn(String returnNo) throws SQLException, ClassNotFoundException;
    RideDTO getRide(String rideNo) throws SQLException, ClassNotFoundException;
    CustomerDTO getCustomer(String custID) throws SQLException, ClassNotFoundException;
    MembershipDTO getMembership(String membCode) throws SQLException, ClassNotFoundException;
    String getVehicleNo(String rideNo) throws SQLException;
    VehicleDTO getVehicle(String vehiNo) throws SQLException, ClassNotFoundException;
    String getReturnDate(String returnNo) throws SQLException;
}