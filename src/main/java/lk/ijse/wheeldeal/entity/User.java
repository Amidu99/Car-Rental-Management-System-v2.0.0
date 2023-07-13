package lk.ijse.wheeldeal.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class User {
    private String UserID;
    private String UserName;
    private String Password;
    private String PassHint;
    private String EmpID;
}