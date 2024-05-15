package model;

import java.sql.Connection;
import java.util.List;

public class MYSQLHistoricoDAO implements DAOGenerica{
    @Override
    public boolean create(Object o, Connection con) {
        return false;
    }

    @Override
    public boolean read(Object o, Connection con) {
        return false;
    }

    @Override
    public boolean update(Object o, Connection con) {
        return false;
    }

    @Override
    public boolean delete(Object o, Connection con) {
        return false;
    }

    @Override
    public boolean exists(Object o, Connection con) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List all() {
        return null;
    }
}
