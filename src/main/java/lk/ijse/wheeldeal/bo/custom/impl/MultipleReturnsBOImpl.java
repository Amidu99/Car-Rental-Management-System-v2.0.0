package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.MultipleReturnsBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.*;
import lk.ijse.wheeldeal.dto.ReturnDTO;
import lk.ijse.wheeldeal.dto.RideVehicleDTO;
import lk.ijse.wheeldeal.entity.Return;
import lk.ijse.wheeldeal.entity.RideVehicle;
import java.sql.SQLException;
import java.util.List;

public class MultipleReturnsBOImpl implements MultipleReturnsBO {
    RideVehicleDAO rideVehicleDAO = (RideVehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_VEHICLE);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    RideDAO rideDAO = (RideDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP);
    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RETURN);

    @Override
    public List<String> getVehiNos(String rideNo) throws SQLException {
        return rideVehicleDAO.getVehiNos(rideNo);
    }

    @Override
    public boolean addDistance(RideVehicleDTO dto) throws SQLException {
        return rideVehicleDAO.addDistance(new RideVehicle(dto.getRideNo(), dto.getVehiNO(), dto.getDistance()));
    }

    @Override
    public double getVehiCostPerKM(String vehiNo) throws SQLException {
        return vehicleDAO.getVehiCostPerKM(vehiNo);
    }

    @Override
    public double getCustomerDiscount(String rideNo) throws SQLException {
        String custID = rideDAO.getCustomerID(rideNo);
        String membCode = customerDAO.getCustomerMemb(custID);
        return membershipDAO.getDiscount(membCode);
    }

    @Override
    public boolean addReturn(ReturnDTO dto) throws SQLException, ClassNotFoundException {
        return returnDAO.add(new Return(dto.getReturnNo(), dto.getRideNo(), dto.getDistance(), dto.getCost(), dto.getReDate()));
    }

    @Override
    public double getDistance(String rideNo, String vehiNo) throws SQLException {
        return rideVehicleDAO.getDistance(rideNo, vehiNo);
    }
}