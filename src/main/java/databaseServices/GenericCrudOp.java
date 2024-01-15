package databaseServices;

import java.sql.SQLException;
import java.util.Collection;

public interface GenericCrudOp<T>{

    Collection<T> getAll() throws SQLException;
    T getByID(T entity) throws SQLException;
    boolean insert(T user) throws SQLException;
    boolean delete(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;
}
