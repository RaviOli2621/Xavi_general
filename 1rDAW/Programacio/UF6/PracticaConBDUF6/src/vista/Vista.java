package vista;

import model.Estadisticas_jugadores;

import java.util.ArrayList;

public class Vista {
    public static void mostrarUnMisatgeGeneric(String string)
    {
        System.out.println(string);
    }
    public static void mostrarPreg1(ArrayList<String> missatge)
    {
        System.out.printf("%-10s %-40s\n","id","nombre");
        for (int i = 0; i < missatge.size(); i++)
        {
            System.out.printf("%-10s %-40s\n",missatge.get(i),missatge.get(i+1));
            i++;
        }
    }
    public static void mostrarMenu(){
        System.out.println("1- Listar tots els jugadors d'un equip");
        System.out.println("2- Calcular la mitjana de punts, rebots, assistencies, ... d'un jugador");
        System.out.println("3- Llistar tots els partits jugats per un equip amb el seu resultat");
        System.out.println("4- Inserir un nou jugador a un equip");
        System.out.println("5- Traspassar un judador a un altra equip");
        System.out.println("6- Actualitzar les dades de jugadors o equips després d'un partit");
        System.out.println("7- Modificar les estadístiques d’un jugador");
        System.out.println("8- Retirar (Eliminar) un jugador");
        System.out.println("9- Canviar nom franquícia d’un equip");
    }
    public static void editarJugadorDades()
    {
        System.out.println("Que característica quieres editar");
        System.out.println("\t1. Id del equipo");
        System.out.println("\t2. Puntos totales");
        System.out.println("\t3. Tiros metidos");
        System.out.println("\t4. Tiros tirados");
        System.out.println("\t5. Triples metidos");
        System.out.println("\t6. Triples tirados");
        System.out.println("\t7. Triros libres metidos");
        System.out.println("\t8. Triros libres tirados");
        System.out.println("\t9. Rebotes ofensivos");
        System.out.println("\t10. Rebotes defensivos");
        System.out.println("\t11. Assistencias");
        System.out.println("\t12. Robadas");
        System.out.println("\t13. Bloqueos");
        System.out.println("\t14. Minutos jugados");
        System.out.println("\t0. Nada, me equivoque de boton al seleccionar la funcion / no quiero editar mas (creo que no era necessario un texto tan largo para esta opción aunque el daño ya esta hecho. Bueno ya que estamo, saludos Xavi)");

    }
    public static void editarEquipDades()
    {
        System.out.println("Que característica quieres editar");
        System.out.println("\t1. Ciutat");
        System.out.println("\t2. Nom");
        System.out.println("\t3. Acronim");
        System.out.println("\t4. Divisio");
        System.out.println("\t5. Guanyades");
        System.out.println("\t6. Perdudes");
        System.out.println("\t0. Nada, me equivoque de boton al seleccionar la funcion / no quiero editar mas (creo que no era necessario un texto tan largo para esta opción aunque el daño ya esta hecho. Bueno ya que estamo, saludos Xavi)");

    }
    public static void mostrarEstadisticas_jugadores(Estadisticas_jugadores e){
        Vista.mostrarUnMisatgeGeneric(e.toString());

    }
}
