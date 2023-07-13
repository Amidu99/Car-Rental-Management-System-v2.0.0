package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.RideBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.*;
import lk.ijse.wheeldeal.dto.RideDTO;
import lk.ijse.wheeldeal.entity.Ride;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RideBOImpl implements RideBO {
    //with Factory Design Pattern
    RideDAO rideDAO = (RideDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE);
    RideDriverDAO rideDriverDAO = (RideDriverDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_DRIVER);
    RideVehicleDAO rideVehicleDAO = (RideVehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_VEHICLE);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public List<RideDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Ride> all = rideDAO.getAll();
        List<RideDTO> allRides = new ArrayList<>();
        for (Ride r : all){
            allRides.add(new RideDTO(r.getRideNo(), r.getCustID(), r.getRideDate(), r.getStatus()));
        }
        return allRides;
    }

    @Override
    public RideDTO searchRide(String rideNo) throws SQLException, ClassNotFoundException {
        Ride r = rideDAO.search(rideNo);
        return new RideDTO(r.getRideNo(), r.getCustID(), r.getRideDate(), r.getStatus());
    }

    @Override
    public boolean addRide(RideDTO dto) throws SQLException, ClassNotFoundException {
        return rideDAO.add(new Ride(dto.getRideNo(), dto.getCustID(), dto.getRideDate(), dto.getStatus()));
    }

    @Override
    public boolean update(RideDTO dto) throws SQLException, ClassNotFoundException {
        return rideDAO.update(new Ride(dto.getRideNo(), dto.getCustID(), dto.getRideDate(), dto.getStatus()));
    }

    @Override
    public boolean deleteRide(String rideNo) throws SQLException, ClassNotFoundException {
        return rideDAO.delete(rideNo);
    }

    @Override
    public String generateNextRideID() throws SQLException, ClassNotFoundException {
        return rideDAO.generateNextID();
    }

    @Override
    public RideDTO getLastRide() throws SQLException {
        Ride r = rideDAO.getLastRide();
        return new RideDTO(r.getRideNo(), r.getCustID(), r.getRideDate(), r.getStatus());
    }

    @Override
    public List<RideDTO> getAllPending() throws SQLException {
        List<Ride> all = rideDAO.getAllPending();
        List<RideDTO> allRides = new ArrayList<>();
        for (Ride r : all){
            allRides.add(new RideDTO(r.getRideNo(), r.getCustID(), r.getRideDate(), r.getStatus()));
        }
        return allRides;
    }

    @Override
    public double getCustomerDiscount(String rideNo) throws SQLException {
        String custID = rideDAO.getCustomerID(rideNo);
        String membCode = customerDAO.getCustomerMemb(custID);
        return membershipDAO.getDiscount(membCode);
    }

    @Override
    public List<String> getPendingRideNos() throws SQLException {
        return rideDAO.getPendingRideNos();
    }

    @Override
    public boolean updateStatus(String rideNo) throws SQLException {
        return rideDAO.updateStatus(rideNo);
    }

    @Override
    public List<String> getCustomerIDs() throws SQLException {
        return customerDAO.getCustomerIDs();
    }

    @Override
    public String getCustomerName(String custID) throws SQLException {
        return customerDAO.getCustomerName(custID);
    }

    @Override
    public String getCustomerMemb(String custID) throws SQLException {
        return customerDAO.getCustomerMemb(custID);
    }

    @Override
    public String getVehicle(String rideNo) throws SQLException {
        return rideVehicleDAO.getVehicle(rideNo);
    }

    @Override
    public String getVehicleModel(String vehiNo) throws SQLException {
        return vehicleDAO.getVehicleModel(vehiNo);
    }

    @Override
    public InputStream getImage(String vehiNo) throws SQLException {
        return vehicleDAO.getImage(vehiNo);
    }

    @Override
    public boolean isAvailableVehicle(String rideNo) throws SQLException {
        return rideVehicleDAO.isAvailable(rideNo);
    }

    @Override
    public boolean isAvailableDriver(String rideNo) throws SQLException {
        return rideDriverDAO.isAvailable(rideNo);
    }
}