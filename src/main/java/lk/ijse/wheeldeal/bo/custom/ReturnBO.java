package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.*;
import java.sql.SQLException;
import java.util.List;

public interface ReturnBO extends SuperBO {
    String generateNextReturnNo() throws SQLException, ClassNotFoundException;
    List<String> getPendingRideNos() throws SQLException;
    List<ReturnDTO> getLast20Returns() throws SQLException;
    int getCount(String value) throws SQLException;
    double getVehiCostPerKM(String rideNo) throws SQLException;
    double getCustomerDiscount(String rideNo) throws SQLException;
    boolean addReturn(ReturnDTO returnDTO) throws SQLException, ClassNotFoundException;
    boolean isReturned(String text) throws SQLException;
    String getLastReturnNo() throws SQLException;
    ReturnDTO getReturn(String returnNo) throws SQLException, ClassNotFoundException;
    RideDTO getRide(String rideNo) throws SQLException, ClassNotFoundException;
    CustomerDTO getCustomer(String custID) throws SQLException, ClassNotFoundException;
    MembershipDTO getMembership(String membCode) throws SQLException, ClassNotFoundException;
    String getVehicle(String rideNo) throws SQLException;
    VehicleDTO searchVehicle(String vehiNo) throws SQLException, ClassNotFoundException;
    String getMonthlyIncome(String year, String month) throws SQLException;
    String getAnnualIncome(String year) throws SQLException;
}