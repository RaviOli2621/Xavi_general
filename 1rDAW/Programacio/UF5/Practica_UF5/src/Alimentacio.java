import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Alimentacio extends Productes
{
    Date data_caducitat;

    Pattern dataPat = Pattern.compile("dd-MM-yyyy");
    SimpleDateFormat paraParsearLaData = new SimpleDateFormat(dataPat.pattern());


    float preu;

    String nom;

    String codiBarres;
    public Alimentacio(String preu, String nom, String codiBarres,String data_caducitat) throws Exception
    {
        super(preu,nom, codiBarres);
        checkDataCad(data_caducitat);
        this.nom = nom;
        this.preu = Float.parseFloat(preu);
        this.codiBarres = (codiBarres);
        this.preu = preuSegonsCaducitat(this.preu,this.data_caducitat);
    }

    private void checkDataCad(String p)throws Exception
    {
        data_caducitat = paraParsearLaData.parse(p);
    }
    public static float preuSegonsCaducitat(float preu, Date data_caducitat)
    {
        Date actual = new Date();
        int dataDif = (int)((data_caducitat.getTime() - actual.getTime())/(1000*60*60*24)+1);
        // el calcul es divideixo en parts: preu - (preu*(1/(dataDif))) + (preu * 0.1f)
        float calculPreu = ((float) 1 /(dataDif));
        calculPreu = calculPreu*preu;
        calculPreu = preu -calculPreu;
        calculPreu = calculPreu + (preu*0.1f);
        preu = ((int)(calculPreu*100)); // per poder tenir el numero amb 2 digits
        preu = preu/100;                // per poder tenir el numero amb 2 digits
       return preu;
    }

    public void setData_caducitat(Date data_caducitat) {
        this.data_caducitat = data_caducitat;
    }

    public void setDataPat(Pattern dataPat) {
        this.dataPat = dataPat;
    }

    public void setParaParsearLaData(SimpleDateFormat paraParsearLaData) {
        this.paraParsearLaData = paraParsearLaData;
    }

    @Override
    public float getPreu() {
        return preu;
    }

    @Override
    public void setPreu(float preu) {
        this.preu = preu;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getCodiBarres() {
        return codiBarres;
    }

    @Override
    public void setCodiBarres(String codiBarres) {
        this.codiBarres = (codiBarres);
    }

    public Date getData_caducitat() {
        return data_caducitat;
    }

    public Pattern getDataPat() {
        return dataPat;
    }

    public SimpleDateFormat getParaParsearLaData() {
        return paraParsearLaData;
    }

    @Override
    public String toString() {
        return "Alimentacio{" +
                "data_caducitat=" + data_caducitat +
                ", dataPat=" + dataPat +
                ", paraParsearLaData=" + paraParsearLaData +
                ", preu=" + preu +
                ", nom='" + nom + '\'' +
                ", codiBarres='" + codiBarres + '\'' +
                '}';
    }
}
