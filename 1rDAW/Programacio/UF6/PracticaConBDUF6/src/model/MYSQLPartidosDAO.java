package model;

import vista.Vista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MYSQLPartidosDAO implements DAOGenerica<Partidos>{
    static Connection con;

    public MYSQLPartidosDAO(Connection con) {
        this.con = con;
    }

    // CRUD, implementarlo como objeto

    @Override
    public boolean create(Partidos p) {
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
            Vista.mostrarUnMisatgeGeneric("Error al crear: " + s.getMessage());
        }
        return false;
    }

    @Override
    public boolean read(Partidos p) {
        Partidos par = readQuery(p.getPartit_id());
        if (par == null) return false;
        p.setEquip_id(par.getEquip_id());
        p.setResultat(par.getResultat());
        p.setData_partit(par.getData_partit());
        p.setMatx(par.getMatx());
        return true;
    }
    public boolean read(ArrayList<Partidos> partits) {
        partits.addAll(readQuery());
        if (partits.isEmpty()) return false;
        return true;
    }
    private static Partidos readQuery(int partit_id) {
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
            Vista.mostrarUnMisatgeGeneric("Error al hacer select");
            return null;
        }
        return null;
    }
    private static ArrayList<Partidos> readQuery() {
        PreparedStatement sta;
        ResultSet rs;
        ArrayList<Partidos> partits = new ArrayList<>();
        Partidos p;
        try {
            sta = con.prepareStatement("SELECT * FROM partits");
            rs = sta.executeQuery();
            while (rs.next())
            {
                p = new Partidos(0);
                p.setPartit_id(rs.getInt(1));
                p.setEquip_id(rs.getInt(2));
                p.setData_partit(rs.getDate(3));
                p.setMatx(rs.getString(4));
                p.setResultat(rs.getString(5));

                partits.add(p);
            }
        }catch (SQLException s)
        {
            Vista.mostrarUnMisatgeGeneric("Error al hacer select");
            return null;
        }
        return partits;
    }

    @Override
    public boolean update(Partidos p) {
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
            Vista.mostrarUnMisatgeGeneric("Error al fer update");
        }
        return false;
    }

    @Override
    public boolean delete(Partidos p) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("DELETE FROM partits WHERE partit_id =? ");
            sta.setInt(1,p.getPartit_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            Vista.mostrarUnMisatgeGeneric("Error al borrar: " + s.getMessage());
        }
        return false;
    }

    // ALTRES DAO
    @Override
    public boolean exists(Partidos partido) {
        ArrayList<Partidos> partidos = readQuery();
        AtomicBoolean existe = new AtomicBoolean(false);
        partidos.forEach((j) ->
        {
            if(j.getPartit_id() == partido.getPartit_id()) existe.set(true);
        });
        return existe.get();
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
