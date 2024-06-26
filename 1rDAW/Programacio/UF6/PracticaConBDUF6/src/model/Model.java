package model;

import controlador.Controlador;
import vista.Vista;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
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

        if(ruta.mkdirs())
        {
            Vista.mostrarUnMisatgeGeneric("creado con exito");
        }
        try {
            fileEquip.createNewFile();
            fileJug.createNewFile();
            filePart.createNewFile();
            fileEstadíst.createNewFile();
            fileHist.createNewFile();
        }catch (Exception e)
        {
            Vista.mostrarUnMisatgeGeneric("Ha ocurrido un error en la creacion de los archivos necessarios para ejecutar el programa");
        }
    }

    //Editar un document
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
            Vista.mostrarUnMisatgeGeneric("Error en el processo de edicion de los archivos de texto:\n\t" + e.getMessage());
        }
    }

    //generar dades aleatories
    public static void generarDades()
    {
        //Generar equipo (falta los partidos ganados y perdidos)
        if(new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Equips.txt").length() == 0)
        {
            for (int i = 1; i <= 100; i++)
            {
                String nom = i+"equip";
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Equips.txt",
                        i + "," + (i + "ciutat") + "," + nom + "," + nom.toUpperCase().substring(0,3) + "," +
                                ((int)(Math.random()*(4-1+1)+1) + "DIV") + "," + (int)(Math.random()*(100-1+1)+1) + "," + (int)(Math.random()*(100-1+1)+1));
            }
        }
        //Generar Jugador
        if(new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Jugadores.txt").length() == 0)
        {
            for (int i = 1; i <= 100; i++)
            {

                String nom = i+"jugador";
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Jugadores.txt",
                        i + "," + nom + "," + "cognom" + "," + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "," +
                                (int)(Math.random()*(90-1+1)+1) + "," + (int)(Math.random()*(90-1+1)+1) + "," + (int)(Math.random()*(90-1+1)+1)
                                + "," + "posicio" + "," + i);
            }
        }
        //Generar partido
        if(new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Partidos.txt").length() == 0)
        {
            for (int i = 1; i <= 100; i++)
            {
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Partidos.txt",
                        i + "," + i + "," + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "," + ((i+"EQUIP").substring(0,3) + " vs " + ((i+1)+"EQUIP").substring(0,3)) + "," +
                                'W');
                i++;
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Partidos.txt",
                        i-1 + "," + i + "," + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "," + ((i+"EQUIP").substring(0,3) + " vs " + ((i-1)+"EQUIP").substring(0,3)) + "," +
                                'L');
            }
        }
        //Generar Estadisticas_Jugador
        if(new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Estadísticas_jug.txt").length() == 0)
        {
            for (int i = 1; i <= 100; i++)
            {
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Estadísticas_jug.txt",
                        i + "," + i + "," + 48.0 + "," + ((int)(Math.random()*(40-1+1)+1))
                                + "," + ((int)(Math.random()*(40-1+1)+1)) + "," + ((int)(Math.random()*(40-1+1)+1))
                                + "," + ((int)(Math.random()*(40-1+1)+1)) + "," + ((int)(Math.random()*(40-1+1)+1))
                                + "," + ((int)(Math.random()*(2-1+1)+1)) + "," + ((int)(Math.random()*(40-1+1)+1))
                                + "," + ((int)(Math.random()*(40-1+1)+1)) + "," + ((int)(Math.random()*(40-1+1)+1))
                                + "," + ((int)(Math.random()*(40-1+1)+1)) + "," + ((int)(Math.random()*(40-1+1)+1))
                                + "," + ((int)(Math.random()*(40-1+1)+1)) + "," + i);
            }
        }
        //Generar Historic
        if(new File(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Historic.txt").length() == 0)
        {
            for (int i = 101; i <= 111; i++)
            {
                String nom = i+"jugador";
                EditarDocumentos(".\\1rDAW\\Programacio\\UF6\\PracticaConBDUF6\\Arxius\\Historic.txt",
                        nom + "," + i + "," + i + "," + 24.0 + "," + ((int)(Math.random()*(29-1+1)+1))
                                + "," + ((int)(Math.random()*(24-1+1)+1)) + "," + ((int)(Math.random()*(29-1+1)+1))
                                + "," + ((int)(Math.random()*(29-1+1)+1)) + "," + ((int)(Math.random()*(29-1+1)+1))
                                + "," + ((int)(Math.random()*(29-1+1)+1)) + "," + ((int)(Math.random()*(29-1+1)+1))
                                + "," + ((int)(Math.random()*(29-1+1)+1)) + "," + ((int)(Math.random()*(29-1+1)+1))
                                + "," + ((int)(Math.random()*(29-1+1)+1)) + "," + ((int)(Math.random()*(29-1+1)+1))
                                + "," + ((int)(Math.random()*(29-1+1)+1)));
            }
        }

    }

    //Inserir documents generats a la base de dades
    public static void insertarDatos(Connection con)
    {
        File [] archivos = new File("./1RDAW/Programacio/UF6/PracticaConBDUF6/Arxius").listFiles();

        for (int i = 0; i < archivos.length-1; i++)
        {
            try {
                FileReader reader = new FileReader(archivos[i]);
                BufferedReader breader = new BufferedReader(reader);

                if(archivos[i].getName().toLowerCase().contains("equips"))
                {
                    inserirEquip(breader,con);
                } else if (archivos[i].getName().toLowerCase().contains("jugadores")) {
                    inserirJugadors(breader,con);   
                }else if (archivos[i].getName().toLowerCase().contains("partidos"))
                {
                    inserirPartido(breader,con);
                }else if (archivos[i].getName().toLowerCase().contains("estadísticas_jug"))
                {
                    inserirEstadist(breader,con);
                } else if (archivos[i].getName().toLowerCase().contains("historic")) {
                    inserirHistorial(breader,con);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

    }

    //Crear un equip a la base de dades
    private static void inserirEquip(BufferedReader breader, Connection con) throws IOException {
        MYSQLEquiposDAO equiposDAO = new MYSQLEquiposDAO(con);
        String linea;

        while ((linea = breader.readLine()) != null)
        {
            String []lineaSep = linea.split(",");
            equiposDAO.create(new Equipos(Integer.parseInt(lineaSep[0]),Integer.parseInt(lineaSep[5]),Integer.parseInt(lineaSep[6])
                    ,lineaSep[2],lineaSep[1],lineaSep[3],lineaSep[4]));
        }
    }

    //Crear un jugador a la base de dades
    private static void inserirJugadors(BufferedReader breader, Connection con) throws IOException, ParseException {
        MYSQLJugadoresDAO jugadoresDAO = new MYSQLJugadoresDAO(con);
        String linea;
        while ((linea = breader.readLine()) != null)
        {
            String []lineaSep = linea.split(",");
            Date parsed = new SimpleDateFormat("yyyy-MM-dd").parse(lineaSep[3]);

            jugadoresDAO.create(new Jugadores(Integer.parseInt(lineaSep[0]),lineaSep[1],lineaSep[2]
                    ,(new Date(parsed.getTime())),Float.parseFloat(lineaSep[4]),Float.parseFloat(lineaSep[5]),
                    lineaSep[6],lineaSep[7],Integer.parseInt(lineaSep[8])));
        }
    }

    private static void inserirPartido(BufferedReader breader, Connection con) throws IOException, ParseException {
        MYSQLPartidosDAO partidosDAO = new MYSQLPartidosDAO(con);
        String linea;

        while ((linea = breader.readLine()) != null)
        {
            String []lineaSep = linea.split(",");
            Date parsed = new SimpleDateFormat("yyyy-MM-dd").parse(lineaSep[2]);
            
            partidosDAO.create(new Partidos(Integer.parseInt(lineaSep[0]),Integer.parseInt(lineaSep[1]),lineaSep[4],
                    new Date(parsed.getTime()),lineaSep[3]));
        }
    }
    private static void inserirEstadist(BufferedReader breader, Connection con) throws IOException {
        MYSQLEstadisticas_jugadoresDAO estDAO = new MYSQLEstadisticas_jugadoresDAO(con);
        String linea;

        while ((linea = breader.readLine()) != null)
        {
            String []lineaSep = linea.split(",");
            estDAO.create(new Estadisticas_jugadores(Integer.parseInt(lineaSep[0]),Integer.parseInt(lineaSep[1]),Float.parseFloat(lineaSep[14])
            ,Float.parseFloat(lineaSep[3]),Float.parseFloat(lineaSep[4]),Float.parseFloat(lineaSep[5]),Float.parseFloat(lineaSep[6])
            ,Float.parseFloat(lineaSep[7]),Float.parseFloat(lineaSep[8]),Float.parseFloat(lineaSep[9]),Float.parseFloat(lineaSep[10])
            ,Float.parseFloat(lineaSep[11]),Float.parseFloat(lineaSep[12]),Float.parseFloat(lineaSep[13]),Float.parseFloat(lineaSep[2])
            ,Integer.parseInt(lineaSep[15])));
        }
    }
    private static void inserirHistorial(BufferedReader breader, Connection con) throws IOException {
        MYSQLHistoricoDAO historicoDAO = new MYSQLHistoricoDAO(con);
        String linea;

        while ((linea = breader.readLine()) != null)
        {
            String []lineaSep = linea.split(",");
            historicoDAO.create(new Historico(lineaSep[0],Integer.parseInt(lineaSep[1]),Integer.parseInt(lineaSep[2])
                    ,Integer.parseInt(lineaSep[15]),Integer.parseInt(lineaSep[4]),Integer.parseInt(lineaSep[5]),Integer.parseInt(lineaSep[6])
                    ,Integer.parseInt(lineaSep[7]),Integer.parseInt(lineaSep[8]),Integer.parseInt(lineaSep[9]),Integer.parseInt(lineaSep[10])
                    ,Integer.parseInt(lineaSep[11]),Integer.parseInt(lineaSep[12]),Integer.parseInt(lineaSep[13]),Integer.parseInt(lineaSep[14])
                    ,Float.parseFloat(lineaSep[3])));
        }
    }

    //Exersicis-----------------------------------------------------------------------------------------------------------------------------------

    //Tractament de dades d'un equip per llistar els seus jugadors
    public static void llistarJugadorsSegunEquipo(String equipo, Connection con)
    {
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        MYSQLJugadoresDAO dao = new MYSQLJugadoresDAO(con);
        int id_equipo = sacarIdEquipoConNombre(equipo, con);
        dao.read(jugadores);
        
        //Mostra el nom y ID dels jugadors segons l'ID de l'equip
        jugadores.forEach((Jugadores j) ->
        {
            if(id_equipo == j.equip_id)
            {
                Vista.mostrarUnMisatgeGeneric(j.jugador_id + " " + j.nom);
            }
        });
    }
    
    //Comprova si coincideixen 2 noms y retorna l'ID si el troba
    private static int comrpobarNombresCoincidir(String nom, String cognom, Connection con)
    {
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        MYSQLJugadoresDAO daoJug = new MYSQLJugadoresDAO(con);
        AtomicInteger id_jugador = new AtomicInteger(-1);

        daoJug.read(jugadores);
        
        //comparo cada nom compet dels jugadors amb els rebuts per parametre
        jugadores.forEach((jug) ->
        {
            if(jug.getNom().equals(nom) && jug.getCognom().equals(cognom))
            {
                id_jugador.set(jug.getJugador_id());
            }
        });
        return id_jugador.get();
    }
    
    //Comprova el nom de l'equip y troba l'ID
    public static int sacarIdEquipoConNombre(String equipo, Connection con)
    {
        ArrayList<Equipos> equipos = new ArrayList<>();
        MYSQLEquiposDAO daoEqu = new MYSQLEquiposDAO(con);
        AtomicInteger id_equipo = new AtomicInteger(-1);

        daoEqu.read(equipos);

        //comparo cada nom dels equips amb els rebuts per parametre
        equipos.forEach((eq) ->
        {
            if(eq.getNom().equals(equipo))
            {
                id_equipo.set(eq.getEquip_id());
            }
        });

        return id_equipo.get();
    }

    //Separa el nom compet en nom y cognom y els retorna
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

    //Crea un jugador nou en un equip y si ja existeix el mou a un altre
    public static void crearJugadorEnEquipo(String nombre, String equipo, Connection con)
    {
        MYSQLJugadoresDAO daoJug = new MYSQLJugadoresDAO(con);
        String apellido;
        boolean idRep = true;
        int id_jugadorRepetit; // si el jugador ya existe aqui se guarda la id del juagdpr antiguo
        apellido = separarNombreEnApellido(nombre)[1];
        nombre = separarNombreEnApellido(nombre)[0];
        id_jugadorRepetit = comrpobarNombresCoincidir(nombre,apellido,con);

        //genero el jugador amb ID random
        if(id_jugadorRepetit == -1)
        {
            while (idRep)
            {
                    idRep = false;
                    int id = (int)(Math.random()*(2147483646+1-1)+1);
                    Jugadores j = new Jugadores(id,nombre,apellido,new Date(),0,0,"0","",sacarIdEquipoConNombre(equipo,con));
                    if(!daoJug.exists(j)) daoJug.create(j);
                    else idRep = true;
            }
        }else
        {
            Vista.mostrarUnMisatgeGeneric("El nombre ya existe, transpassando jugador al nuevo equipo");
            moverJugador((nombre + " " + apellido), equipo, con);
        }

    }

    //Mou un jugador a un equip
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

    //Trova l'ID d'un jugador per el nom
    public static int trobaIdJugador(String nom, String cognom, Connection con){

        ArrayList<Jugadores> jugadores = new ArrayList<>();
        MYSQLJugadoresDAO daoEqu = new MYSQLJugadoresDAO(con);
        AtomicInteger jugador_id = new AtomicInteger(-1);
        daoEqu.read(jugadores);

        //compara tots els noms complets dels jugadors amb els rebuts per patametre
        jugadores.forEach((eq) ->
        {
            if(eq.getNom().equals(nom) && eq.getCognom().equals(cognom))
            {
                jugador_id.set(eq.getJugador_id());
            }
        });
        return jugador_id.get();
    }

    //Tractament de dades per mostrar la mitja d'estadistiques d'un jugador
    public static void mostrarAVGJugador(int jugador_id, Connection con){
        Estadisticas_jugadores mediaJug = new Estadisticas_jugadores(jugador_id);

        prepararAVGJugador(mediaJug, jugador_id, con);
        Vista.mostrarEstadisticas_jugadores(mediaJug);
    }

    //Guardem l'es estadistiques d'un sol jugador en un objecte per calcular la seva mitja
    private static void prepararAVGJugador(Estadisticas_jugadores mediaJug, int jugador_id, Connection con){
        MYSQLEstadisticas_jugadoresDAO statsDAO = new MYSQLEstadisticas_jugadoresDAO(con);
        ArrayList<Estadisticas_jugadores> stats_jugadors = new ArrayList<>();
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
        calcularAVG(mediaJug, totalPartits);
    }

    //Fa el calcul de la mitja d'estadistiques d'un jugador segons els sus partits
    private static void calcularAVG(Estadisticas_jugadores mediaJug, int totalPartits){

        mediaJug.setTirs_anotats(mediaJug.getTirs_anotats() / totalPartits);
        mediaJug.setTirs_tirats(mediaJug.getTirs_tirats() / totalPartits);
        mediaJug.setTir_triples_anotats(mediaJug.getTir_triples_anotats() / totalPartits);
        mediaJug.setTirs_triples_tirats(mediaJug.getTirs_triples_tirats() / totalPartits);
        mediaJug.setTirs_lliures_anotats(mediaJug.getTirs_lliures_anotats() / totalPartits);
        mediaJug.setTir_lliures_tirats(mediaJug.getTir_lliures_tirats() / totalPartits);
        mediaJug.setRebots_defensius(mediaJug.getRebots_defensius() / totalPartits);
        mediaJug.setRebots_ofensius(mediaJug.getRebots_ofensius() / totalPartits);
        mediaJug.setBloqueigs(mediaJug.getBloqueigs() / totalPartits);
        mediaJug.setRobades(mediaJug.getRobades() / totalPartits);
        mediaJug.setAssistencies(mediaJug.getAssistencies() / totalPartits);
        mediaJug.setMinuts_jugats(mediaJug.getMinuts_jugats() / totalPartits);
        mediaJug.setPunts(mediaJug.getPunts() / totalPartits);
    }

    //Tractament de dades per llistar els partits d'un equip
    public static void partidosDelEquipo(int id, Connection con)
    {
        MYSQLPartidosDAO partDAO = new MYSQLPartidosDAO(con);
        MYSQLEquiposDAO equDAO = new MYSQLEquiposDAO(con);
        ArrayList<Partidos> part = new ArrayList<>();
        ArrayList<Partidos> partDeUnEquipo = new ArrayList<>();
        ArrayList<Equipos> equipos = new ArrayList<>();

        partDAO.read(part);
        equDAO.read(equipos);

        //Comparem l'ID dels equips amb el rebut per parametre
        part.forEach((p) ->
        {
            if(p.equip_id == id) partDeUnEquipo.add(p);
        });

        //Mostrar cada enfrontament
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
                if(p.matx.split(" vs ").length > 1)
                {
                    if(p.matx.split(" vs ")[1].equals(e.getAcronim())) vis.set(e.getNom());
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
            Estadisticas_jugadores estadAModif = new Estadisticas_jugadores(id_jugador);
            estadisticasDAO.read(id_jugador,totEst);

            totEst.forEach((e) ->
            {
                if(e.getPartit_id() == partit_id)
                {
                    estadAModif.setPartit_id(partit_id);
                    estadAModif.setEquip_id(e.getEquip_id());
                    estadAModif.setPunts(e.getPunts());
                    estadAModif.setTirs_anotats(e.getTirs_anotats());
                    estadAModif.setTirs_tirats(e.getTirs_tirats());
                    estadAModif.setTir_triples_anotats(e.getTir_triples_anotats());
                    estadAModif.setTirs_triples_tirats(e.getTirs_triples_tirats());
                    estadAModif.setTirs_lliures_anotats(e.getTirs_lliures_anotats());
                    estadAModif.setTir_lliures_tirats(e.getTir_lliures_tirats());
                    estadAModif.setRebots_ofensius(e.getRebots_ofensius());
                    estadAModif.setRebots_defensius(e.getRebots_defensius());
                    estadAModif.setAssistencies(e.getAssistencies());
                    estadAModif.setRobades(e.getRobades());
                    estadAModif.setBloqueigs(e.getBloqueigs());
                    estadAModif.setMinuts_jugats(e.getMinuts_jugats());
                }
            });
            Controlador.editarElJugador(estadAModif);
            estadisticasDAO.update(estadAModif);
            //Crear el objeto Estadisticas_jugadores con los nuevos datos menos idjug, idpart i idequip i insertar los datos donde toca
        }else
        {
            Vista.mostrarUnMisatgeGeneric("El partido seleccionado no existe");
        }
    }

    private static void prepararSUMJugador(Estadisticas_jugadores sumJugador, int jugador_id, Connection con){
        MYSQLEstadisticas_jugadoresDAO statsDAO = new MYSQLEstadisticas_jugadoresDAO(con);
        ArrayList<Estadisticas_jugadores> stats_jugadors = new ArrayList<>();

        statsDAO.read(jugador_id,stats_jugadors);

        stats_jugadors.forEach((e) ->
        {
            sumJugador.setTirs_anotats(sumJugador.getTirs_anotats() + e.getTirs_anotats());
            sumJugador.setTirs_tirats(sumJugador.getTirs_tirats() + e.getTirs_tirats());
            sumJugador.setTir_triples_anotats(sumJugador.getTir_triples_anotats() + e.getTir_triples_anotats());
            sumJugador.setTirs_triples_tirats(sumJugador.getTirs_triples_tirats() + e.getTirs_triples_tirats());
            sumJugador.setTirs_lliures_anotats(sumJugador.getTirs_lliures_anotats() + e.getTirs_lliures_anotats());
            sumJugador.setTir_lliures_tirats(sumJugador.getTir_lliures_tirats() + e.getTir_lliures_tirats());
            sumJugador.setRebots_defensius(sumJugador.getRebots_defensius() + e.getRebots_defensius());
            sumJugador.setRebots_ofensius(sumJugador.getRebots_ofensius() + e.getRebots_ofensius());
            sumJugador.setBloqueigs(sumJugador.getBloqueigs() + e.getBloqueigs());
            sumJugador.setRobades(sumJugador.getRobades() + e.getRobades());
            sumJugador.setAssistencies(sumJugador.getAssistencies() + e.getAssistencies());
            sumJugador.setMinuts_jugats(sumJugador.getMinuts_jugats() + e.getMinuts_jugats());
            sumJugador.setPunts(sumJugador.getPunts() + e.getPunts());
            sumJugador.setEquip_id(e.getEquip_id());
        });
    }
    public static void moverAHistoric(String nom, Connection con)
    {
        int id = trobaIdJugador(separarNombreEnApellido(nom)[0], separarNombreEnApellido(nom)[1],con);
        MYSQLHistoricoDAO histDAO = new MYSQLHistoricoDAO(con);
        Historico hist = new Historico(id);
        MYSQLJugadoresDAO jugDAO = new MYSQLJugadoresDAO(con);
        Jugadores jug = new Jugadores(id);
        jugDAO.read(jug);
        Estadisticas_jugadores sumJugador = new Estadisticas_jugadores(id);
        prepararSUMJugador(sumJugador,id,con);

        hist.setNom((jug.getNom() + " " + jug.getCognom()));
        hist.setUltim_Equip_id(jug.getEquip_id());
        hist.setTot_min_jugats(sumJugador.getMinuts_jugats());
        hist.setPunts_tot((int)sumJugador.getPunts());
        hist.setTirs_anotats((int)sumJugador.getTirs_anotats());
        hist.setTirs_tirats((int)sumJugador.getTirs_tirats());
        hist.setTir_triples_anotats((int)sumJugador.getTir_triples_anotats());
        hist.setTirs_triples_tirats((int)sumJugador.getTirs_triples_tirats());
        hist.setTirs_lliures_anotats((int)sumJugador.getTirs_lliures_anotats());
        hist.setTir_lliures_tirats((int)sumJugador.getTir_lliures_tirats());
        hist.setRebots_ofensius((int)sumJugador.getRebots_ofensius());
        hist.setRebots_defensius((int)sumJugador.rebots_defensius);
        hist.setAssistencies((int)sumJugador.getAssistencies());
        hist.setRobades((int)sumJugador.getRobades());
        hist.setBloqueigs((int)sumJugador.getBloqueigs());

        histDAO.create(hist);
        jugDAO.delete(jug);
    }
    public static void actualizarDadesPartit(File doc, Connection con){
        String linea, separador = ";", tirs_anotats = "tirs_anotats", tirs_tirats = "tirs_tirats",
                tirs_triples_anotats = "tirs_triples_anotats", tirs_triples_tirats = "tirs_triples_tirats",
                tirs_lliures_anotats = "tirs_lliures_anotats", tir_lliures_tirats = "tir_lliures_tirats",
                rebots_ofensius = "rebots_ofensius", rebots_defensius = "rebots_defensius", assistencies = "assistencies",
                robades = "robades", bloqueigs = "bloqueigs", punts = "punts", minuts_jugats = "minuts_jugats",
                jug_id = "jugador_id", equip_id = "equip_id", part = "partit";

        File []archivos = doc.listFiles();
        for (int i = 0; i < archivos.length; i++)
        {
            MYSQLEstadisticas_jugadoresDAO statsDAO = new MYSQLEstadisticas_jugadoresDAO(con);
            Estadisticas_jugadores stats = new Estadisticas_jugadores(0);
            boolean jugadortrobat = true;

            try {
                FileReader reader = new FileReader(archivos[i]);
                BufferedReader breader = new BufferedReader(reader);

                //compruevo si en el documento esta el partido y jugador
                while ((linea = breader.readLine()) != null){
                    String[] paraules = linea.split(separador);

                    if(paraules[0].equals(jug_id)){
                        stats = new Estadisticas_jugadores(Integer.parseInt(paraules[1]));
                    }
                    if(paraules[0].equals(part)){
                        stats.setPartit_id(Integer.parseInt(paraules[1]));
                        break;
                    }
                }
                statsDAO.read(stats);

                //compruevo cada campo y lo guardo en el objeto
                while ((linea = breader.readLine()) != null){
                    if(jugadortrobat){

                        String[] paraules = linea.split(separador);

                        if(paraules[0].equals(tirs_anotats)){
                            stats.setTirs_anotats(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(tirs_tirats)){
                            stats.setTirs_tirats(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(tirs_triples_anotats)){
                            stats.setTir_triples_anotats(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(tirs_triples_tirats)){
                            stats.setTirs_triples_tirats(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(tirs_lliures_anotats)){
                            stats.setTirs_lliures_anotats(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(tir_lliures_tirats)){
                            stats.setTir_lliures_tirats(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(rebots_ofensius)){
                            stats.setRebots_ofensius(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(rebots_defensius)){
                            stats.setRebots_defensius(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(assistencies)){
                            stats.setAssistencies(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(robades)){
                            stats.setRobades(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(bloqueigs)){
                            stats.setBloqueigs(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(punts)){
                            stats.setPunts(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(minuts_jugats)){
                            stats.setMinuts_jugats(Float.parseFloat(paraules[1]));
                        }
                        if(paraules[0].equals(equip_id)){
                            stats.setEquip_id(Integer.parseInt(paraules[1]));
                        }

                    }else {
                        Vista.mostrarUnMisatgeGeneric("El jugador no apareix en el partit");
                    }
                }
                Vista.mostrarUnMisatgeGeneric("Estadistiques del jugador '" + stats.getPartit_id() + "' actualitzades");

            }catch (IOException e){
                Vista.mostrarUnMisatgeGeneric(e.getMessage());
            }catch (InputMismatchException ඞ){
                Vista.mostrarUnMisatgeGeneric(ඞ.getMessage());
            }
            statsDAO.update(stats);
        }
    }
    public static void editarEquip(int equip_id, Connection con){
        MYSQLEquiposDAO equipDAO = new MYSQLEquiposDAO(con);

        if(equipDAO.exists(new Equipos(equip_id))){
            Equipos equipoAMod = new Equipos(equip_id);
            equipDAO.read(equipoAMod);

            Controlador.editarElEquipo(equipoAMod);
            equipDAO.update(equipoAMod);
        }else
        {
            Vista.mostrarUnMisatgeGeneric("El equipo seleccionado no existe");
        }
    }

}
