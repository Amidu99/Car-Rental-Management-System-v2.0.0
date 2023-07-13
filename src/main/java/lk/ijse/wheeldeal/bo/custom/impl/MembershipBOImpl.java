package lk.ijse.wheeldeal.bo.custom.impl;

import lk.ijse.wheeldeal.bo.custom.MembershipBO;
import lk.ijse.wheeldeal.dao.DAOFactory;
import lk.ijse.wheeldeal.dao.custom.MembershipDAO;
import lk.ijse.wheeldeal.dto.MembershipDTO;
import lk.ijse.wheeldeal.entity.Membership;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipBOImpl implements MembershipBO {
    //with Factory Design Pattern
    MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP);

    @Override
    public List<MembershipDTO> getAllMembership() throws SQLException, ClassNotFoundException {
        List<Membership> memberships = membershipDAO.getAll();
        List<MembershipDTO> allMemberships = new ArrayList<>();
        for (Membership m : memberships){
            allMemberships.add(new MembershipDTO(m.getCode(), m.getType(), m.getDiscount(), m.getFee()));
        }
        return allMemberships;
    }

    @Override
    public MembershipDTO searchMembership(String membCode) throws SQLException, ClassNotFoundException {
        Membership m = membershipDAO.search(membCode);
        return new MembershipDTO(m.getCode(), m.getType(), m.getDiscount(), m.getFee());
    }

    @Override
    public boolean addMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException {
        return membershipDAO.add(new Membership(dto.getCode(), dto.getType(), dto.getDiscount(), dto.getFee()));
    }

    @Override
    public boolean updateMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException {
        return membershipDAO.update(new Membership(dto.getCode(), dto.getType(), dto.getDiscount(), dto.getFee()));
    }

    @Override
    public boolean deleteMembership(String membCode) throws SQLException, ClassNotFoundException {
        return membershipDAO.delete(membCode);
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return membershipDAO.generateNextID();
    }

    @Override
    public List<String> getCodes() throws SQLException {
        return membershipDAO.getCodes();
    }

    @Override
    public double getDiscount(String membCode) throws SQLException {
        return membershipDAO.getDiscount(membCode);
    }
}