package model;

public class Historico {
    int tirs_anotats, tirs_tirats, tir_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tir_lliures_tirats,
            rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs, jugador_id, ultim_Equip_id, punts_tot;
    float tot_min_jugats;

    //constructors
    public Historico(int jugador_id){
        this.jugador_id = jugador_id;
        setUltim_Equip_id(0);
        setPunts_tot(0);
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
        setTot_min_jugats(0);
    }
    public Historico(Historico h){
        this.jugador_id = h.jugador_id;
        setUltim_Equip_id(h.ultim_Equip_id);
        setPunts_tot(h.punts_tot);
        setTirs_anotats(h.tirs_anotats);
        setTirs_tirats(h.tirs_tirats);
        setTir_triples_anotats(h.tir_triples_anotats);
        setTirs_triples_tirats(h.tirs_triples_tirats);
        setTirs_lliures_anotats(h.tirs_lliures_anotats);
        setTir_lliures_tirats(h.tir_lliures_tirats);
        setRebots_ofensius(h.rebots_ofensius);
        setRebots_defensius(h.rebots_defensius);
        setAssistencies(h.assistencies);
        setRobades(h.robades);
        setBloqueigs(h.bloqueigs);
        setTot_min_jugats(h.tot_min_jugats);
    }
    public Historico(int jugador_id, int ultim_Equip_id, int punts_tot, int tirs_anotats, int tirs_tirats, int tir_triples_anotats, int tirs_triples_tirats, int tirs_lliures_anotats, int tir_lliures_tirats, int rebots_ofensius, int rebots_defensius, int assistencies, int robades, int bloqueigs, float tot_min_jugats) {
        this.jugador_id = jugador_id;
        this.ultim_Equip_id = ultim_Equip_id;
        this.punts_tot = punts_tot;
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
        this.tot_min_jugats = tot_min_jugats;
    }

    //Getters
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
    public int getJugador_id() {return jugador_id;}
    public int getUltim_Equip_id() {return ultim_Equip_id;}
    public int getPunts_tot() {return punts_tot;}
    public float getTot_min_jugats() {return tot_min_jugats;}

    //Setters
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
    public void setJugador_id(int jugador_id) {this.jugador_id = jugador_id;}
    public void setUltim_Equip_id(int ultim_Equip_id) {this.ultim_Equip_id = ultim_Equip_id;}
    public void setPunts_tot(int punts_tot) {this.punts_tot = punts_tot;}
    public void setTot_min_jugats(float tot_min_jugats) {this.tot_min_jugats = tot_min_jugats;}
}
