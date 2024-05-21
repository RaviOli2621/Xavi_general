package model;

import vista.Vista;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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
    private static int comrpobarNombresCoincidir(String nom, String cognom, Connection con)
    {
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        MYSQLJugadoresDAO daoJug = new MYSQLJugadoresDAO(con);
        AtomicInteger id_jugador = new AtomicInteger(-1);

        daoJug.read(jugadores);

        jugadores.forEach((jug) ->
        {
            if(jug.getNom().equals(nom) && jug.getCognom().equals(cognom))
            {
                id_jugador.set(jug.jugador_id);
            }
        });
        return id_jugador.get();
    }
    private static int sacarIdEquipoConNombre(String equipo, Connection con)
    {
        ArrayList<Equipos> equipos = new ArrayList<>();
        //MYSQLEquiposDAO daoEqu = new MYSQLEquiposDAO(con);
        AtomicInteger id_jugador = new AtomicInteger(-1);

        //daoEqu.read(equipos);

        return 0;
    }

    public static void crearJugadorEnEquipo(String nombre, String equipo, Connection con)
    {
        MYSQLJugadoresDAO daoJug = new MYSQLJugadoresDAO(con);
        String apellido = "";
        boolean idRep = false;
        int id_jugadorRepetit; // si el jugador ya existe aqui se guarda la id del juagdpr antiguo
        if(nombre.contains(" "))
        {
            for (int i = 1; i < nombre.split(" ").length; i++) apellido += nombre.split(" ")[0] + " ";
            apellido = apellido.trim();
            nombre = nombre.split(" ")[0];
        }
        id_jugadorRepetit = comrpobarNombresCoincidir(nombre,apellido,con);
        if(id_jugadorRepetit >= 0)
        {
            while (idRep)
            {
                try {
                    idRep = false;
                    daoJug.create(new Jugadores((int)(Math.random()*(2147483646+1-1)+1),nombre,apellido,null,0,0,null,null,Integer.parseInt(equipo)));
                }catch (Exception e)
                {
                    if(e.getMessage().equals("Ya existe la id"))
                    {
                        idRep = true;
                    }
                }
            }
        }else
        {
            Vista.mostrarUnMisatgeGeneric("El nombre ya existe, transpassando jugador al nuevo equipo");
            moverJugador(id_jugadorRepetit);
        }

    }
    public static void moverJugador(int id, String equipo)
    {

    }


}
