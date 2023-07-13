package lk.ijse.wheeldeal.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class Vehicle {
    private String VehiNo;
    private String VehiType;
    private String Model;
    private Double CostPerKM;
    private String Availability;
}