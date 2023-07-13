package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.RideVehicleBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.RideVehicleDAO;
import lk.ijse.wheeldeal.dao.custom.VehicleDAO;
import lk.ijse.wheeldeal.dto.RideVehicleDTO;
import lk.ijse.wheeldeal.dto.VehicleDTO;
import lk.ijse.wheeldeal.entity.RideVehicle;
import lk.ijse.wheeldeal.entity.Vehicle;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RideVehicleBOImpl implements RideVehicleBO {
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    RideVehicleDAO rideVehicleDAO = (RideVehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_VEHICLE);

    @Override
    public List<VehicleDTO> getAllAvailableVehicles(LocalDate rideDate) throws SQLException {
        List<Vehicle> all = vehicleDAO.getAllAvailableVehicles(rideDate);
        List<VehicleDTO> allVehicles = new ArrayList<>();
        for (Vehicle v: all){
            allVehicles.add(new VehicleDTO(v.getVehiNo(), v.getVehiType(), v.getModel(), v.getCostPerKM(), v.getAvailability()));
        }
        return allVehicles;
    }

    @Override
    public InputStream getVehicleImage(String vehiNo) throws SQLException {
        return vehicleDAO.getImage(vehiNo);
    }

    @Override
    public boolean addRide(RideVehicleDTO dto) throws SQLException, ClassNotFoundException {
        return rideVehicleDAO.add(new RideVehicle(dto.getRideNo(), dto.getVehiNO(), dto.getRideDate()));
    }
}