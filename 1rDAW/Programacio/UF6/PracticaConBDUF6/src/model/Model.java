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
        File ruta = new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius");
        File fileEquip = new File (ruta + "\\Equips.txt");
        File fileJug = new File (ruta + "\\Jugadores.txt");
        File filePart = new File (ruta + "\\Partidos.txt");
        File fileEstadíst = new File (ruta + "\\Estadísticas_jug.txt");
        File fileHist = new File (ruta + "\\Historic.txt");
        File docEx6 = new File(ruta + "\\DadesPartit.scv");

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
            docEx6.createNewFile();
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
        if(new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Equips.txt").length() == 0)
        {
            for (int i = 1; i <= 100; i++)
            {
                String nom = i+"nom";
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Equips.txt",
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
        nombreApelliod[1] = "";
        if(nombre.contains(" "))
        {
            for (int i = 1; i < nombre.split(" ").length; i++) nombreApelliod[1] += nombre.split(" ")[i] + " ";
            nombreApelliod[1] = nombreApelliod[1].trim();
            nombreApelliod[0] = nombre.split(" ")[0];
        }
        return nombreApelliod;
    }
    public static void crearJugadorEnEquipo(String nombre, String equipo, Connection con)
    {
        MYSQLJugadoresDAO daoJug = new MYSQLJugadoresDAO(con);
        String apellido = "";
        boolean idRep = true;
        int id_jugadorRepetit; // si el jugador ya existe aqui se guarda la id del juagdpr antiguo
        apellido = separarNombreEnApellido(nombre)[1];
        nombre = separarNombreEnApellido(nombre)[0];
        id_jugadorRepetit = comrpobarNombresCoincidir(nombre,apellido,con);
        if(id_jugadorRepetit == -1)
        {
            while (idRep)
            {
                    idRep = false;
                    int id = (int)(Math.random()*(2147483646+1-1)+1);
                    Jugadores j = new Jugadores(id,nombre,apellido,null,0,0,"0","",sacarIdEquipoConNombre(equipo,con));
                    if(!daoJug.exists(j)) daoJug.create(j);
                    else idRep = true;
            }
        }else
        {
            Vista.mostrarUnMisatgeGeneric("El nombre ya existe, transpassando jugador al nuevo equipo");
            moverJugador((nombre + " " + apellido), equipo, con);
        }

    }
    public static void moverJugador(String nom, String equipo, Connection con)
    {
        int id_equipo = sacarIdEquipoConNombre(equipo, con), id_jug;
        MYSQLJugadoresDAO DAOjug = new MYSQLJugadoresDAO(con);
        String apellido = "";
        apellido = separarNombreEnApellido(nom)[1];
        nom = separarNombreEnApellido(nom)[0];
        id_jug = trobaIdJugador(nom,apellido,con);

        if (id_equipo >= 0 )
        {
            Jugadores j = new Jugadores(id_jug);
            DAOjug.read(j);
            j.setEquip_id(id_equipo);
            DAOjug.update(j);
        }
    }
    public static int trobaIdJugador(String nom, String cognom, Connection con){

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
    public static void mostrarAVGJugador(int jugador_id, Connection con){
        MYSQLEstadisticas_jugadoresDAO statsDAO = new MYSQLEstadisticas_jugadoresDAO(con);
        ArrayList<Estadisticas_jugadores> stats_jugadors = new ArrayList<>();
        Estadisticas_jugadores mediaJug = new Estadisticas_jugadores(jugador_id);
        int totalPartits;

        statsDAO.read(jugador_id,stats_jugadors);

        stats_jugadors.forEach((e) ->
        {
            mediaJug.setTirs_anotats(mediaJug.getTirs_anotats() + e.getTirs_anotats());
            mediaJug.setTirs_tirats(mediaJug.getTirs_tirats() + e.getTirs_tirats());
            mediaJug.setTir_triples_anotats(mediaJug.getTir_triples_anotats() + e.getTir_triples_anotats());
            mediaJug.setTirs_triples_tirats(mediaJug.getTirs_triples_tirats() + e.getTirs_triples_tirats());
            mediaJug.setTirs_lliures_anotats(mediaJug.getTirs_lliures_anotats() + e.getTirs_lliures_anotats());
            mediaJug.setTir_lliures_tirats(mediaJug.getTir_lliures_tirats() + e.getTir_lliures_tirats());
            mediaJug.setRebots_defensius(mediaJug.getRebots_defensius() + e.getRebots_defensius());
            mediaJug.setRebots_ofensius(mediaJug.getRebots_ofensius() + e.getRebots_ofensius());
            mediaJug.setBloqueigs(mediaJug.getBloqueigs() + e.getBloqueigs());
            mediaJug.setRobades(mediaJug.getRobades() + e.getRobades());
            mediaJug.setAssistencies(mediaJug.getAssistencies() + e.getAssistencies());
            mediaJug.setMinuts_jugats(mediaJug.getMinuts_jugats() + e.getMinuts_jugats());
            mediaJug.setPunts(mediaJug.getPunts() + e.getPunts());
            mediaJug.setEquip_id(e.getEquip_id());
        });
        totalPartits = stats_jugadors.size();
        
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

    public static void editarJugador(String nom, int partit_id, Connection con)
    {
        MYSQLPartidosDAO partidosDAO = new MYSQLPartidosDAO(con);
        MYSQLEstadisticas_jugadoresDAO estadisticasDAO = new MYSQLEstadisticas_jugadoresDAO(con);


        if(partidosDAO.exists(new Partidos(partit_id))){
            int id_jugador = trobaIdJugador(separarNombreEnApellido(nom)[0],separarNombreEnApellido(nom)[1],con);
            ArrayList<Estadisticas_jugadores> totEst = new ArrayList<>();

            //Crear el objeto Estadisticas_jugadores con los nuevos datos menos idjug, idpart i idequip i insertar los datos donde toca

            estadisticasDAO.read(id_jugador,totEst);

        }else
        {
            System.out.println("El partido seleccionado no existe");
        }
    }

}
