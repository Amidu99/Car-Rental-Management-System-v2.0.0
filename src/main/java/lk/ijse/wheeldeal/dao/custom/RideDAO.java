package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.Ride;
import java.sql.SQLException;
import java.util.List;

public interface RideDAO extends CrudDAO<Ride> {
    Ride getLastRide() throws SQLException;
    List<Ride> getAllPending() throws SQLException;
    String getCustomerID(String rideNo) throws SQLException;
    List<String> getPendingRideNos() throws SQLException;
    boolean updateStatus(String rideNo) throws SQLException;
}