package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MYSQLEstadisticas_jugadoresDAO implements DAOGenerica<Estadisticas_jugadores>{
    static Connection con;
    public MYSQLEstadisticas_jugadoresDAO(Connection con) {
        this.con = con;
    }
    // CRUD, implementarlo como objeto
    public boolean create(Estadisticas_jugadores e)
    {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("INSERT INTO estadistiques_jugadors (jugador_id, partit_id, punts, tirs_anotats," +
                    " tirs_tirats, tir_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tir_lliures_tirats, rebots_ofensius," +
                    " rebots_defensius, assistencies, robades, bloqueigs, minuts_jugats, equip_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
            sta.setInt(1,e.getJugador_id());
            sta.setInt(2,e.getPartit_id());
            sta.setInt(3,e.getPunts());
            sta.setInt(4,e.getTirs_anotats());
            sta.setInt(5,e.getTirs_tirats());
            sta.setInt(6,e.getTir_triples_anotats());
            sta.setInt(7,e.getTirs_triples_tirats());
            sta.setInt(8,e.getTirs_lliures_anotats());
            sta.setInt(9,e.getTir_lliures_tirats());
            sta.setInt(10,e.getRebots_ofensius());
            sta.setInt(11,e.getRebots_defensius());
            sta.setInt(12,e.getAssistencies());
            sta.setInt(13,e.getBloqueigs());
            sta.setInt(14,e.getRobades());
            sta.setFloat(15,e.getMinuts_jugats());
            sta.setInt(16,e.getEquip_id());
            sta.executeUpdate();
            return true;
        }catch (SQLIntegrityConstraintViolationException s)
        {
            System.out.println("Ya existe la id");
        }catch (SQLException s)
        {
            System.out.println("Error al crear: " + s.getMessage());
        }
        return false;
    }
    public boolean read(Estadisticas_jugadores e) {
        Estadisticas_jugadores ej = readQuery(e.getJugador_id());
        if (ej == null) return false;
        e.setPartit_id(ej.getPartit_id());
        e.setPunts(ej.getPunts());
        e.setTirs_anotats(ej.getTirs_anotats());
        e.setTirs_tirats(ej.getTirs_tirats());
        e.setTir_triples_anotats(ej.getTir_triples_anotats());
        e.setTirs_triples_tirats(ej.getTirs_triples_tirats());
        e.setTirs_lliures_anotats(ej.getTirs_lliures_anotats());
        e.setTir_lliures_tirats(ej.getTir_lliures_tirats());
        e.setRebots_ofensius(ej.getRebots_ofensius());
        e.setRebots_defensius(ej.getRebots_defensius());
        e.setAssistencies(ej.getAssistencies());
        e.setBloqueigs(ej.getBloqueigs());
        e.setRobades(ej.getRobades());
        e.setMinuts_jugats(ej.getMinuts_jugats());
        e.setEquip_id(ej.getEquip_id());
        return true;
    }
    public boolean read(ArrayList<Estadisticas_jugadores> estadisticasJugadores) {
        estadisticasJugadores.addAll(readQuery());
        if (estadisticasJugadores.isEmpty()) return false;
        return true;
    }
    private static Estadisticas_jugadores readQuery(int id) {
        PreparedStatement sta;
        ResultSet rs;
        try {
            sta = con.prepareStatement("SELECT * FROM estadistiques_jugadors WHERE jugador_id = ?");
            sta.setInt(1,id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                if (rs.getString("jugador_id").isEmpty()) return null;
                return new Estadisticas_jugadores(id, rs.getInt("partit_id"),rs.getInt("tirs_anotats")
                        ,rs.getInt("tirs_tirats"),rs.getInt("punts"),rs.getInt("tir_triples_anotats")
                        ,rs.getInt("tirs_triples_tirats"),rs.getInt("tirs_lliures_anotats")
                        ,rs.getInt("tir_lliures_tirats"),rs.getInt("rebots_ofensius"),rs.getInt("rebots_defensius")
                        ,rs.getInt("assistencies"),rs.getInt("robades"),rs.getInt("bloqueigs")
                        ,rs.getFloat("minuts_jugats"),rs.getInt("equip_id"));
            }
        }catch (SQLException s)
        {
            System.out.println("Error al hacer select");
            return null;
        }
        return null;
    }
    private static ArrayList<Estadisticas_jugadores> readQuery() {
        PreparedStatement sta;
        ResultSet rs;
        ArrayList<Estadisticas_jugadores> estadisticasJugadores = new ArrayList<>();
        Estadisticas_jugadores ej;
        try {
            sta = con.prepareStatement("SELECT * FROM estaistiques_jugadors");
            rs = sta.executeQuery();
            while (rs.next())
            {
                ej = new Estadisticas_jugadores(0);
                ej.setJugador_id(rs.getInt(1));
                ej.setPartit_id(rs.getInt(2));
                ej.setPunts(rs.getInt(3));
                ej.setTirs_anotats(rs.getInt(4));
                ej.setTirs_tirats(rs.getInt(5));
                ej.setTir_triples_anotats(rs.getInt(6));
                ej.setTirs_triples_tirats(rs.getInt(7));
                ej.setTirs_lliures_anotats(rs.getInt(8));
                ej.setTir_lliures_tirats(rs.getInt(9));
                ej.setRebots_ofensius(rs.getInt(10));
                ej.setRebots_defensius(rs.getInt(11));
                ej.setAssistencies(rs.getInt(12));
                ej.setRobades(rs.getInt(13));
                ej.setBloqueigs(rs.getInt(14));
                ej.setMinuts_jugats(rs.getFloat(15));
                ej.setEquip_id(rs.getInt(16));
                estadisticasJugadores.add(ej);
            }
        }catch (SQLException s)
        {
            System.out.println("Error al hacer select");
            return null;
        }
        return estadisticasJugadores;
    }
    public boolean update(Estadisticas_jugadores e) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("UPDATE estadistiques_jugadors SET partit_id =?,punts =?,tirs_anotats =?," +
                    "tirs_tirats =?,tir_triples_anotats =?,tirs_triples_tirats =?,tirs_lliures_anotats =?,tir_lliures_tirats =?" +
                    ",rebots_ofensius =?,rebots_defensius =?,assistencies =?,robades =?,bloqueigs =?,minuts_jugats =?,equip_id =?" +
                    " WHERE jugador_id =? ");
            sta.setInt(1,e.getPartit_id());
            sta.setInt(2,e.getPunts());
            sta.setInt(3,e.getTirs_anotats());
            sta.setInt(4,e.getTirs_tirats());
            sta.setInt(5,e.getTir_triples_anotats());
            sta.setInt(6,e.getTirs_triples_tirats());
            sta.setInt(7,e.getTirs_lliures_anotats());
            sta.setInt(8,e.getTir_lliures_tirats());
            sta.setInt(9,e.getRebots_ofensius());
            sta.setInt(10,e.getRebots_defensius());
            sta.setInt(11,e.getAssistencies());
            sta.setInt(12,e.getRobades());
            sta.setInt(13,e.getBloqueigs());
            sta.setFloat(14,e.getMinuts_jugats());
            sta.setInt(15,e.getEquip_id());
            sta.setInt(16, e.getJugador_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al fer update");
        }
        return false;
    }
    public boolean delete(Estadisticas_jugadores e) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("DELETE FROM estadistiques_jugadors WHERE jugador_id =? ");
            sta.setInt(1,e.getJugador_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al borrar: " + s.getMessage());
        }
        return false;
    }

    // ALTRES DAO
    public boolean exists(Estadisticas_jugadores e) {
        return true;
    }

    public int count() {
        return 0;
    }

    public List<Estadisticas_jugadores> all() {
        return new ArrayList<>();
    }
}
