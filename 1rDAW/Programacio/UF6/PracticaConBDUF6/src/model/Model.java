package model;

import vista.Vista;

import java.sql.*;
import java.util.ArrayList;

public class Model
{
    private static Connection con;
    public static void openCon(String url, String nom, String contra) {
        Connection conection = null;

        try {
            Vista.mostrarUnMisatgeGeneric("Conectando...");
            conection = DriverManager.getConnection(url, nom, contra);
            Vista.mostrarUnMisatgeGeneric("Connexió establerta");
        } catch (SQLException e) {
            Vista.mostrarUnMisatgeGeneric(e.getMessage());
        }

        con = conection;
    }

    public static void llistarJugadorsSegunEquipo(String equipo)
    {
        ArrayList<String> missatge = new ArrayList<>();
        try {
            PreparedStatement sta;
            if(!equipo.isEmpty())
            {
                sta = con.prepareStatement("SELECT p.jugador_id,CONCAT(p.nom, p.cognom) AS full_name"
                        +" FROM jugadors p"
                        +" INNER JOIN equips t ON t.equip_id = p.equip_id"
                        +" WHERE t.nom =?" );
                sta.setString(1,equipo);

            }else
            {
                sta = con.prepareStatement("SELECT p.jugador_id,CONCAT(p.nom, p.cognom) AS full_name"
                        +" FROM jugadors p"
                        +" INNER JOIN equips t ON t.equip_id = p.equip_id");
            }
            ResultSet rs = sta.executeQuery();

            while (rs.next())
            {
                String id = rs.getString("jugador_id");
                String nombre = rs.getString("full_name");
                missatge.add(id);
                missatge.add(nombre);
            }
            if(missatge.isEmpty())
            {
                missatge.add("Null");
                missatge.add("Null");
            }
            Vista.mostrarPreg1(missatge);
        }catch (SQLException e)
        {
            Vista.mostrarUnMisatgeGeneric(e.getMessage());
        }
    }

    public static void closeCon() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            Vista.mostrarUnMisatgeGeneric(e.getMessage());
        }
    }
}
