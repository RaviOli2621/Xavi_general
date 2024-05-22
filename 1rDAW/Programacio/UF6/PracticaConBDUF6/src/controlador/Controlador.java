package controlador;
import model.*;
import vista.Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        Vista.mostrarUnMisatgeGeneric("Que pregunta quieres");

        respuesta = scan.next().trim();
        scan.nextLine();
        switch (respuesta)
        {
            case "1":
                pregunta1();
                break;
            case "2":
                //pregunta2();
                break;
            case "3":
                //pregunta3();
                break;
            case "4":
                pregunta4();
                break;
            case "5":
                pregunta5();
                break;
            case "6":
                //pregunta6();
                break;
            case "7":
                //pregunta7();
                break;
            case "8":
                //pregunta8();
                break;
            case "9":
                //pregunta9();
                break;
            default:
                prueba();
        }
    }

    private static void pregunta1()
    {
        String respuesta;
        Vista.mostrarUnMisatgeGeneric("Que equipo quieres ver los jugadores?");
        respuesta = scan.nextLine().trim();
        Model.llistarJugadorsSegunEquipo(respuesta,con);
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
        int id;
        String equipo;
        Vista.mostrarUnMisatgeGeneric("Indique el id del jugador a mover");
        id = scan.nextInt();
        Vista.mostrarUnMisatgeGeneric("Ahora indique su nuevo equipo");
        equipo = scan.nextLine().trim();
        Model.moverJugador(id,equipo,con);
    }

    private static void pregunta6()
    {

    }

    private static void pregunta7()
    {

    }

    private static void pregunta8()
    {

    }

    private static void pregunta9()
    {

    }
    public static void prueba()
    {
        Jugadores prueba1 = new Jugadores(2544);

        MYSQLJugadoresDAO dao = new MYSQLJugadoresDAO(con);

        dao.create(prueba1);


    }
}
