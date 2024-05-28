package model;

import vista.Vista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MYSQLEquiposDAO implements DAOGenerica<Equipos>{
    static Connection con;

    public MYSQLEquiposDAO(Connection con) {
        this.con = con;
    }

    // CRUD, implementarlo como objeto
    public boolean create(Equipos e)
    {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("INSERT INTO equips (equip_id, guanyades, perdudes, nom, ciutat, acronim, divisio) " +
                    "VALUES (?,?,?,?,?,?,?)" );
            sta.setInt(1,e.getEquip_id());
            sta.setInt(2,e.getGuanyades());
            sta.setInt(3,e.getPerdudes());
            sta.setString(4,e.getNom());
            sta.setString(5,e.getCiutat());
            sta.setString(6,e.getAcronim());
            sta.setString(7,e.getDivisio());
            sta.executeUpdate();
            return true;
        }catch (SQLIntegrityConstraintViolationException s)
        {
            Vista.mostrarUnMisatgeGeneric("Ya existe la id");
        }catch (SQLException s)
        {
            Vista.mostrarUnMisatgeGeneric("Error al crear: " + s.getMessage());
        }
        return false;
    }
    public boolean read(Equipos e) {
        Equipos eq = readQuery(e.getEquip_id());
        if (eq == null) return false;
        e.setGuanyades(eq.getGuanyades());
        e.setPerdudes(eq.getPerdudes());
        e.setNom(eq.getNom());
        e.setCiutat(eq.getCiutat());
        e.setAcronim(eq.getAcronim());
        e.setDivisio(eq.getDivisio());
        return true;
    }
    public boolean read(ArrayList<Equipos> equipos) {
        equipos.addAll(readQuery());
        if (equipos.isEmpty()) return false;
        return true;
    }
    private static Equipos readQuery(int equip_id) {
        PreparedStatement sta;
        ResultSet rs;
        try {
            sta = con.prepareStatement("SELECT * FROM equips WHERE equip_id = ?");
            sta.setInt(1,equip_id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                if (rs.getString("equip_id").isEmpty()) return null;
                return new Equipos(equip_id, rs.getInt("guanyades"),rs.getInt("perdudes"),rs.getString("nom")
                        ,rs.getString("ciutat"),rs.getString("acronim"),rs.getString("divisio"));
            }
        }catch (SQLException s)
        {
            Vista.mostrarUnMisatgeGeneric("Error al hacer select");
            return null;
        }
        return null;
    }
    private static ArrayList<Equipos> readQuery() {
        PreparedStatement sta;
        ResultSet rs;
        ArrayList<Equipos> equipos = new ArrayList<>();
        Equipos e;
        try {
            sta = con.prepareStatement("SELECT * FROM equips");
            rs = sta.executeQuery();
            while (rs.next())
            {
                e = new Equipos(0);
                e.setEquip_id(rs.getInt(1));
                e.setCiutat(rs.getString(2));
                e.setNom(rs.getString(3));
                e.setAcronim(rs.getString(4));
                e.setDivisio(rs.getString(5));
                e.setGuanyades(rs.getInt(6));
                e.setPerdudes(rs.getInt(7));
                equipos.add(e);
            }
        }catch (SQLException s)
        {
            Vista.mostrarUnMisatgeGeneric("Error al hacer select");
            return null;
        }
        return equipos;
    }
    public boolean update(Equipos e) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("UPDATE equips SET guanyades =?,perdudes =?,nom =?,ciutat =?,acronim =?,divisio =?" +
                    "                            WHERE equip_id =? ");
            sta.setInt(1,e.getGuanyades());
            sta.setInt(2,e.getPerdudes());
            sta.setString(3,e.getNom());
            sta.setString(4,e.getCiutat());
            sta.setString(5,e.getAcronim());
            sta.setString(6,e.getDivisio());
            sta.setInt(7,e.getEquip_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            Vista.mostrarUnMisatgeGeneric("Error al fer update");
        }
        return false;
    }
    public boolean delete(Equipos e) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("DELETE FROM equips WHERE equip_id =? ");
            sta.setInt(1,e.getEquip_id());
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
    public boolean exists(Equipos e) {
        ArrayList<Equipos> equipos = readQuery();
        AtomicBoolean existe = new AtomicBoolean(false);
        equipos.forEach((j) ->
        {
            if(j.getEquip_id() == e.getEquip_id()) existe.set(true);
        });
        return existe.get();
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
