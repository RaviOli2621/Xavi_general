package model;

import vista.Vista;

import java.sql.*;
import java.util.ArrayList;

public class Model
{



    public static void llistarJugadorsSegunEquipo(String equipo, Connection con)
    {
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        MYSQLJugadoresDAO dao = new MYSQLJugadoresDAO(con);

        dao.read(jugadores, con);

        jugadores.forEach((Jugadores j) ->
        {
            if(equipo.equals(j.equip_id + ""))
            {
                System.out.println(j.jugador_id + " " + j.nom);
            }
        });
    }



}
