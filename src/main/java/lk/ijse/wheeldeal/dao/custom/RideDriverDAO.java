package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.RideDriver;
import java.sql.SQLException;
import java.time.LocalDate;

public interface RideDriverDAO extends CrudDAO<RideDriver> {
    boolean isNotAvailableOnDate(String driverID, LocalDate rideDate) throws SQLException;
    boolean add(RideDriver rideDriver) throws SQLException;
    boolean isAvailable(String rideNo) throws SQLException;
}