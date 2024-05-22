package model;
import java.util.Date;
public class Partidos {
    int partit_id, equip_id;
    String resultat;
    Date data_partit;
    String matx;

    //constructors
    public Partidos(int partit_id){
        this.partit_id = partit_id;
        setEquip_id(-1);
        setResultat(null);
        setData_partit(null);
        setMatx(null);
    }
    public Partidos(Partidos p){
        this.partit_id = p.partit_id;
        setEquip_id(p.equip_id);
        setResultat(p.resultat);
        setData_partit(p.data_partit);
        setMatx(p.matx);
    }
    public Partidos(int partit_id, int equip_id, String resultat, Date data_partit, String matx) {
        this.partit_id = partit_id;
        this.equip_id = equip_id;
        this.resultat = resultat;
        this.data_partit = data_partit;
        this.matx = matx;
    }
    //getters
    public int getPartit_id() {return partit_id;}
    public int getEquip_id() {return equip_id;}
    public String getResultat() {return resultat;}
    public java.sql.Date getData_partit() {return (java.sql.Date) data_partit;}
    public String getMatx() {return matx;}

    //setters
    public void setPartit_id(int partit_id) {this.partit_id = partit_id;}
    public void setEquip_id(int equip_id) {this.equip_id = equip_id;}
    public void setResultat(String resultat) {this.resultat = resultat;}
    public void setData_partit(Date data_partit) {this.data_partit = (Date)data_partit;}
    public void setMatx(String matx) {this.matx = matx;}
}
