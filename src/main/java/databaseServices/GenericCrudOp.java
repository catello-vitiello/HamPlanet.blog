package databaseServices;

import java.sql.SQLException;
import java.util.Collection;

public interface GenericCrudOp<T, K, P>{

    Collection<T> getAll(P filter) throws SQLException;
    T getByID(K id) throws SQLException;
    boolean insert(T entity) throws SQLException;
    boolean delete(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;

}
