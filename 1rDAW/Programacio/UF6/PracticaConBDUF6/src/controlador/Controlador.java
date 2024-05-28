package controlador;
import model.*;
import vista.Vista;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLData;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controlador
{
    static Scanner scan = new Scanner(System.in);
    private static Connection con;
    public static void comenzarPrograma()
    {
        String url = "jdbc:mysql://192.168.56.103:3306/NBAData";
        String username = "perepi";
        String paswrd = "pastanaga";
        openCon(url,username,paswrd);
        Model.crearDocs();
        Model.generarDades();
        consultes();
        closeCon();
    }
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
    public static void closeCon() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            Vista.mostrarUnMisatgeGeneric(e.getMessage());
        }
    }

    public static void consultes()
    {
        String respuesta;
        Vista.mostrarMenu();
        respuesta = scan.next().trim();
        scan.nextLine();
        switch (respuesta)
        {
            case "1":
                pregunta1();
                break;
            case "2":
                pregunta2();
                break;
            case "3":
                pregunta3();
                break;
            case "4":
                pregunta4();
                break;
            case "5":
                pregunta5();
                break;
            case "6":
                pregunta6();
                break;
            case "7":
                pregunta7();
                break;
            case "8":
                pregunta8();
                break;
            case "9":
                pregunta9();
                break;
            case "0":
                Vista.mostrarUnMisatgeGeneric("Sortint...");
                break;
            default:
                Vista.mostrarUnMisatgeGeneric("Te equivocaste");
        }
    }

    private static void pregunta1()
    {
        String respuesta;
        Vista.mostrarUnMisatgeGeneric("Que equipo quieres ver los jugadores?");
        respuesta = scan.nextLine().trim();
        Model.llistarJugadorsSegunEquipo(respuesta,con);
    }

    private static void pregunta2()
    {
        String nom;
        String [] jugador;
        int jugador_id;

        Vista.mostrarUnMisatgeGeneric("De quin jugador vols veure la mitjana d'estadistiques?");
        nom = scan.nextLine();
        jugador = Model.separarNombreEnApellido(nom);
        jugador_id = Model.trobaIdJugador(jugador[0], jugador[1], con);
        Model.mostrarAVGJugador(jugador_id,con);
    }

    private static void pregunta3()
    {
        String nom;
        int id;

        Vista.mostrarUnMisatgeGeneric("Que equipo quieres ver los partidos");
        nom = scan.nextLine().trim();
        id = Model.sacarIdEquipoConNombre(nom, con);
        Model.partidosDelEquipo(id,con);
    }

    private static void pregunta4()
    {
        String nombre, equipo;

        Vista.mostrarUnMisatgeGeneric("Indique el nombre completo del jugador");
        nombre = scan.nextLine().trim();
        Vista.mostrarUnMisatgeGeneric("Ahora indique su equipo");
        equipo = scan.nextLine().trim();
        Model.crearJugadorEnEquipo(nombre,equipo,con);
    }

    private static void pregunta5()
    {
        String nom, equipo;

        Vista.mostrarUnMisatgeGeneric("Indique el nombre completo del jugador a mover");
        nom = scan.nextLine().trim();
        Vista.mostrarUnMisatgeGeneric("Ahora indique su nuevo equipo");
        equipo = scan.nextLine().trim();
        Model.moverJugador(nom,equipo,con);
    }

    private static void pregunta6()
    {
        File doc;

        doc = new File("./1RDAW/Programacio/UF6/PracticaConBDUF6/Arxius/partits");

        Model.actualizarDadesPartit(doc, con);

    }

    private static void pregunta7()
    {
        String nom;
        int partit_id;

        Vista.mostrarUnMisatgeGeneric("Indique el nombre completo del jugador");
        nom = scan.nextLine().trim();
        Vista.mostrarUnMisatgeGeneric("Ahora indique su partido_id");
        try {
            partit_id = scan.nextInt();
        }catch (InputMismatchException i) {partit_id = 0;}
        Model.editarJugador(nom,partit_id,con);
    }

    private static void pregunta8()
    {
        String nom;

        Vista.mostrarUnMisatgeGeneric("Introduzca el nombre completo del jugador al qual quieres jubilar");
        nom = scan.nextLine().trim();
        Model.moverAHistoric(nom, con);
    }

    private static void pregunta9()
    {
        String nom;

        Vista.mostrarUnMisatgeGeneric("Indique el nombre del equipo");
        nom = scan.nextLine().trim();
        Model.editarEquip(Model.sacarIdEquipoConNombre(nom, con),con);
    }
    public static Estadisticas_jugadores editarElJugador(Estadisticas_jugadores est)//funcion complementaria a editarJugador
    {
        Scanner scan = new Scanner(System.in);
        boolean mantenerBucle = true;
        while (mantenerBucle)
        {
            Vista.editarJugadorDades();
            switch (scan.next())
            {
                case "1":
                    Vista.mostrarUnMisatgeGeneric("Introduzca el nuevo id del equipo:");
                    try {
                        est.setEquip_id(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "2":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los puntos totales del jugador:");
                    try {
                        est.setPunts(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "3":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los tiros metidos:");
                    try {
                        est.setTirs_anotats(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "4":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los tiros tirados");
                    try {
                        est.setTirs_tirats(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "5":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los triple metidos");
                    try {
                        est.setTir_triples_anotats(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "6":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los triple tirados");
                    try {
                        est.setTirs_triples_tirats(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "7":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los tiros libres metidos");
                    try {
                        est.setTirs_lliures_anotats(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "8":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los tiros libres tirados");
                    try {
                        est.setTir_lliures_tirats(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "9":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los rebotes ofensivos");
                    try {
                        est.setRebots_ofensius(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "10":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los rebotes defensivos");
                    try {
                        est.setRebots_defensius(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "11":
                    Vista.mostrarUnMisatgeGeneric("Introduzca las assistencias");
                    try {
                        est.setAssistencies(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "12":
                    Vista.mostrarUnMisatgeGeneric("Introduzca las robadas");
                    try {
                        est.setRobades(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "13":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los bloqueos");
                    try {
                        est.setBloqueigs(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "14":
                    Vista.mostrarUnMisatgeGeneric("Introduzca los minutos jugados");
                    try {
                        est.setMinuts_jugats(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "0":
                    mantenerBucle = false;
                    break;
                default:
                    Vista.mostrarUnMisatgeGeneric("Te equivocaste mi loco");
                    break;
            }
        }
        Vista.mostrarUnMisatgeGeneric("Los datos modificados estan siendo subidos a la base de datos");
        return est;
    }
    public static Equipos editarElEquipo(Equipos eq)//funcion complementaria a editarJugador
    {
        Scanner scan = new Scanner(System.in);
        boolean mantenerBucle = true;
        while (mantenerBucle)
        {
            Vista.editarEquipDades();
            switch (scan.nextLine().trim())
            {
                case "1":
                    Vista.mostrarUnMisatgeGeneric("Introdueix el nom de la ciutat (Valor actual: " + eq.getCiutat() + "):");
                    try {
                        eq.setCiutat(scan.nextLine());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "2":
                    Vista.mostrarUnMisatgeGeneric("Introdueix el nom de l'equip:");
                    try {
                        eq.setNom(scan.nextLine());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "3":
                    Vista.mostrarUnMisatgeGeneric("Introdueix l'acronim");
                    try {
                        eq.setAcronim(scan.nextLine());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "4":
                    Vista.mostrarUnMisatgeGeneric("Introdueix la divisio");
                    try {
                        eq.setDivisio(scan.nextLine());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "5":
                    Vista.mostrarUnMisatgeGeneric("Introdueix el numero de victories");
                    try {
                        eq.setGuanyades(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "6":
                    Vista.mostrarUnMisatgeGeneric("Introdueix el numero de derrotes");
                    try {
                        eq.setPerdudes(scan.nextInt());
                    }catch (InputMismatchException i)
                    {
                        Vista.mostrarUnMisatgeGeneric("Valor no valido");
                    }
                    break;
                case "0":
                    mantenerBucle = false;
                    break;
                default:
                    Vista.mostrarUnMisatgeGeneric("Te equivocaste mi loco");
                    break;
            }
        }
        Vista.mostrarUnMisatgeGeneric(eq.toString());
        Vista.mostrarUnMisatgeGeneric("Los datos modificados estan siendo subidos a la base de datos");
        return eq;
    }
}
