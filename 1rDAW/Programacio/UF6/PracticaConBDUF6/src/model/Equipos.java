package model;

public class Equipos {
    int equip_id, guanyades, perdudes;
    String nom, ciutat, acronim, divisio;

    public Equipos(int equip_id){
        this.equip_id = equip_id;
        setGuanyades(0);
        setPerdudes(0);
        setNom("");
        setCiutat("");
        setAcronim("");
        setDivisio("");
    }
    public Equipos(Equipos e){
        this.equip_id = e.equip_id;
        setGuanyades(e.guanyades);
        setPerdudes(e.perdudes);
        setNom(e.nom);
        setCiutat(e.ciutat);
        setAcronim(e.acronim);
        setDivisio(e.divisio);
    }
    public Equipos(int equip_id, int guanyades, int perdudes, String nom, String ciutat, String acronim, String divisio) {
        this.equip_id = equip_id;
        this.guanyades = guanyades;
        this.perdudes = perdudes;
        this.nom = nom;
        this.ciutat = ciutat;
        this.acronim = acronim;
        this.divisio = divisio;
    }

    //getters
    public int getEquip_id() {return equip_id;}
    public int getGuanyades() {return guanyades;}
    public int getPerdudes() {return perdudes;}
    public String getNom() {return nom;}
    public String getCiutat() {return ciutat;}
    public String getAcronim() {return acronim;}
    public String getDivisio() {return divisio;}

    //setters
    public void setEquip_id(int equip_id) {this.equip_id = equip_id;}
    public void setGuanyades(int guanyades) {this.guanyades = guanyades;}
    public void setPerdudes(int perdudes) {this.perdudes = perdudes;}
    public void setNom(String nom) {this.nom = nom;}
    public void setCiutat(String ciutat) {this.ciutat = ciutat;}
    public void setAcronim(String acronim) {this.acronim = acronim;}
    public void setDivisio(String divisio) {this.divisio = divisio;}

    @Override
    public String toString() {
        return "Equipo: " + "\n" +
                "\tEquip_id: " + equip_id + "\n" +
                "\tGuanyades: " + guanyades + "\n" +
                "\tPerdudes: " + perdudes + "\n" +
                "\tNom: " + nom + "\n" +
                "\tCiutat: " + ciutat + "\n" +
                "\tAcronim: " + acronim + "\n" +
                "\tDivisio: " + divisio + "\n";
    }
}
