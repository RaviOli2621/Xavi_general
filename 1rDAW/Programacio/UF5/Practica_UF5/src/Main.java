import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Productes> carrito = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("BENVINGUT A SAPAMERCAT");
        inici();
    }
    private static void inici()
    {
        String resposta;
        System.out.printf("%12s\n","------------");
        System.out.printf("%12s\n","-- INICI ---");
        System.out.printf("%12s\n","------------");

        System.out.println("1) Introduir producte");
        System.out.println("2) Passar per caixa");
        System.out.println("3) Mostrar carret de compra");
        System.out.println("0) Acabar");

        resposta = scan.nextLine().trim();
        switch (resposta)
        {
            case "1":
                IntroduirProducte();
                break;
            case "2":
                PasarPerCaixa();
                break;
            case "3":
                MostrarCarret();
                break;
        }
    }

    public static void IntroduirProducte()
    {
        String resposta;
        System.out.printf("%15s\n","---------------");
        System.out.printf("%15s\n","-- PRODUCTE ---");
        System.out.printf("%15s\n","---------------");

        System.out.println("1) Alimentació");
        System.out.println("2) Tèxtil");
        System.out.println("3) Electrònica");
        System.out.println("0) Tornar");

        resposta = scan.nextLine().trim();

        switch (resposta)
        {
            case "1":
                Alimentacio();
                break;
            case "2":
                Textil();
                break;
            case "3":
                Electronica();
                break;
            case "0":
                inici();
                break;
        }
    }
    public static void Alimentacio()
    {
        boolean puedesSalirDelBucle = false;
        while (!puedesSalirDelBucle)
        {
            System.out.println("Afegir alimentació");
            String nom, preu, codiDeBarres, dataCaducitat;
            System.out.println("Nom producte:");
            nom = scan.nextLine().trim();
            System.out.println("Preu producte:");
            preu = scan.nextLine().trim();
            System.out.println("Codi de barres producte:");
            codiDeBarres = scan.nextLine().trim();
            System.out.println("Data caducitat producte:");
            dataCaducitat = scan.nextLine().trim();
            try {
                Alimentacio aliment = new Alimentacio(preu,nom,codiDeBarres,dataCaducitat);
                carrito.add(aliment);
                puedesSalirDelBucle = true;
            }catch (java.text.ParseException e)//exepció en el parser de la data
            {
                System.out.println("El format de la data es incorrecte");
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        IntroduirProducte();
    }
    public static void Textil()
    {
        boolean puedesSalirDelBucle = false;
        while (!puedesSalirDelBucle)
        {
            System.out.println("Afegir tèxtil");
            String nom, preu, codiDeBarres, composicio;
            System.out.println("Nom producte:");
            nom = scan.nextLine().trim();
            System.out.println("Preu producte:");
            preu = scan.nextLine().trim();
            System.out.println("Codi de barres producte:");
            codiDeBarres = scan.nextLine().trim();
            System.out.println("Composicio tèxtil producte:");
            composicio = scan.nextLine().trim();
            try {
                Textil textil = new Textil(preu,nom,codiDeBarres,composicio);
                carrito.add(textil);
                puedesSalirDelBucle = true;
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        IntroduirProducte();
    }
    public static void Electronica()
    {
        boolean puedesSalirDelBucle = false;
        while (!puedesSalirDelBucle)
        {
            System.out.println("Afegir electrònica");
            String nom, preu, codiDeBarres, diesDeGarantia;
            System.out.println("Nom producte:");
            nom = scan.nextLine().trim();
            System.out.println("Preu producte:");
            preu = scan.nextLine().trim();
            System.out.println("Codi de barres producte:");
            codiDeBarres = scan.nextLine().trim();
            System.out.println("Dies de garantia producte:");
            diesDeGarantia = scan.nextLine().trim();
            try {
                Electronica electronica = new Electronica(preu,nom,codiDeBarres,diesDeGarantia);
                carrito.add(electronica);
                puedesSalirDelBucle = true;
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        IntroduirProducte();
    }
    public static void PasarPerCaixa()
    {

    }
    public static void MostrarCarret()
    {

    }
    public static void cosasxd()
    {
        try {
            Electronica al = new Electronica("100.0","Manzana","123456432","21");
        }catch (java.text.ParseException e)//exepció en el parser de la data
        {
            System.out.println("El format de la data es incorrecte");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
