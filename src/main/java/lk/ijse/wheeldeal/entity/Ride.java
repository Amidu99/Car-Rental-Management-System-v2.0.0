package lk.ijse.wheeldeal.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class Ride {
    private String RideNo;
    private String CustID;
    private String RideDate;
    private String Status;
}