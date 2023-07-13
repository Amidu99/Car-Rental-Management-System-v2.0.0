package lk.ijse.wheeldeal.dao.custom;

import lk.ijse.wheeldeal.dao.CrudDAO;
import lk.ijse.wheeldeal.entity.Driver;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DriverDAO extends CrudDAO<Driver> {
    List<Driver> getAllAvailableDrivers(LocalDate rideDate) throws SQLException;
}
