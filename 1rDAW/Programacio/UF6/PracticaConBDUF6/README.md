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
~~~ java
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
~~~
Model:
~~~java

~~~
DAOGenerica:
~~~java

~~~
Objeto:
~~~java

~~~
ObjetoDAO:
~~~java

~~~
Main:
~~~java

~~~