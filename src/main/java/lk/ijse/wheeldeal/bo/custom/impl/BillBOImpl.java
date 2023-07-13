package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.BillBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.*;
import lk.ijse.wheeldeal.dto.*;
import lk.ijse.wheeldeal.entity.*;
import java.sql.SQLException;

public class BillBOImpl implements BillBO {
    //with Factory Design Pattern
    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RETURN);
    RideDAO rideDAO = (RideDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE);
    RideVehicleDAO rideVehicleDAO = (RideVehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_VEHICLE);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public ReturnDTO getReturn(String returnNo) throws SQLException, ClassNotFoundException {
        Return r = returnDAO.search(returnNo);
        return new ReturnDTO(r.getReturnNo(), r.getRideNo(), r.getDistance(), r.getCost(), r.getReDate());
    }

    @Override
    public RideDTO getRide(String rideNo) throws SQLException, ClassNotFoundException {
        Ride r = rideDAO.search(rideNo);
        return new RideDTO(r.getRideNo(), r.getCustID(), r.getRideDate(), r.getStatus());
    }

    @Override
    public CustomerDTO getCustomer(String custID) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(custID);
        return new CustomerDTO(c.getCustID(), c.getID(), c.getName(), c.getTel(), c.getMembCode());
    }

    @Override
    public MembershipDTO getMembership(String membCode) throws SQLException, ClassNotFoundException {
        Membership m = membershipDAO.search(membCode);
        return new MembershipDTO(m.getCode(), m.getType(), m.getDiscount(), m.getFee());
    }

    @Override
    public String getVehicleNo(String rideNo) throws SQLException {
        return rideVehicleDAO.getVehicle(rideNo);
    }

    @Override
    public VehicleDTO getVehicle(String vehiNo) throws SQLException, ClassNotFoundException {
        Vehicle v = vehicleDAO.search(vehiNo);
        return new VehicleDTO(v.getVehiNo(), v.getVehiType(), v.getModel(), v.getCostPerKM(), v.getAvailability());
    }

    @Override
    public String getReturnDate(String returnNo) throws SQLException {
        return returnDAO.getDate(returnNo);
    }
}