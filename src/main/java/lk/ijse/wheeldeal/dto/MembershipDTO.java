package lk.ijse.wheeldeal.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class MembershipDTO {
    private String Code;
    private String Type;
    private Double Discount;
    private Double Fee;
}