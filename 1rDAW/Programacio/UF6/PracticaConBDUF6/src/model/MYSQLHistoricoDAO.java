package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MYSQLHistoricoDAO implements DAOGenerica<Historico>{

    static Connection con;
    public MYSQLHistoricoDAO(Connection con) {
        this.con = con;
    }

    // CRUD, implementarlo como objeto
    public boolean create(Historico h)
    {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("INSERT INTO historic (nom, jugador_id, ultim_equip_id, punts_tot, tirs_anotats," +
                    " tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius," +
                    " rebots_defensius, assistencies, robades, bloqueigs, tot_min_jugats) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
            sta.setString(1,h.getNom());
            sta.setInt(2,h.getJugador_id());
            sta.setInt(3,h.getUltim_Equip_id());
            sta.setInt(4,h.getPunts_tot());
            sta.setInt(5,h.getTirs_anotats());
            sta.setInt(6,h.getTirs_tirats());
            sta.setInt(7,h.getTir_triples_anotats());
            sta.setInt(8,h.getTirs_triples_tirats());
            sta.setInt(9,h.getTirs_lliures_anotats());
            sta.setInt(10,h.getTir_lliures_tirats());
            sta.setInt(11,h.getRebots_ofensius());
            sta.setInt(12,h.getRebots_defensius());
            sta.setInt(13,h.getAssistencies());
            sta.setInt(14,h.getRobades());
            sta.setInt(15,h.getBloqueigs());
            sta.setFloat(16,h.getTot_min_jugats());
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
    public boolean read(Historico h) {
        Historico ej = readQuery(h.getJugador_id());
        if (ej == null) return false;
        h.setNom(ej.getNom());
        h.setUltim_Equip_id(ej.getUltim_Equip_id());
        h.setPunts_tot(ej.getPunts_tot());
        h.setTirs_anotats(ej.getTirs_anotats());
        h.setTirs_tirats(ej.getTirs_tirats());
        h.setTir_triples_anotats(ej.getTir_triples_anotats());
        h.setTirs_triples_tirats(ej.getTirs_triples_tirats());
        h.setTirs_lliures_anotats(ej.getTirs_lliures_anotats());
        h.setTir_lliures_tirats(ej.getTir_lliures_tirats());
        h.setRebots_ofensius(ej.getRebots_ofensius());
        h.setRebots_defensius(ej.getRebots_defensius());
        h.setAssistencies(ej.getAssistencies());
        h.setBloqueigs(ej.getBloqueigs());
        h.setRobades(ej.getRobades());
        h.setTot_min_jugats(ej.getTot_min_jugats());
        return true;
    }
    public boolean read(ArrayList<Historico> historicos) {
        historicos.addAll(readQuery());
        if (historicos.isEmpty()) return false;
        return true;
    }
    private static Historico readQuery(int jugador_id) {
        PreparedStatement sta;
        ResultSet rs;
        try {
            sta = con.prepareStatement("SELECT * FROM historic WHERE jugador_id = ?");
            sta.setInt(1,jugador_id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                if (rs.getString("jugador_id").isEmpty()) return null;
                return new Historico(rs.getString("nom"),jugador_id, rs.getInt("ultim_quip_id"),rs.getInt("tirs_anotats")
                        ,rs.getInt("tirs_tirats"),rs.getInt("punts_tot"),rs.getInt("tirs_triples_anotats")
                        ,rs.getInt("tirs_triples_tirats"),rs.getInt("tirs_lliures_anotats")
                        ,rs.getInt("tirs_lliures_tirats"),rs.getInt("rebots_ofensius"),rs.getInt("rebots_defensius")
                        ,rs.getInt("assistencies"),rs.getInt("robades"),rs.getInt("bloqueigs")
                        ,rs.getFloat("tot_min_jugats"));
            }
        }catch (SQLException s)
        {
            System.out.println("Error al hacer select");
            return null;
        }
        return null;
    }
    private static ArrayList<Historico> readQuery() {
        PreparedStatement sta;
        ResultSet rs;
        ArrayList<Historico> historicos = new ArrayList<>();
        Historico ej;
        try {
            sta = con.prepareStatement("SELECT * FROM historic");
            rs = sta.executeQuery();
            while (rs.next())
            {
                ej = new Historico(0);
                ej.setNom(rs.getString(1));
                ej.setJugador_id(rs.getInt(2));
                ej.setUltim_Equip_id(rs.getInt(3));
                ej.setPunts_tot(rs.getInt(4));
                ej.setTirs_anotats(rs.getInt(5));
                ej.setTirs_tirats(rs.getInt(6));
                ej.setTir_triples_anotats(rs.getInt(7));
                ej.setTirs_triples_tirats(rs.getInt(8));
                ej.setTirs_lliures_anotats(rs.getInt(9));
                ej.setTir_lliures_tirats(rs.getInt(10));
                ej.setRebots_ofensius(rs.getInt(11));
                ej.setRebots_defensius(rs.getInt(12));
                ej.setAssistencies(rs.getInt(13));
                ej.setRobades(rs.getInt(14));
                ej.setBloqueigs(rs.getInt(15));
                ej.setTot_min_jugats(rs.getFloat(16));
                historicos.add(ej);
            }
        }catch (SQLException s)
        {
            System.out.println("Error al hacer select");
            return null;
        }
        return historicos;
    }
    public boolean update(Historico h) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("UPDATE historic SET ultim_equip_id =?,punts_tot =?,tirs_anotats =?," +
                    "tirs_tirats =?,tirs_triples_anotats =?,tirs_triples_tirats =?,tirs_lliures_anotats =?,tirs_lliures_tirats =?" +
                    ",rebots_ofensius =?,rebots_defensius =?,assistencies =?,robades =?,bloqueigs =?,tot_min_jugats =?,nom=?" +
                    " WHERE jugador_id =? ");
            sta.setInt(1,h.getUltim_Equip_id());
            sta.setInt(2,h.getPunts_tot());
            sta.setInt(3,h.getTirs_anotats());
            sta.setInt(4,h.getTirs_tirats());
            sta.setInt(5,h.getTir_triples_anotats());
            sta.setInt(6,h.getTirs_triples_tirats());
            sta.setInt(7,h.getTirs_lliures_anotats());
            sta.setInt(8,h.getTir_lliures_tirats());
            sta.setInt(9,h.getRebots_ofensius());
            sta.setInt(10,h.getRebots_defensius());
            sta.setInt(11,h.getAssistencies());
            sta.setInt(12,h.getRobades());
            sta.setInt(13,h.getBloqueigs());
            sta.setFloat(14,h.getTot_min_jugats());
            sta.setString(15,h.getNom());
            sta.setInt(16, h.getJugador_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al fer update");
        }
        return false;
    }
    public boolean delete(Historico h) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("DELETE FROM historic WHERE jugador_id =? ");
            sta.setInt(1,h.getJugador_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al borrar: " + s.getMessage());
        }
        return false;
    }
    @Override
    public boolean exists(Historico h) {
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
