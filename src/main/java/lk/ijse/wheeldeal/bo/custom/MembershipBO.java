package lk.ijse.wheeldeal.bo.custom;

import lk.ijse.wheeldeal.bo.SuperBO;
import lk.ijse.wheeldeal.dto.MembershipDTO;
import java.sql.SQLException;
import java.util.List;

public interface MembershipBO extends SuperBO {
    List<MembershipDTO> getAllMembership() throws SQLException, ClassNotFoundException;
    MembershipDTO searchMembership(String membCode) throws SQLException, ClassNotFoundException;
    boolean addMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteMembership(String membCode) throws SQLException, ClassNotFoundException;
    String generateNextID() throws SQLException, ClassNotFoundException;
    List<String> getCodes() throws SQLException;
    double getDiscount(String membCode) throws SQLException;
}