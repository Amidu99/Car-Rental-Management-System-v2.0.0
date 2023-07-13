package lk.ijse.wheeldeal.dao.custom.impl;

import lk.ijse.wheeldeal.dao.custom.MembershipDAO;
import lk.ijse.wheeldeal.entity.Membership;
import lk.ijse.wheeldeal.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public List<Membership> getAll() throws SQLException {
        List<Membership> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM membership");
        while (resultSet.next()) {
            data.add(new Membership(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4)
            ));
        }
        return data;
    }

    @Override
    public Membership search(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM membership WHERE Code = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, code);
        if(resultSet.next()){
            return new Membership(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public boolean add(Membership m) throws SQLException {
        String sql = "INSERT INTO membership ( Code, Type, Discount, Fee ) VALUES ( ?, ?, ?, ? )";
        return CrudUtil.execute(sql, m.getCode(), m.getType(), m.getDiscount(), m.getFee());
    }

    @Override
    public boolean update(Membership m) throws SQLException {
        String sql = "UPDATE membership SET Type = ?, Discount = ?, Fee = ? WHERE Code = ?";
        return CrudUtil.execute(sql, m.getType(), m.getDiscount(), m.getFee(), m.getCode());
    }

    @Override
    public boolean delete(String code) throws SQLException {
        String sql = "DELETE FROM membership WHERE Code = ?";
        return CrudUtil.execute(sql, code);
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getCodes() throws SQLException {
        List<String> codes = new ArrayList<>();
        String sql = "SELECT Code FROM membership";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public double getDiscount(String membCode) throws SQLException {
        String sql = "SELECT * FROM membership WHERE Code = ?";
        ResultSet resultSet = CrudUtil.execute(sql, membCode);
        if(resultSet.next()){
            return resultSet.getDouble(3);
        }
        return -1;
    }
}