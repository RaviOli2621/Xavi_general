import java.util.Comparator;
import java.util.regex.Pattern;

public abstract class Productes implements Comparator
{
    float preu;

    String nom;

    String codiBarres;

    Pattern floatPat = Pattern.compile("[0-9]++(.[0-9]++)?");
    Pattern intPat = Pattern.compile("[0-9]++");


    public Productes(String preu, String nom, String codiBarres) throws Exception
    {
        checkPreu(preu);
        checkCodiBarres(codiBarres);
        checkNom(nom);
    }
    private void checkNom(String p) throws Exception
    {
        if(!(p.length() <= 15 && !p.isEmpty())) throw new Exception("El nombre del producto tiene una longitud incorrecta");
        nom = p;
    }
    private void checkPreu(String p) throws Exception
    {
        if(!chechkData(p,floatPat)) throw new Exception("El formato del precio es erroneo");
        preu = Float.parseFloat(p);
    }
    private void checkCodiBarres(String p) throws Exception
    {
        if(!chechkData(p,intPat)) throw new Exception("El formato del codigo de barras es erroneo");
        codiBarres = p;
    }
    private boolean chechkData(String mirar,Pattern estructura) // funcion general para mirar si sigue un formato
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

    public void setCodiBarres(String codiBarres)throws Exception {
        checkCodiBarres(codiBarres);
    }

}
