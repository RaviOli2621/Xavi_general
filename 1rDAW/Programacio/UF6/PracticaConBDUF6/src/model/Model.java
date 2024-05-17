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

        dao.read(jugadores);

        jugadores.forEach((Jugadores j) ->
        {
            if(equipo.equals(j.equip_id + ""))
            {
                System.out.println(j.jugador_id + " " + j.nom);
            }
        });
    }

    public static void crearJugadorEnEquipo(String nombre, String equipo, Connection con)
    {
        MYSQLJugadoresDAO daoJug = new MYSQLJugadoresDAO(con);
        String apellido = "";
        if(nombre.contains(" "))
        {
            for (int i = 1; i < nombre.split(" ").length; i++) apellido += nombre.split(" ")[0] + " ";
            apellido = apellido.trim();
            nombre = nombre.split(" ")[0];
        }
        try {
            daoJug.create(new Jugadores((int)(Math.random()*(2147483646+1-1)+1),nombre,apellido,null,0,0,null,null,Integer.parseInt(equipo)));
        }catch (Exception e)
        {
            if(e.getMessage().equals("Ya existe la id"))
            {
                //repetida la id, volver a generar el jugador con otra id
            }
        }
    }


}
