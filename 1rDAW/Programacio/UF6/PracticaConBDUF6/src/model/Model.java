package model;

import vista.Vista;

import java.io.File;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Model
{
    //Generar dades
    public static void crearDocs()
    {
        File ruta = new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Datos");
        File fileEquip = new File (ruta + "\\Equips.txt");
        File fileJug = new File (ruta + "\\Jugadores.txt");
        File filePart = new File (ruta + "\\Partidos.txt");
        File fileEstadíst = new File (ruta + "\\Estadísticas_jug.txt");
        File fileHist = new File (ruta + "\\Historic.txt");

        if(ruta.mkdirs())
        {
            System.out.println("creado con exito");
        }
        try {
            fileEquip.createNewFile();
            fileJug.createNewFile();
            filePart.createNewFile();
            fileEstadíst.createNewFile();
            fileHist.createNewFile();
        }catch (Exception e)
        {
            System.out.println("Ha ocurrido un error en la creacion de los archivos necessarios para ejecutar el programa");
        }
    }
    public static void EditarDocumentos(String archivoConRuta, String mensaje)
    {
        String archivoATexto = "";

        try {
            // Guardar los datos originales del documento
            Scanner reader = new Scanner(new File(archivoConRuta));

            while (reader.hasNextLine())
            {
                archivoATexto += reader.nextLine().trim() + "\n";
            }

            // Añadir el mensaje extra
            PrintStream writer = new PrintStream(new File (archivoConRuta));
            writer.print(mensaje + "\n" + archivoATexto);

            writer.close();
            reader.close();
        }catch (Exception e)
        {
            System.out.println("Error en el processo de edicion de los archivos de texto:\n\t" + e.getMessage());
        }
    }
    public static void generarDades()
    {
        //Generar equipo (falta los partidos ganados y perdidos)
        if(new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Datos\\Equips.txt").length() == 0)
        {
            for (int i = 1; i <= 100; i++)
            {
                String nom = i+"nom";
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Datos\\Equips.txt",
                        i + "," + (i + "ciutat") + "," + nom + "," + nom.toUpperCase().substring(0,3) + "," +
                                ((int)(Math.random()*(4-1+1)+1) + "DIV") + "," + "Ganar" + "," + "Perder");
            }
        }
    }

    //Exersicis-----------------------------------------------------------------------------------------------------------------------------------
    public static void llistarJugadorsSegunEquipo(String equipo, Connection con)
    {
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        MYSQLJugadoresDAO dao = new MYSQLJugadoresDAO(con);

        dao.read(jugadores);

        jugadores.forEach((Jugadores j) ->
        {
            if(equipo.equals(j.equip_id + ""))
            {
                Vista.mostrarUnMisatgeGeneric(j.jugador_id + " " + j.nom);
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
                id_jugador.set(jug.getJugador_id());
            }
        });
        return id_jugador.get();
    }
    public static int sacarIdEquipoConNombre(String equipo, Connection con)
    {
        ArrayList<Equipos> equipos = new ArrayList<>();
        MYSQLEquiposDAO daoEqu = new MYSQLEquiposDAO(con);
        AtomicInteger id_equipo = new AtomicInteger(-1);

        daoEqu.read(equipos);

        equipos.forEach((eq) ->
        {
            if(eq.getNom().equals(equipo))
            {
                id_equipo.set(eq.getEquip_id());
            }
        });

        return id_equipo.get();
    }
    public static String[] separarNombreEnApellido(String nombre)
    {
        String []nombreApelliod = new String[2];
        if(nombre.contains(" "))
        {
            for (int i = 1; i < nombre.split(" ").length; i++) nombreApelliod[1] += nombre.split(" ")[0] + " ";
            nombreApelliod[1] = nombreApelliod[1].trim();
            nombreApelliod[0] = nombre.split(" ")[0];
        }
        return nombreApelliod;
    }
    public static void crearJugadorEnEquipo(String nombre, String equipo, Connection con)
    {
        MYSQLJugadoresDAO daoJug = new MYSQLJugadoresDAO(con);
        String apellido = "";
        boolean idRep = false;
        int id_jugadorRepetit; // si el jugador ya existe aqui se guarda la id del juagdpr antiguo
        apellido = separarNombreEnApellido(nombre)[1];
        nombre = separarNombreEnApellido(nombre)[0];
        id_jugadorRepetit = comrpobarNombresCoincidir(nombre,apellido,con);
        if(id_jugadorRepetit >= 0)
        {
            while (idRep)
            {
                try {
                    idRep = false;
                    daoJug.create(new Jugadores((int)(Math.random()*(2147483646+1-1)+1),nombre,apellido,null,0,0,null,null,sacarIdEquipoConNombre(equipo,con)));
                }catch (Exception e)
                {
                    if(e.getMessage().equals("Ya existe la id"))
                    {
                        idRep = true;
                    }
                    else
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }else
        {
            Vista.mostrarUnMisatgeGeneric("El nombre ya existe, transpassando jugador al nuevo equipo");
            moverJugador(id_jugadorRepetit, equipo, con);
        }

    }
    public static void moverJugador(int id, String equipo, Connection con)
    {
        int id_equipo = sacarIdEquipoConNombre(equipo, con);
        MYSQLJugadoresDAO DAOjug = new MYSQLJugadoresDAO(con);
        if (id_equipo >= 0 )
        {
            Jugadores j = new Jugadores(id);
            DAOjug.read(j);
            j.setEquip_id(id_equipo);
            DAOjug.update(j);
        }
    }
    public static int trobaId(String nom, String cognom, Connection con){

        ArrayList<Jugadores> jugadores = new ArrayList<>();
        MYSQLJugadoresDAO daoEqu = new MYSQLJugadoresDAO(con);
        AtomicInteger jugador_id = new AtomicInteger(-1);
        daoEqu.read(jugadores);

        jugadores.forEach((eq) ->
        {
            if(eq.getNom().equals(nom) && eq.getCognom().equals(cognom))
            {
                jugador_id.set(eq.getEquip_id());
            }
        });
        return jugador_id.get();
    }
    public static void mostrarAVGJugaor(int jugador_id, Connection con){
        MYSQLEstadisticas_jugadoresDAO statsDAO = new MYSQLEstadisticas_jugadoresDAO(con);
        Estadisticas_jugadores stats = new Estadisticas_jugadores(jugador_id);
        ArrayList<Estadisticas_jugadores> stats_jug = new ArrayList<>();

        statsDAO.read(stats);
        statsDAO.read(stats_jug);
        //todo: faltan muchas cosas por hacer, no hace nada

    }

    public static void partidosDelEquipo(int id, Connection con)
    {
        MYSQLPartidosDAO partDAO = new MYSQLPartidosDAO(con);
        MYSQLEquiposDAO equDAO = new MYSQLEquiposDAO(con);
        ArrayList<Partidos> part = new ArrayList<>();
        ArrayList<Partidos> partDeUnEquipo = new ArrayList<>();
        ArrayList<Equipos> equipos = new ArrayList<>();

        partDAO.read(part);
        equDAO.read(equipos);

        part.forEach((p) ->
        {
            if(p.equip_id == id) partDeUnEquipo.add(p);
        });

        partDeUnEquipo.forEach((p) ->
        {
            AtomicReference<String> local = new AtomicReference<>();
            AtomicReference<String> vis = new AtomicReference<>();
            equipos.forEach((e) ->
            {
                if(id == e.equip_id) local.set(e.nom);
                if((p.matx.split(" @ ").length > 1))
                {
                    if(p.matx.split(" @ ")[1].equals(e.getAcronim())) vis.set(e.getNom());
                }
                if(p.matx.split(" vs. ").length > 1)
                {
                    if(p.matx.split(" vs. ")[1].equals(e.getAcronim())) vis.set(e.getNom());
                }
            });
            Vista.mostrarUnMisatgeGeneric(local + " vs " + vis + ": " + p.resultat);
        });

    }


}
