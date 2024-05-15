package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MYSQLJugadoresDAO
{
    // CRUD, implementarlo como objeto
    public static boolean create(Jugadores e, Connection con) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("INSERT INTO jugadors (jugador_id,equip_id,nom,cognom,data_naixement,alcada,pes,dorsal,posicio) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)" );
            sta.setInt(1,e.getJugador_id());
            sta.setInt(2,e.getEquip_id());
            sta.setString(3,e.getNom());
            sta.setString(4,e.getCognom());
            sta.setDate(5,e.getData_naixement());
            sta.setFloat(6,e.getAlcada());
            sta.setFloat(7,e.getPes());
            sta.setString(8,e.getDorsal());
            sta.setString(9,e.getPosicio());
            sta.executeQuery();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al crear");
        }
        return false;
    }

    public static boolean read(Jugadores j, Connection con) {
        Jugadores jd = read(j.getJugador_id(),con);
        if (jd == null) return false;
        j.setNom(jd.getNom());
        j.setCognom(jd.getCognom());
        j.setData_naixement(jd.getData_naixement());
        j.setAlcada(jd.getAlcada());
        j.setPes(jd.getPes());
        j.setDorsal(jd.getDorsal());
        j.setPosicio(jd.getPosicio());
        j.setEquip_id(jd.getEquip_id());
        return true;
    }

    private static Jugadores read(int id, Connection con) {
        PreparedStatement sta;
        ResultSet rs;
        try {
            sta = con.prepareStatement("SELECT * FROM jugadors WHERE jugador_id = ?");
            sta.setInt(1,id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                if (rs.getString("jugador_id").isEmpty()) return null;
                return new Jugadores(id, rs.getString("nom"),rs.getString("cognom"),rs.getDate("data_naixement")
                        ,rs.getFloat("alcada"),rs.getFloat("pes"),rs.getString("dorsal")
                        ,rs.getString("posicio"),rs.getInt("equip_id"));
            }
        }catch (SQLException s)
        {
            System.out.println("Error al hacer select");
            return null;
        }
        return null;
    }
    public boolean update(Jugadores e, Connection con) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("UPDATE jugadors SET equip_id =?,nom =?,cognom =?,data_naixement =?,alcada =?,pes =?" +
                    ",dorsal =?,posicio =? WHERE id =? ");
            sta.setInt(1,e.getEquip_id());
            sta.setString(2,e.getNom());
            sta.setString(3,e.getCognom());
            sta.setDate(4,e.getData_naixement());
            sta.setFloat(5,e.getAlcada());
            sta.setFloat(6,e.getPes());
            sta.setString(7,e.getDorsal());
            sta.setString(8,e.getPosicio());
            sta.setInt(9,e.getJugador_id());
            ResultSet rs = sta.executeQuery();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al fer update");
        }
        return false;
    }

    public boolean delete(Jugadores e, Connection con) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("DELETE FROM jugadors WHERE id =? ");
            sta.setInt(1,e.getJugador_id());
            ResultSet rs = sta.executeQuery();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al fer update");
        }
        return false;
    }

    // ALTRES DAO
    public boolean exists(Jugadores e, Connection con) {
        return true;
    }

    public int count() {
        return 0;
    }

    public List<Jugadores> all() {
        return new ArrayList<>();
    }
}
