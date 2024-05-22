package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MYSQLJugadoresDAO implements DAOGenerica<Jugadores>
{
    static Connection con;

    public MYSQLJugadoresDAO(Connection con) {
        this.con = con;
    }

    // CRUD, implementarlo como objeto
    public boolean create(Jugadores j)
    {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("INSERT INTO jugadors (jugador_id,equip_id,nom,cognom,data_naixement,alcada,pes,dorsal,posicio) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)" );
            sta.setInt(1,j.getJugador_id());
            sta.setInt(2,j.getEquip_id());
            sta.setString(3,j.getNom());
            sta.setString(4,j.getCognom());
            sta.setDate(5,j.getData_naixement());
            sta.setFloat(6,j.getAlcada());
            sta.setFloat(7,j.getPes());
            sta.setString(8,j.getDorsal());
            sta.setString(9,j.getPosicio());
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

    public boolean read(Jugadores j) {
        Jugadores jd = readQuery(j.getJugador_id());
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
    public boolean read(ArrayList<Jugadores> jugadores) {
        jugadores.addAll(readQuery());
        if (jugadores.isEmpty()) return false;
        return true;
    }

    private static Jugadores readQuery(int jugador_id) {
        PreparedStatement sta;
        ResultSet rs;
        try {
            sta = con.prepareStatement("SELECT * FROM jugadors WHERE jugador_id = ?");
            sta.setInt(1,jugador_id);
            rs = sta.executeQuery();
            while (rs.next())
            {
                if (rs.getString("jugador_id").isEmpty()) return null;
                return new Jugadores(jugador_id, rs.getString("nom"),rs.getString("cognom"),rs.getDate("data_naixement")
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
    private static ArrayList<Jugadores> readQuery() {
        PreparedStatement sta;
        ResultSet rs;
        ArrayList<Jugadores> jugadors = new ArrayList<>();
        Jugadores j;
        try {
            sta = con.prepareStatement("SELECT * FROM jugadors");
            rs = sta.executeQuery();
            while (rs.next())
            {
                j = new Jugadores(0);
                j.setJugador_id(rs.getInt(1));
                j.setNom(rs.getString(2));
                j.setCognom(rs.getString(3));
                j.setData_naixement(rs.getDate(4));
                j.setAlcada(rs.getFloat(5));
                j.setPes(rs.getFloat(6));
                j.setDorsal(rs.getString(7));
                j.setPosicio(rs.getString(8));
                j.setEquip_id(rs.getInt(9));
                jugadors.add(j);
            }
        }catch (SQLException s)
        {
            System.out.println("Error al hacer select");
            return null;
        }
        return jugadors;
    }
    public boolean update(Jugadores e) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("UPDATE jugadors SET equip_id =?,nom =?,cognom =?,data_naixement =?,alcada =?,pes =?" +
                    ",dorsal =?,posicio =? WHERE jugador_id =? ");
            sta.setInt(1,e.getEquip_id());
            sta.setString(2,e.getNom());
            sta.setString(3,e.getCognom());
            sta.setDate(4,e.getData_naixement());
            sta.setFloat(5,e.getAlcada());
            sta.setFloat(6,e.getPes());
            sta.setString(7,e.getDorsal());
            sta.setString(8,e.getPosicio());
            sta.setInt(9,e.getJugador_id());
            sta.executeUpdate();
            return true;
        }catch (SQLException s)
        {
            System.out.println("Error al fer update");
        }
        return false;
    }

    public boolean delete(Jugadores e) {
        PreparedStatement sta;
        try {
            sta = con.prepareStatement("DELETE FROM jugadors WHERE jugador_id =? ");
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
    public boolean exists(Jugadores e) {
        return true;
    }

    public int count() {
        return 0;
    }

    public List<Jugadores> all() {
        return new ArrayList<>();
    }
}
