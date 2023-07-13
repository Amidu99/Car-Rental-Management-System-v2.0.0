package lk.ijse.wheeldeal.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll() throws SQLException, ClassNotFoundException;
    T search(String id) throws SQLException, ClassNotFoundException;
    boolean add(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNextID() throws SQLException, ClassNotFoundException;
}