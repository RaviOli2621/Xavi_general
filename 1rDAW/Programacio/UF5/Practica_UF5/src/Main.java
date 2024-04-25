import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class Main
{
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Productes> carrito = new ArrayList<>();
    public static void main(String[] args) {
        crearDocs();
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
            case "100": //Hecho para testear que funcione el limite de 100
                try {
                    for (int i = 0; carrito.size() < 100; i++)
                    {
                        carrito.add(new Electronica("10.0", i+"",i+""+(i+1),"10"));
                    }
                }catch (Exception e){}
                inici();
                break;
            default:
                break;
        }
    }
    public static void crearDocs()
    {
        File rutaExept = new File(".\\logs");
        File rutaTextilPrice = new File(".\\updates");
        File fileExept = new File (".\\logs\\Exceptions.dat");
        File fileTextilPrice = new File (".\\updates\\UpdateTextilPrices.dat");

        if(rutaExept.mkdirs())
        {
            System.out.println("creado con exito");
        }
        if(rutaTextilPrice.mkdirs())
        {
            System.out.println("creado con exito");
        }
        try {
            fileExept.createNewFile();
            fileTextilPrice.createNewFile();
        }catch (Exception e)
        {
            System.out.println("Ha ocurrido un error en la creacion de losarchivos necessarios para ejecutar el programa");
        }
    }
    public static void EditarDocumentos(String archivoConRuta, String mensaje)
    {
        String archivoATexto = "";

        try {
            // Guardar los datos originales del documento
            Scanner reader = new Scanner(new File(archivoConRuta));

            while (reader.hasNextLine())
            {
                archivoATexto += reader.nextLine().trim() + "\n";
            }

            // Añadir el mensaje extra
            PrintStream writer = new PrintStream(new File (archivoConRuta));
            writer.print(mensaje + "\n" + archivoATexto);

            writer.close();
            reader.close();
        }catch (Exception e)
        {
            System.out.println("Error en el processo de edicion de los archivos de texto:\n\t" + e.getMessage());
        }
    }

    public static void IntroduirProducte()
    {
        if(carrito.size() < 100)
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
        }else
        {
            System.out.println("El carrito se encuentra lleno");
            inici();
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
                EditarDocumentos(".\\logs\\Exceptions.dat",new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()) + " :\t" + "El format de la data es incorrecte");
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                EditarDocumentos(".\\logs\\Exceptions.dat",new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()) + " :\t" + e.getMessage());
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
                if(noRepTextil(codiDeBarres))
                {
                    preu = preuTextil(codiDeBarres, preu);
                    Textil textil = new Textil(preu,nom,codiDeBarres,composicio);
                    carrito.add(textil);
                }
                puedesSalirDelBucle = true;
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                EditarDocumentos(".\\logs\\Exceptions.dat",new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()) + " :\t" + e.getMessage());
            }
        }
        IntroduirProducte();
    }
    public static String preuTextil(String codiBarres, String preu)
    {
        File file = new File (".\\updates\\UpdateTextilPrices.dat");
        ArrayList<String> archivoATexto = new ArrayList<>(); // archivoATexto.get(i).trim().split("//")[0] = codigo de barras, archivoATexto.get(i).trim().split("//")[1] = precio


        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine())
            {
                archivoATexto.add(reader.nextLine().trim());
            }

            // Añadir el mensaje extra
            if(archivoATexto.isEmpty())
            {
                EditarDocumentos(".\\updates\\UpdateTextilPrices.dat", codiBarres + "//" + preu);
                return preu;
            }else
            {
                for (int i = 0; i < archivoATexto.size(); i++) {
                    if((codiBarres).equals(archivoATexto.get(i).split("//")[0]) && Float.parseFloat(preu) != Float.parseFloat(archivoATexto.get(i).split("//")[1]))
                    {
                        System.out.println("El codigo de barras proporcionado ya contiene un precio designado, actualizando precio");
                        return archivoATexto.get(i).trim().split("//")[1];
                    }
                }
                //No hay coincidencias de codigos entre el documento y el introducido, actualizando documento
                EditarDocumentos(".\\updates\\UpdateTextilPrices.dat", codiBarres + "//" + preu);
                return preu;
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return preu;
        }
    }
    public static boolean noRepTextil(String codiBarres)
    {
        for (int i = 0; i < carrito.size(); i++) {
            if(carrito.get(i) instanceof Textil && carrito.get(i).getCodiBarres().equals(codiBarres))
            {
                System.out.println("Error: No pueden haber dos productos textiles con el mismo codigo de barras en el carrito");
                return false;
            }
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
                EditarDocumentos(".\\logs\\Exceptions.dat",new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()) + " :\t" + e.getMessage());
            }
        }
        IntroduirProducte();
    }
    public static void PasarPerCaixa()
    {
        OrdenarMostrarCarret();
        Pattern dataPat = Pattern.compile("dd-MM-yyyy");
        SimpleDateFormat paraParsearLaData = new SimpleDateFormat(dataPat.pattern());
        AtomicReference<Float> precioFinal = new AtomicReference<>(0f);

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
        tiquet.forEach((k,v) ->
        {
            System.out.printf("%-15s\t%-10s\t%-10s\t%-10s\t\n",v.split("//")[0],v.split("//")[1],k.split("//")[1],(Float.parseFloat(k.split("//")[1]) * Float.parseFloat(v.split("//")[1])));
            precioFinal.updateAndGet(v1 -> v1 + (Float.parseFloat(k.split("//")[1]) * Float.parseFloat(v.split("//")[1])));
        });
        System.out.println("Precio final: " + precioFinal);
        System.out.println("--------------------\n");
        carrito.clear();
        inici();
    }
    public static void OrdenarMostrarCarret()
    {
        System.out.println();

        carrito.sort((a,e) -> a.getClass().toString().split(" ")[1].compareToIgnoreCase(e.getClass().toString().split(" ")[1])); //ordenar todos los objetos por classe

        carrito.sort((a,e) ->
        {
            if(a.getClass().toString().split(" ")[1].equals("Textil") && e.getClass().toString().split(" ")[1].equals("Textil"))
            {
                Textil t1 = (Textil) a;
                Textil t2 = (Textil) e;

                return t1.getCompocisioTextil().compareToIgnoreCase(t2.getCompocisioTextil());
            }else return 0;
        });
    }
    public static void MostrarCarret()
    {
        OrdenarMostrarCarret();
        HashMap<String,String> tiquet = new HashMap<>(); // la clave guarda el codigo de barras y el precio, el segundo valor guarda el nombre del producto y la cantidad

        for (int i = 0; i < carrito.size(); i++) {
            String key = carrito.get(i).toString().split("//")[3];
            if(!tiquet.containsKey(key))
            {
                tiquet.put(key,carrito.get(i).toString().split("//")[0] + "//1");
            }else
            {
                tiquet.replace(key,tiquet.get(key).split("//")[0] + "//" + (Integer.parseInt(tiquet.get(key).split("//")[1]) + 1));
            }
        }
        System.out.println("Carret");
        tiquet.forEach((k,v) -> System.out.printf("%s\t%s\t%s\t\n",v.split("//")[0], " --> " ,v.split("//")[1]));
        carrito.clear();
        inici();
    }
}
