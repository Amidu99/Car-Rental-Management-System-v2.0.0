package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.VehicleBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.VehicleDAO;
import lk.ijse.wheeldeal.dto.VehicleDTO;
import lk.ijse.wheeldeal.entity.Vehicle;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public String generateNextVehicleNo() throws SQLException, ClassNotFoundException {
        return vehicleDAO.generateNextID();
    }

    @Override
    public List<VehicleDTO> getAllVehicles() throws SQLException, ClassNotFoundException {
        List<Vehicle> all = vehicleDAO.getAll();
        List<VehicleDTO> allVehicles = new ArrayList<>();
        for (Vehicle v : all){
            allVehicles.add(new VehicleDTO(v.getVehiNo(), v.getVehiType(), v.getModel(), v.getCostPerKM(), v.getAvailability()));
        }
        return allVehicles;
    }

    @Override
    public boolean addVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.add(new Vehicle(dto.getVehiNo(), dto.getVehiType(), dto.getModel(), dto.getCostPerKM(), dto.getAvailability()));
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(dto.getVehiNo(), dto.getVehiType(), dto.getModel(), dto.getCostPerKM(), dto.getAvailability()));
    }

    @Override
    public boolean deleteVehicle(String vehiNo) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(vehiNo);
    }

    @Override
    public VehicleDTO getVehicle(String vehiNo) throws SQLException, ClassNotFoundException {
        Vehicle v = vehicleDAO.search(vehiNo);
        return new VehicleDTO(v.getVehiNo(), v.getVehiType(), v.getModel(), v.getCostPerKM(), v.getAvailability());
    }

    @Override
    public boolean isEmpty(String vehiNo) throws SQLException {
        return vehicleDAO.isEmpty(vehiNo);
    }

    @Override
    public InputStream getImage(String vehiNo) throws SQLException, ClassNotFoundException {
        return vehicleDAO.getImage(vehiNo);
    }

    @Override
    public boolean addImage(FileInputStream fis, String vehiNo) throws SQLException {
        return vehicleDAO.addImage(fis, vehiNo);
    }

    @Override
    public boolean updateImage(FileInputStream fis, String vehiNo) throws SQLException {
        return vehicleDAO.updateImage(fis, vehiNo);
    }
}