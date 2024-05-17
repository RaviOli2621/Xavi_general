package controlador;
import model.Jugadores;
import model.MYSQLJugadoresDAO;
import model.Model;
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

    public static void prueba()
    {
        Jugadores prueba1 = new Jugadores(2544,null,null,null,0,0,null,null,0);

        MYSQLJugadoresDAO dao = new MYSQLJugadoresDAO(con);

        dao.read(prueba1,con);

        dao.create(new Jugadores(9999999,"mindundi","jonson",null,0,0,"10","Forward",1610612747),con);

        dao.update(new Jugadores(9999999,"mindundi2","jonson",null,0,0,"10","Forward",1610612747),con);

        dao.delete(new Jugadores(9999999,"mindundi","jonson",null,0,0,"10","Forward",1610612747),con);


        System.out.println(prueba1.getNom() + " " + prueba1.getEquip_id());
    }
}
