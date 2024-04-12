public class Productes
{
    float preu;

    String nom;

    String codiBarres; //el codi de barres és un String ja que no el tractarem matemàticament

    public Productes(float preu, String nom, String codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
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
