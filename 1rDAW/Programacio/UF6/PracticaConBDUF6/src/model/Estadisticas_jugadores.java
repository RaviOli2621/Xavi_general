package model;

public class Estadisticas_jugadores {
    int tirs_anotats, tirs_tirats, tir_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tir_lliures_tirats,
            rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs;

    //constructors
    public Estadisticas_jugadores(int tirs_anotats, int tirs_tirats, int tir_triples_anotats, int tirs_triples_tirats, int tirs_lliures_anotats, int tir_lliures_tirats, int rebots_ofensius, int rebots_defensius, int assistencies, int robades, int bloqueigs) {
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
    }

    //getters
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
