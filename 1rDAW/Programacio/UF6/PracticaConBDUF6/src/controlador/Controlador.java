package controlador;
import model.*;
import vista.Vista;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

        Vista.mostrarUnMisatgeGeneric("Indique el nombre del jugador");
        nombre = scan.nextLine().trim();
        Vista.mostrarUnMisatgeGeneric("Ahora indique su equipo");
        equipo = scan.nextLine().trim();
        Model.crearJugadorEnEquipo(nombre,equipo,con);
    }

    private static void pregunta5()
    {
        String nom, equipo;

        Vista.mostrarUnMisatgeGeneric("Indique el nombre del jugador a mover");
        nom = scan.nextLine().trim();
        Vista.mostrarUnMisatgeGeneric("Ahora indique su nuevo equipo");
        equipo = scan.nextLine().trim();
        Model.moverJugador(nom,equipo,con);
    }

    private static void pregunta6()
    {
        String nom;
        int partit_id;
        File doc = new File("./1RDAW/Programacio/UF6/PracticaConBDUF6/Arxius/DadesPartit.csv");

        Vista.mostrarUnMisatgeGeneric("Indique el nombre del jugador");
        nom = scan.nextLine().trim();
        Vista.mostrarUnMisatgeGeneric("Ahora indique su partido_id");
        try {
            partit_id = scan.nextInt();
        }catch (InputMismatchException i) {partit_id = 0;}
        Vista.mostrarUnMisatgeGeneric("Indique el partido");
        Model.actualizarDadesPartit(nom, partit_id, doc, con);

    }

    private static void pregunta7()
    {
        String nom;
        int partit_id;

        Vista.mostrarUnMisatgeGeneric("Indique el nombre del jugador");
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

        Vista.mostrarUnMisatgeGeneric("Introduzca el nombre del jugador al qual quieres jubilar");
        nom = scan.nextLine().trim();
        Model.moverAHistoric(nom, con);
    }

    private static void pregunta9()
    {

    }
}
