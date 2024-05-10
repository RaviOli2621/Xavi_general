package controlador;
import model.Model;
import vista.Vista;

import java.util.Scanner;

public class Controlador
{
    static Scanner scan = new Scanner(System.in);

    public static void comenzarPrograma()
    {
        String url = "jdbc:mysql://192.168.56.103:3306/NBAData";
        String username = "perepi";
        String paswrd = "pastanaga";
        Model.openCon(url,username,paswrd);
        consultes();
        Model.closeCon();
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
        }
    }

    private static void pregunta1()
    {
        String respuesta;
        Vista.mostrarUnMisatgeGeneric("Que equipo quieres ver los jugadores?");
        respuesta = scan.nextLine().trim();
        Model.llistarJugadorsSegunEquipo(respuesta);
    }
}
