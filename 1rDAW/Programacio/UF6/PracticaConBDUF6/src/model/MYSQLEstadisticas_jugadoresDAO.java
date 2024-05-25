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
                    " tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius," +
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
            sta.setInt(13,e.getRobades());
            sta.setInt(14,e.getBloqueigs());
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
    public boolean read(int jug_id,ArrayList<Estadisticas_jugadores> estadisticasJugadores) {
        readQuery(estadisticasJugadores, jug_id);
        if (estadisticasJugadores.isEmpty()) return false;
        return true;
    }
    private static Estadisticas_jugadores readQuery(int jugador_id) {
        PreparedStatement sta;
        ResultSet rs;
        try {
            sta = con.prepareStatement("SELECT * FROM estadistiques_jugadors WHERE jugador_id = ?");
            sta.setInt(1,jugador_id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                if (rs.getString("jugador_id").isEmpty()) return null;
                return new Estadisticas_jugadores(jugador_id, rs.getInt("partit_id"),rs.getInt("tirs_anotats")
                        ,rs.getInt("tirs_tirats"),rs.getInt("punts"),rs.getInt("tirs_triples_anotats")
                        ,rs.getInt("tirs_triples_tirats"),rs.getInt("tirs_lliures_anotats")
                        ,rs.getInt("tirs_lliures_tirats"),rs.getInt("rebots_ofensius"),rs.getInt("rebots_defensius")
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
    private static ArrayList<Estadisticas_jugadores> readQuery(ArrayList<Estadisticas_jugadores> estadisticasJugadores, int jugd_id) {
        PreparedStatement sta;
        ResultSet rs;
        Estadisticas_jugadores ej;
        try {
            sta = con.prepareStatement("SELECT * FROM estadistiques_jugadors WHERE jugador_id = ?");
            sta.setInt(1,jugd_id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                ej = new Estadisticas_jugadores(0);
                ej.setJugador_id(rs.getInt(1));
                ej.setPartit_id(rs.getInt(2));
                ej.setMinuts_jugats(rs.getFloat(3));
                ej.setPunts(rs.getFloat(4));
                ej.setTirs_anotats(rs.getFloat(5));
                ej.setTirs_tirats(rs.getFloat(6));
                ej.setTir_triples_anotats(rs.getFloat(7));
                ej.setTirs_triples_tirats(rs.getFloat(8));
                ej.setTirs_lliures_anotats(rs.getFloat(9));
                ej.setTir_lliures_tirats(rs.getFloat(10));
                ej.setRebots_ofensius(rs.getFloat(11));
                ej.setRebots_defensius(rs.getFloat(12));
                ej.setAssistencies(rs.getFloat(13));
                ej.setRobades(rs.getFloat(14));
                ej.setBloqueigs(rs.getFloat(15));
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
            sta = con.prepareStatement("UPDATE estadistiques_jugadors SET punts =?,tirs_anotats =?," +
                    "tirs_tirats =?,tirs_triples_anotats =?,tirs_triples_tirats =?,tirs_lliures_anotats =?,tirs_lliures_tirats =?" +
                    ",rebots_ofensius =?,rebots_defensius =?,assistencies =?,robades =?,bloqueigs =?,minuts_jugats =?,equip_id =?" +
                    " WHERE jugador_id =? AND partit_id =?");
            sta.setInt(1,e.getPunts());
            sta.setInt(2,e.getTirs_anotats());
            sta.setInt(3,e.getTirs_tirats());
            sta.setInt(4,e.getTir_triples_anotats());
            sta.setInt(5,e.getTirs_triples_tirats());
            sta.setInt(6,e.getTirs_lliures_anotats());
            sta.setInt(7,e.getTir_lliures_tirats());
            sta.setInt(8,e.getRebots_ofensius());
            sta.setInt(9,e.getRebots_defensius());
            sta.setInt(10,e.getAssistencies());
            sta.setInt(11,e.getRobades());
            sta.setInt(12,e.getBloqueigs());
            sta.setFloat(13,e.getMinuts_jugats());
            sta.setInt(14,e.getEquip_id());
            sta.setInt(15, e.getJugador_id());
            sta.setInt(16,e.getPartit_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            Vista.mostrarUnMisatgeGeneric("Error al fer update " +s.getMessage() );
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
