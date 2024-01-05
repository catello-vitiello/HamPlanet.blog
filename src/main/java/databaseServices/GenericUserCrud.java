package databaseServices;

import java.sql.SQLException;
import java.util.Collection;

public interface GenericUserCrud<T>{

    Collection<T> getAll() throws SQLException;
    T getByID(T user) throws SQLException;
    boolean insertNewUser(T user) throws SQLException;
    boolean deleteAccount(T user) throws SQLException;
    boolean updateUser(T user) throws SQLException;
}
