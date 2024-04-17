import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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
            default:
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
            default:
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
            System.out.println("Data caducitat producte (dd-MM-yyyy):");
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
                if(noRepTextil(textil.codiBarres)) carrito.add(textil);
                puedesSalirDelBucle = true;
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        IntroduirProducte();
    }
    public static boolean noRepTextil(String codiBarres)
    {
        for (int i = 0; i < carrito.size(); i++) {
            if(carrito.get(i) instanceof Textil && carrito.get(i).getCodiBarres().equals(codiBarres)) return false;
        }
        return true;
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
        Pattern dataPat = Pattern.compile("dd-MM-yyyy");
        SimpleDateFormat paraParsearLaData = new SimpleDateFormat(dataPat.pattern());

        HashMap<String,String> tiquet = new HashMap<>(); // la clave guarda el codigo de barras y el precio, el segundo valor guarda el nombre del producto y la cantidad

        for (int i = 0; i < carrito.size(); i++) {
            String key = carrito.get(i).toString().split("//")[3] + "//" + carrito.get(i).toString().split("//")[2];
            if(!tiquet.containsKey(key))
            {
                tiquet.put(key,carrito.get(i).toString().split("//")[0] + "//1");
            }else
            {
                tiquet.replace(key,tiquet.get(key).split("//")[0] + "//" + (Integer.parseInt(tiquet.get(key).split("//")[1]) + 1));
            }
        }
        System.out.println("--------------------\nSAPAMERCAT\n--------------------\nData: " + paraParsearLaData.format(new Date()) + "\n--------------------");
        tiquet.forEach((k,v) -> System.out.printf("%-15s\t%-10s\t%-10s\t%-10s\t\n",v.split("//")[0],v.split("//")[1],k.split("//")[1],(Float.parseFloat(k.split("//")[1]) * Float.parseFloat(v.split("//")[1]))));
        System.out.println("--------------------\n");
        carrito.clear();
        inici();
    }
    public static void MostrarCarret()
    {

    }
}
