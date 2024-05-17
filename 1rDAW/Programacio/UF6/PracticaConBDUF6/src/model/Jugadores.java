package model;
import java.util.Date;
public class Jugadores {
    int jugador_id, equip_id;
    Date data_naixement;
    float alcada, pes;
    String nom, cognom, dorsal, posicio;

    //constructors
    public Jugadores(int jugador_id){
        this.jugador_id = jugador_id;
        setPosicio(null);
        setAlcada(0);
        setCognom(null);
        setDorsal(null);
        setNom(null);
        setPes(0);
        setData_naixement(null);
        setEquip_id(-1);
    }
    public Jugadores(Jugadores j){
        this.jugador_id = j.jugador_id;
        setPosicio(j.posicio);
        setAlcada(j.alcada);
        setCognom(j.cognom);
        setDorsal(j.dorsal);
        setNom(j.nom);
        setPes(j.pes);
        setData_naixement(j.data_naixement);
        setEquip_id(j.equip_id);
    }
    public Jugadores(int jugador_id, String nom, String cognom, Date data_naixement, float alcada, float pes, String dorsal, String posicio, int equip_id){
        this.jugador_id = jugador_id;
        this.nom = nom;
        this.cognom = cognom;
        this.data_naixement = data_naixement;
        this.alcada = alcada;
        this.pes = pes;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.equip_id = equip_id;
    }

    //getters
    public int getJugador_id() {
        return jugador_id;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public java.sql.Date getData_naixement() {
        return (java.sql.Date) data_naixement;
    }

    public float getAlcada() {
        return alcada;
    }

    public float getPes() {
        return pes;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getDorsal() {
        return dorsal;
    }

    public String getPosicio() {
        return posicio;
    }

    //setters
    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public void setData_naixement(Date data_naixement) {
        this.data_naixement = data_naixement;
    }

    public void setAlcada(float alcada) {
        this.alcada = alcada;
    }

    public void setPes(float pes) {
        this.pes = pes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }
}
