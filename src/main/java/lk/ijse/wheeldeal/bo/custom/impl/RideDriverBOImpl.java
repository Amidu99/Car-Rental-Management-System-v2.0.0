package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.RideDriverBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.DriverDAO;
import lk.ijse.wheeldeal.dao.custom.RideDriverDAO;
import lk.ijse.wheeldeal.dto.DriverDTO;
import lk.ijse.wheeldeal.dto.RideDriverDTO;
import lk.ijse.wheeldeal.entity.Driver;
import lk.ijse.wheeldeal.entity.RideDriver;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RideDriverBOImpl implements RideDriverBO {
    DriverDAO driverDAO = (DriverDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DRIVER);
    RideDriverDAO rideDriverDAO = (RideDriverDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_DRIVER);

    @Override
    public List<DriverDTO> getAllAvailableDrivers(LocalDate rideDate) throws SQLException {
        List<Driver> allAvailableDrivers = driverDAO.getAllAvailableDrivers(rideDate);
        List<DriverDTO> driversOnThisDay = new ArrayList<>();
        for (Driver d : allAvailableDrivers){
            if (rideDriverDAO.isNotAvailableOnDate(d.getDriverID(), rideDate)) {
                driversOnThisDay.add(new DriverDTO(d.getDriverID(), d.getName(), d.getLocation(), d.getTel(), d.getAvailability()));
            }
        }
        return driversOnThisDay;
    }

    @Override
    public boolean addRide(RideDriverDTO dto) throws SQLException {
        return rideDriverDAO.add(new RideDriver(dto.getRideNo(), dto.getDriverID(), dto.getRideDate()));
    }
}