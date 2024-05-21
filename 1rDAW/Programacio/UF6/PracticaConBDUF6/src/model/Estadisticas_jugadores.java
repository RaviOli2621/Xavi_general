package model;

public class Estadisticas_jugadores {
    int tirs_anotats, tirs_tirats, tir_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tir_lliures_tirats,
            rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs, jugador_id, partit_id, punts;
    float minuts_jugats;

    //constructors
    public Estadisticas_jugadores(int jugador_id){
        this.jugador_id = jugador_id;
        setPartit_id(0);
        setPunts(0);
        setTirs_anotats(0);
        setTirs_tirats(0);
        setTir_triples_anotats(0);
        setTirs_triples_tirats(0);
        setTirs_lliures_anotats(0);
        setTir_lliures_tirats(0);
        setRebots_ofensius(0);
        setRebots_defensius(0);
        setAssistencies(0);
        setRobades(0);
        setBloqueigs(0);
        setMinuts_jugats(0);

    }
    public Estadisticas_jugadores(Estadisticas_jugadores e){
        this.jugador_id = e.jugador_id;
        setPartit_id(e.partit_id);
        setPunts(e.punts);
        setTirs_anotats(e.tirs_anotats);
        setTirs_tirats(e.tirs_tirats);
        setTir_triples_anotats(e.tir_triples_anotats);
        setTirs_triples_tirats(e.tirs_triples_tirats);
        setTirs_lliures_anotats(e.tirs_lliures_anotats);
        setTir_lliures_tirats(e.tir_lliures_tirats);
        setRebots_ofensius(e.rebots_ofensius);
        setRebots_defensius(e.rebots_defensius);
        setAssistencies(e.assistencies);
        setRobades(e.robades);
        setBloqueigs(e.bloqueigs);
        setMinuts_jugats(e.minuts_jugats);
    }
    public Estadisticas_jugadores(int jugador_id, int partit_id, int punts, int tirs_anotats, int tirs_tirats, int tir_triples_anotats, int tirs_triples_tirats, int tirs_lliures_anotats, int tir_lliures_tirats, int rebots_ofensius, int rebots_defensius, int assistencies, int robades, int bloqueigs, float minuts_jugats) {
        this.jugador_id = jugador_id;
        this.partit_id = partit_id;
        this.punts = punts;
        this.tirs_anotats = tirs_anotats;
        this.tirs_tirats = tirs_tirats;
        this.tir_triples_anotats = tir_triples_anotats;
        this.tirs_triples_tirats = tirs_triples_tirats;
        this.tirs_lliures_anotats = tirs_lliures_anotats;
        this.tir_lliures_tirats = tir_lliures_tirats;
        this.rebots_ofensius = rebots_ofensius;
        this.rebots_defensius = rebots_defensius;
        this.assistencies = assistencies;
        this.robades = robades;
        this.bloqueigs = bloqueigs;
        this.minuts_jugats = minuts_jugats;
    }

    //getters
    public int getJugador_id() {return jugador_id;}
    public int getPartit_id() {return partit_id;}
    public int getPunts() {return punts;}
    public float getMinuts_jugats() {return minuts_jugats;}
    public int getTirs_anotats() {return tirs_anotats;}
    public int getTirs_tirats() {return tirs_tirats;}
    public int getTir_triples_anotats() {return tir_triples_anotats;}
    public int getTirs_triples_tirats() {return tirs_triples_tirats;}
    public int getTirs_lliures_anotats() {return tirs_lliures_anotats;}
    public int getTir_lliures_tirats() {return tir_lliures_tirats;}
    public int getRebots_ofensius() {return rebots_ofensius;}
    public int getRebots_defensius() {return rebots_defensius;}
    public int getAssistencies() {return assistencies;}
    public int getRobades() {return robades;}
    public int getBloqueigs() {return bloqueigs;}

    //setters

    public void setJugador_id(int jugador_id) {this.jugador_id = jugador_id;}
    public void setPartit_id(int equip_id) {this.partit_id = equip_id;}
    public void setPunts(int punts) {this.punts = punts;}
    public void setMinuts_jugats(float minuts_jugats) {this.minuts_jugats = minuts_jugats;}
    public void setTirs_anotats(int tirs_anotats) {this.tirs_anotats = tirs_anotats;}
    public void setTirs_tirats(int tirs_tirats) {this.tirs_tirats = tirs_tirats;}
    public void setTir_triples_anotats(int tir_triples_anotats) {this.tir_triples_anotats = tir_triples_anotats;}
    public void setTirs_triples_tirats(int tirs_triples_tirats) {this.tirs_triples_tirats = tirs_triples_tirats;}
    public void setTirs_lliures_anotats(int tirs_lliures_anotats) {this.tirs_lliures_anotats = tirs_lliures_anotats;}
    public void setTir_lliures_tirats(int tir_lliures_tirats) {this.tir_lliures_tirats = tir_lliures_tirats;}
    public void setRebots_ofensius(int rebots_ofensius) {this.rebots_ofensius = rebots_ofensius;}
    public void setRebots_defensius(int rebots_defensius) {this.rebots_defensius = rebots_defensius;}
    public void setAssistencies(int assistencies) {this.assistencies = assistencies;}
    public void setRobades(int robades) {this.robades = robades;}
    public void setBloqueigs(int bloqueigs) {this.bloqueigs = bloqueigs;}
}
