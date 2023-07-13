package lk.ijse.wheeldeal.dao;

import lk.ijse.wheeldeal.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, DRIVER, EMPLOYEE, MEMBERSHIP, RETURN, RIDE, RIDE_DRIVER, RIDE_VEHICLE, USER, VEHICLE, VEHICLE_IMAGE
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MEMBERSHIP:
                return new MembershipDAOImpl();
            case RETURN:
                return new ReturnDAOImpl();
            case RIDE:
                return new RideDAOImpl();
            case RIDE_DRIVER:
                return new RideDriverDAOImpl();
            case RIDE_VEHICLE:
                return new RideVehicleDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            default:
                return null;
        }
    }
}