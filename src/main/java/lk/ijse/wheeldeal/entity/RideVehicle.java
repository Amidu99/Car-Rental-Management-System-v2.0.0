package lk.ijse.wheeldeal.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class RideVehicle {
    private String RideNo;
    private String VehiNO;
    private String RideDate;
    private double Distance;

    public RideVehicle(String rideNo, String vehiNo, double distance) {
        this.RideNo = rideNo;
        this.VehiNO = vehiNo;
        this.Distance = distance;
    }

    public RideVehicle(String rideNo, String vehiNo, String rideDate) {
        this.RideNo = rideNo;
        this.VehiNO = vehiNo;
        this.RideDate = rideDate;
    }
}