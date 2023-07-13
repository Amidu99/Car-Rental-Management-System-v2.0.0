package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.RideVehicle;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RideVehicleDAO extends CrudDAO<RideVehicle> {
    List<String> getVehiNos(String rideNo) throws SQLException;
    boolean isAvailableOnDate(String vehiNo, LocalDate rideDate) throws SQLException;
    String getVehicle(String rideNo) throws SQLException;
    boolean isAvailable(String rideNo) throws SQLException;
    int getCount(String rideNo) throws SQLException;
    boolean addDistance(RideVehicle rideVehicle) throws SQLException;
    double getDistance(String rideNo, String vehiNo) throws SQLException;
}