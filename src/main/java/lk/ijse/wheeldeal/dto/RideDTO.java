package lk.ijse.wheeldeal.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class RideDTO {
    private String RideNo;
    private String CustID;
    private String RideDate;
    private String Status;
}