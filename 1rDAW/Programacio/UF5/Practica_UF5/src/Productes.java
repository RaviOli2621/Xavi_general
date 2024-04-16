import java.util.regex.Pattern;

public class Productes
{
    float preu;

    String nom;

    String codiBarres;

    Pattern floatPat = Pattern.compile("[0-9]++.[0-9]++");

    public Productes(String preu, String nom, String codiBarres) throws Exception
    {
        checkPreu(preu);
        this.nom = nom;
        this.codiBarres = codiBarres;
    }
    private void checkPreu(String p) throws Exception
    {
        if(!chechkData(p,floatPat)) throw new Exception("El formato del precio es erroneo");
        preu = Float.parseFloat(p);
    }
    private boolean chechkData(String mirar,Pattern estructura)
    {
        if(estructura.matcher(mirar).matches())
        {
            return true;
        }
        return false;
    }

    public float getPreu() {
        return preu;
    }

    public String getCodiBarres() {
        return codiBarres;
    }

    public String getNom() {
        return nom;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodiBarres(String codiBarres) {
        this.codiBarres = codiBarres;
    }
}
