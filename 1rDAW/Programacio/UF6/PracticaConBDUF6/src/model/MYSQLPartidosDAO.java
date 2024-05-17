package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MYSQLPartidosDAO implements DAOGenerica<Partidos>{
    Connection con;

    public MYSQLPartidosDAO(Connection con) {
        this.con = con;
    }

    // CRUD, implementarlo como objeto

    @Override
    public boolean create(Partidos p, Connection con) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("INSERT INTO partits (partit_id, equip_id, resultat, data_partit, matx) " +
                    "VALUES (?,?,?,?,?)");
            sta.setInt(1,p.getPartit_id());
            sta.setInt(2,p.getEquip_id());
            sta.setString(4,p.getResultat());
            sta.setDate(3,p.getData_partit());
            sta.setString(8,p.getMatx());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al crear: " + s.getMessage());
        }
        return false;
    }

    @Override
    public boolean read(Partidos p, Connection con) {
        Partidos par = read(p.getPartit_id(),con);
        if (par == null) return false;
        p.setEquip_id(par.getEquip_id());
        p.setResultat(par.getResultat());
        p.setData_partit(par.getData_partit());
        p.setMatx(par.getMatx());
        return true;
    }
    private static Partidos read(int partit_id, Connection con) {
        PreparedStatement sta;
        ResultSet rs;
        try {
            sta = con.prepareStatement("SELECT * FROM partits WHERE partit_id = ?");
            sta.setInt(1,partit_id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                if (rs.getString("partit_id").isEmpty()) return null;
                return new Partidos(partit_id, rs.getInt("equip_id"),rs.getString("resultat"),rs.getDate("data_partit")
                        ,rs.getString("matx"));
            }
        }catch (SQLException s)
        {
            System.out.println("Error al hacer select");
            return null;
        }
        return null;
    }

    @Override
    public boolean update(Partidos p, Connection con) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("UPDATE jugadors SET partit_id =?,equip_id =?,resultat =?,data_partit =?,matx =?");
            sta.setInt(1,p.getPartit_id());
            sta.setInt(2,p.getEquip_id());
            sta.setString(4,p.getResultat());
            sta.setDate(3,p.getData_partit());
            sta.setString(8,p.getMatx());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al fer update");
        }
        return false;
    }

    @Override
    public boolean delete(Partidos p, Connection con) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("DELETE FROM partits WHERE partit_id =? ");
            sta.setInt(1,p.getPartit_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al borrar: " + s.getMessage());
        }
        return false;
    }

    @Override
    public boolean exists(Partidos partidos, Connection con) {
        return true;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<Partidos> all() {
        return new ArrayList<>();
    }


}
