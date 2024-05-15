package model;

import java.sql.Connection;
import java.util.List;

public interface DAOGenerica<T>
{
    // CRUD
    boolean create(T t, Connection con);

    boolean read(T t, Connection con);

    boolean update(T t, Connection con);
    boolean delete(T t, Connection con);
    // ALTRES
    boolean exists(T t, Connection con);
    int count();
    List<T> all();
}
