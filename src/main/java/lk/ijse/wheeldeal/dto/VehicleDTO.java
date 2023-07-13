package lk.ijse.wheeldeal.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class VehicleDTO {
    private String VehiNo;
    private String VehiType;
    private String Model;
    private Double CostPerKM;
    private String Availability;
}