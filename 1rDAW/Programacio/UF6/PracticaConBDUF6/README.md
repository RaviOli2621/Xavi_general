Aquest programa tracta dades de una base de dades y permet fer consultes a treves d'un menu, utilitzem metodologia VMC
(Vista, Moel, Controlador) per gestionar el programa y metodologia CRUD (Create, Read, Upadate, Delete) per a gestionar
totes les consultes de la base de dades a traves de les classes DAO implementantles en una interficie DAO generica.

Vista: En aquesta clase tenim els metodes per Mostrar coses per pantalla per informar al usuari que esta succeint com
per exemple mostrarMenu(), un metode que et mostra les diferents opcions que pots fer.
~~~ java
public static void mostrarMenu(){
    System.out.println("1- Listar tots els jugadors d'un equip");
    System.out.println("2- Calcular la mitjana d'estadistiques");
    System.out.println("3- Llistar tots els partits jugats per un equip amb el seu resultat");
    System.out.println("4- Inserir un nou jugador a un equip");
    System.out.println("5- Traspassar un judador a un altra equip");
    System.out.println("6- Actualitzar les dades de jugadors o equips després d'un partit");
    System.out.println("7- Modificar les estadístiques d’un jugador");
    System.out.println("8- Retirar (Eliminar) un jugador");
    System.out.println("9- Canviar dades d'un equip");
    System.out.println("0- Salir");

}
~~~
Controlador: En aquesta clase tenim els metodes amb els cuals directament interactua amb l'usuari y la conexio amb la
base de dades com per exemple consultes(), un metode que permet escollir entre diferents opcions, guarda en variables
les dades introduides per l'usuari i truca als metodes adients a aquesta seleccio per fer el que calgui.
~~~  java
public class Controlador{
    static Scanner scan = new Scanner(System.in);
    private static Connection con;
    
    public static void consultes(){
        String respuesta;
        boolean salir = false;
    
        while (!salir)
        {
    
            Vista.mostrarMenu();
            respuesta = scan.next().trim();
            scan.nextLine();
    
            //menu de les preguntes
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
                    salir = true;
                    break;
                default:
                    Vista.mostrarUnMisatgeGeneric("Te equivocaste");
            }
        }
    }
}
~~~ 
Model: Claser principal on es tracten totes les dades del programa, ja sigui generades per si mateix, introduides
per l'usuari o extretes de la base de dades
Aqui podem trobar les diferents funcions que son requerides per els exersicis.
En l'exersici 6 la estructura dels documents es la seguent:
~~~ 
jugador_id;1                //obligatori en aquesta posició
partit;1                    //obligatori en aquesta posició
tirs_anotats;2.0            //opcional sense importar l'ordre
tirs_tirats;23.             //opcional sense importar l'ordre
tirs_triples_anotats;19.0   //opcional sense importar l'ordre
tirs_triples_tirats;9.0     //opcional sense importar l'ordre
tirs_lliures_anotats;4.0    //opcional sense importar l'ordre
tir_lliures_tirats;4.0      //opcional sense importar l'ordre
rebots_ofensius;19.0        //opcional sense importar l'ordre
rebots_defensius;2.0        //opcional sense importar l'ordre
assistencies;6.0            //opcional sense importar l'ordre
robades;1.0                 //opcional sense importar l'ordre
bloqueigs;13.0              //opcional sense importar l'ordre
punts;60.0                  //opcional sense importar l'ordre
minuts_jugats;19.00         //opcional sense importar l'ordre
equip_id;938532             //opcional sense importar l'ordre
~~~

DAOGenerica: Aquesta interficie serveix com a plantilla per a les DAO de jugador, equip, partir...
~~~ java
public interface DAOGenerica<T>
{
    // CRUD
    boolean create(T t);

    boolean read(T t);

    boolean update(T t);
    boolean delete(T t);
    // ALTRES
    boolean exists(T t);
    int count();
    List<T> all();
}
~~~
Objeto: Aquesta classe conte les propietats, getters, setters, toString i constructors de cada objecte.
Els constructors es troben preparats per a diverses situacions (només id, amb un altre objecte igual o amb totes les dades). Exemple constructors:
~~~ java
int equip_id, guanyades, perdudes;
String nom, ciutat, acronim, divisio;

public Equipos(int equip_id){
    this.equip_id = equip_id;
    setGuanyades(0);
    setPerdudes(0);
    setNom("");
    setCiutat("");
    setAcronim("");
    setDivisio("");
}
~~~
~~~ java
public Equipos(Equipos e){
    this.equip_id = e.equip_id;
    setGuanyades(e.guanyades);
    setPerdudes(e.perdudes);
    setNom(e.nom);
    setCiutat(e.ciutat);
    setAcronim(e.acronim);
    setDivisio(e.divisio);
}
~~~
~~~ java
public Equipos(int equip_id, int guanyades, int perdudes, String nom, String ciutat, String acronim, String divisio) {
    this.equip_id = equip_id;
    this.guanyades = guanyades;
    this.perdudes = perdudes;
    this.nom = nom;
    this.ciutat = ciutat;
    this.acronim = acronim;
    this.divisio = divisio;
}
~~~
ObjetoDAO: Aquesta classe conte els metodes CRUD implementats per l'interficie DAOGenerica, com per exemple update(),
en aquest metode li pasem per parametre un Objecte, utilitzant el prepareStatment fem una consulta amb la conexio de
Controlador amb la sentencia sql adient y fem els sets corresponents per a cada parametre de l'objecte.
La funcio read() esta adaptada per a 2 tipus de situacions, cuan volem extreure una dada concreta o cuan volem extreure
totes les dades de la taula per guarar-les en un arraylist d'objectes.
~~~ java
public boolean update(Estadisticas_jugadores e) {
    PreparedStatement sta;
    try {
        sta = con.prepareStatement("UPDATE estadistiques_jugadors SET punts =?,tirs_anotats =?," +
                "tirs_tirats =?,tirs_triples_anotats =?,tirs_triples_tirats =?,tirs_lliures_anotats =?,tirs_lliures_tirats =?" +
                ",rebots_ofensius =?,rebots_defensius =?,assistencies =?,robades =?,bloqueigs =?,minuts_jugats =?,equip_id =?" +
                " WHERE jugador_id =? AND partit_id =?");
        sta.setFloat(1,e.getPunts());
        sta.setFloat(2,e.getTirs_anotats());
        sta.setFloat(3,e.getTirs_tirats());
        sta.setFloat(4,e.getTir_triples_anotats());
        sta.setFloat(5,e.getTirs_triples_tirats());
        sta.setFloat(6,e.getTirs_lliures_anotats());
        sta.setFloat(7,e.getTir_lliures_tirats());
        sta.setFloat(8,e.getRebots_ofensius());
        sta.setFloat(9,e.getRebots_defensius());
        sta.setFloat(10,e.getAssistencies());
        sta.setFloat(11,e.getRobades());
        sta.setFloat(12,e.getBloqueigs());
        sta.setFloat(13,e.getMinuts_jugats());
        sta.setInt(14,e.getEquip_id());
        sta.setInt(15, e.getJugador_id());
        sta.setInt(16,e.getPartit_id());
        sta.executeUpdate();
        return true;
    }catch (SQLException s)
    {
        Vista.mostrarUnMisatgeGeneric("Error al fer update " +s.getMessage() );
    }
    return false;
}
~~~
Main: Classe principal que executa l'inici del programa trucant a comenzarPrograma(), funcio de la clase Controlador.
~~~ java
public class Main {
    public static void main(String[] args) {
       Controlador.comenzarPrograma();
    }
}   
~~~