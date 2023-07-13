package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.ReturnBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.*;
import lk.ijse.wheeldeal.dto.*;
import lk.ijse.wheeldeal.entity.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnBOImpl implements ReturnBO {
    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RETURN);
    RideDAO rideDAO = (RideDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE);
    RideVehicleDAO rideVehicleDAO = (RideVehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RIDE_VEHICLE);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public String generateNextReturnNo() throws SQLException, ClassNotFoundException {
        return returnDAO.generateNextID();
    }

    @Override
    public List<String> getPendingRideNos() throws SQLException {
        return rideDAO.getPendingRideNos();
    }

    @Override
    public List<ReturnDTO> getLast20Returns() throws SQLException {
        List<ReturnDTO> last20 = new ArrayList<>();
        List<Return> returns = returnDAO.getLast20Returns();
        for (Return r : returns){
            last20.add(new ReturnDTO(r.getReturnNo(), r.getRideNo(), r.getDistance(), r.getCost(), r.getReDate()));
        }
        return last20;
    }

    @Override
    public int getCount(String rideNo) throws SQLException {
        return rideVehicleDAO.getCount(rideNo);
    }

    @Override
    public double getVehiCostPerKM(String rideNo) throws SQLException {
        String vehiNo = rideVehicleDAO.getVehicle(rideNo);
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
        return returnDAO.add(new Return(dto.getReturnNo(),dto.getRideNo(), dto.getDistance(), dto.getCost(), dto.getReDate()));
    }

    @Override
    public boolean isReturned(String returnNo) throws SQLException {
        return returnDAO.isReturned(returnNo);
    }

    @Override
    public String getLastReturnNo() throws SQLException {
        return returnDAO.getLastReturnNo();
    }

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
    public String getVehicle(String rideNo) throws SQLException {
        return rideVehicleDAO.getVehicle(rideNo);
    }

    @Override
    public VehicleDTO searchVehicle(String vehiNo) throws SQLException, ClassNotFoundException {
        Vehicle v = vehicleDAO.search(vehiNo);
        return new VehicleDTO(v.getVehiNo(), v.getVehiType(), v.getModel(), v.getCostPerKM(), v.getAvailability());
    }

    @Override
    public String getMonthlyIncome(String year, String month) throws SQLException {
        return returnDAO.getMonthlyIncome(year, month);
    }

    @Override
    public String getAnnualIncome(String year) throws SQLException {
        return returnDAO.getAnnualIncome(year);
    }
}