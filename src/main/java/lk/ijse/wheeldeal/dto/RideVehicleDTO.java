package lk.ijse.wheeldeal.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class RideVehicleDTO {
    private String RideNo;
    private String VehiNO;
    private String RideDate;
    private double Distance;

    public RideVehicleDTO(String rideNo, String vehiNo, double distance) {
        this.RideNo = rideNo;
        this.VehiNO = vehiNo;
        this.Distance = distance;
    }

    public RideVehicleDTO(String rideNo, String vehiNo, String rideDate) {
        this.RideNo = rideNo;
        this.VehiNO = vehiNo;
        this.RideDate = rideDate;
    }
}