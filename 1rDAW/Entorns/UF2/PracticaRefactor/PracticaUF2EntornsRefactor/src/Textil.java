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
        this.codiBarres = "T-" + codiBarres;
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
        return nom + "//Textil//" + preu + "//" + codiBarres + "//" + compocisioTextil;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Textil t1 = (Textil) o1;
        Textil t2 = (Textil) o2;

        return t1.getCompocisioTextil().compareToIgnoreCase(t2.getCompocisioTextil());
    }
}
