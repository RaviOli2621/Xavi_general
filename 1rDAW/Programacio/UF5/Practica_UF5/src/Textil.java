public class Textil extends Productes
{
    String compocisioTextil;
    float preu;
    String nom;
    String codiBarres;
    public Textil(String preu, String nom, String codiBarres, String compocisioTextil) throws Exception {
        super(preu, nom, codiBarres);
        this.preu = Float.parseFloat(preu);
        this.nom = nom;
        this.codiBarres = (codiBarres);
        this.compocisioTextil = compocisioTextil;
    }

    public void setCompocisioTextil(String compocisioTextil) {
        this.compocisioTextil = compocisioTextil;
    }

    public void setPreu(Float preu) {
        this.preu = preu;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void setCodiBarres(String codiBarres) {
        this.codiBarres = (codiBarres);
    }

    public String getCompocisioTextil() {
        return compocisioTextil;
    }

    @Override
    public float getPreu() {
        return preu;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getCodiBarres() {
        return codiBarres;
    }

    @Override
    public String toString() {
        return "Textil{" +
                "compocisioTextil='" + compocisioTextil + '\'' +
                ", preu=" + preu +
                ", nom='" + nom + '\'' +
                ", codiBarres='" + codiBarres + '\'' +
                '}';
    }
}
