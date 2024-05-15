package model;
import java.util.Date;
public class Partidos {
    int partit_id, equip_id;
    char resultat;
    Date data_partit;
    String matx;

    //constructors
    public Partidos(int partit_id, int equip_id, char resultat, Date data_partit, String matx) {
        this.partit_id = partit_id;
        this.equip_id = equip_id;
        this.resultat = resultat;
        this.data_partit = data_partit;
        this.matx = matx;
    }
    //getters
    public int getPartit_id() {return partit_id;}
    public int getEquip_id() {return equip_id;}
    public char getResultat() {return resultat;}
    public Date getData_partit() {return data_partit;}
    public String getMatx() {return matx;}

    //setters
    public void setPartit_id(int partit_id) {this.partit_id = partit_id;}
    public void setEquip_id(int equip_id) {this.equip_id = equip_id;}
    public void setResultat(char resultat) {this.resultat = resultat;}
    public void setData_partit(Date data_partit) {this.data_partit = data_partit;}
    public void setMatx(String matx) {this.matx = matx;}
}
