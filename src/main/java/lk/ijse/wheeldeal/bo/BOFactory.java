package lk.ijse.wheeldeal.bo;

import lk.ijse.wheeldeal.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        BILL, CUSTOMER, DASHBOARD, DRIVER, EMPLOYEE, LOGIN, MEMBERSHIP, MULTIPLE_RETURN, RETURN, RIDE, RIDE_DRIVER, RIDE_VEHICLE, USER, VEHICLE, VEHICLE_IMAGE
    }

    public SuperBO getBO(BOFactory.BOTypes type) {
        switch (type) {
            case BILL:
                return new BillBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MEMBERSHIP:
                return new MembershipBOImpl();
            case MULTIPLE_RETURN:
                return new MultipleReturnsBOImpl();
            case RETURN:
                return new ReturnBOImpl();
            case RIDE:
                return new RideBOImpl();
            case RIDE_DRIVER:
                return new RideDriverBOImpl();
            case RIDE_VEHICLE:
                return new RideVehicleBOImpl();
            case USER:
                return new UserBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            default:
                return null;
        }
    }
}