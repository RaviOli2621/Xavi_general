import java.util.regex.Pattern;

public class Electronica extends Productes
{
    int diesDeGarantia;
    Pattern intPat = Pattern.compile("[0-9]++");

    float preu;

    String nom;

    String codiBarres;

    public Electronica(String preu, String nom, String codiBarres, String diesDeGarantia) throws Exception {
        super(preu, nom, codiBarres);
        checkDiesGarant(diesDeGarantia);
        this.nom = nom;
        this.preu = Float.parseFloat(preu);
        this.codiBarres = (codiBarres);
        this.preu = preuSegonsGarantia(this.preu,this.diesDeGarantia);
    }

    public static float preuSegonsGarantia(float preu, int diesDeGarantia)
    {
        // calcul original = preu + preu*(diesGarantia/365)*0.1
        float calcul = (float) diesDeGarantia /365;
        calcul = calcul * preu * 0.1f;
        calcul = preu + calcul;
        // per poder ajustar els decimals
        preu = (int)(calcul*100);
        preu = (float)(preu/100);
        return preu;
    }
    private void checkDiesGarant(String p) throws Exception
    {
        if(!chechkData(p,intPat)) throw new Exception("El formato de los dias de garantia es erroneo");
        diesDeGarantia = Integer.parseInt(p);
    }
    private boolean chechkData(String mirar,Pattern estructura)
    {
        if(estructura.matcher(mirar).matches())
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Electronica{" +
                "diesDeGarantia=" + diesDeGarantia +
                ", intPat=" + intPat +
                ", preu=" + preu +
                ", nom='" + nom + '\'' +
                ", codiBarres='" + codiBarres + '\'' +
                '}';
    }
}
