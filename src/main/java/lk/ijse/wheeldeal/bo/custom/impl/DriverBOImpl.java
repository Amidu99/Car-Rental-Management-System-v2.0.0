package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.DriverBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.DriverDAO;
import lk.ijse.wheeldeal.dto.DriverDTO;
import lk.ijse.wheeldeal.entity.Driver;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriverBOImpl implements DriverBO {

    //with Factory Design Pattern
    DriverDAO driverDAO = (DriverDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DRIVER);

    @Override
    public List<DriverDTO> getAllDriver() throws SQLException, ClassNotFoundException {
        List<Driver> all = driverDAO.getAll();
        List<DriverDTO> allDrivers = new ArrayList<>();
        for (Driver d : all){
            allDrivers.add(new DriverDTO(d.getDriverID(), d.getName(), d.getLocation(), d.getTel(), d.getAvailability()));
        }
        return allDrivers;
    }

    @Override
    public List<DriverDTO> getAllAvailableDrivers(LocalDate rideDate) throws SQLException {
        List<Driver> all = driverDAO.getAllAvailableDrivers(rideDate);
        List<DriverDTO> allAvailable = new ArrayList<>();
        for (Driver d : all){
            allAvailable.add(new DriverDTO(d.getDriverID(), d.getName(), d.getLocation(), d.getTel(), d.getAvailability()));
        }
        return allAvailable;
    }

    @Override
    public DriverDTO searchDriver(String driverID) throws SQLException, ClassNotFoundException {
        Driver d = driverDAO.search(driverID);
        return new DriverDTO(d.getDriverID(), d.getName(), d.getLocation(), d.getTel(), d.getAvailability());
    }

    @Override
    public boolean addDriver(DriverDTO dto) throws SQLException, ClassNotFoundException {
        return driverDAO.add(new Driver(dto.getDriverID(), dto.getName(), dto.getLocation(), dto.getTel(), dto.getAvailability()));
    }

    @Override
    public boolean updateDriver(DriverDTO dto) throws SQLException, ClassNotFoundException {
        return driverDAO.update(new Driver(dto.getDriverID(), dto.getName(), dto.getLocation(), dto.getTel(), dto.getAvailability()));
    }

    @Override
    public boolean deleteDriver(String driverId) throws SQLException, ClassNotFoundException {
        return driverDAO.delete(driverId);
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return driverDAO.generateNextID();
    }
}