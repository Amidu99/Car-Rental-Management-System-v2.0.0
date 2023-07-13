package lk.ijse.wheeldeal.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class Membership {
    private String Code;
    private String Type;
    private Double Discount;
    private Double Fee;
}