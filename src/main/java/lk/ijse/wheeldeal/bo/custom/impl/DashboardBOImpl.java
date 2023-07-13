package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.DashboardBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.CustomerDAO;
import lk.ijse.wheeldeal.dao.custom.UserDAO;
import lk.ijse.wheeldeal.dao.custom.VehicleDAO;
import lk.ijse.wheeldeal.dto.VehicleDTO;
import lk.ijse.wheeldeal.entity.Vehicle;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DashboardBOImpl implements DashboardBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public double getMembCount(String code) throws SQLException {
        return customerDAO.getMembCount(code);
    }

    @Override
    public double getVehicleCount(String type) throws SQLException {
        return vehicleDAO.getVehicleCount(type);
    }

    @Override
    public List<VehicleDTO> getAllAvailableVehicles(LocalDate rideDate) throws SQLException {
        List<Vehicle> vehicleList = vehicleDAO.getAllAvailableVehicles(rideDate);
        List<VehicleDTO> allVehicleList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            allVehicleList.add(new VehicleDTO(vehicle.getVehiNo(),vehicle.getVehiType(),vehicle.getModel(), vehicle.getCostPerKM(), vehicle.getAvailability()));
        }
        return allVehicleList;
    }

    @Override
    public boolean isAdmin(String password) throws SQLException {
        return userDAO.isAdmin(password);
    }

    @Override
    public InputStream getImage(String vehiNo) throws SQLException {
        return vehicleDAO.getImage(vehiNo);
    }
}