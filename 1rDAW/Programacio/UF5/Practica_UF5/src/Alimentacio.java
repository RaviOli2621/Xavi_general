import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Alimentacio extends Productes
{
    Date data_caducitat;

    Pattern dataPat = Pattern.compile("dd-MM-yyyy");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataPat.pattern());


    float preu;

    String nom;

    String codiBarres;
    public Alimentacio(String preu, String nom, String codiBarres,String data_caducitat) throws Exception
    {
        super(preu,nom, codiBarres);
        checkDataCad(data_caducitat);
        this.nom = nom;
        this.preu = Float.parseFloat(preu);
        this.codiBarres = codiBarres;
        this.preu = preuChangeBasenCaducitat(this.preu,this.data_caducitat,simpleDateFormat);
    }

    private void checkDataCad(String p)throws Exception
    {
        data_caducitat = simpleDateFormat.parse(p);
    }
    public static float preuChangeBasenCaducitat(float preu, Date data_caducitat, SimpleDateFormat simpleDateFormat)throws  Exception
    {
        Date actual = new Date();
        int dataDif = (int)((data_caducitat.getTime() - actual.getTime())/(1000*60*60*24)+1);
        System.out.println(data_caducitat);

       preu = (float) (preu - preu*(1/(dataDif)) + (preu * 0.1));
       return preu;
    }

    public Date getData_caducitat() {
        return data_caducitat;
    }

    public Pattern getDataPat() {
        return dataPat;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }
}
