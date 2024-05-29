package model;

public class Estadisticas_jugadores {
    int jugador_id, partit_id, equip_id;
    float tirs_anotats, tirs_tirats, tir_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tir_lliures_tirats,
            rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs, punts, minuts_jugats;

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
        setEquip_id(0);
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
        setEquip_id(e.equip_id);
    }
    public Estadisticas_jugadores(int jugador_id, int partit_id, float punts, float tirs_anotats, float tirs_tirats,
                                  float tir_triples_anotats, float tirs_triples_tirats, float tirs_lliures_anotats,
                                  float tir_lliures_tirats, float rebots_ofensius, float rebots_defensius, float assistencies,
                                  float robades, float bloqueigs, float minuts_jugats, int equip_id) {

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
             this.equip_id = equip_id;
         }

    //Getters
    public int getJugador_id() {return jugador_id;}
    public int getPartit_id() {return partit_id;}
    public float getPunts() {return punts;}
    public float getMinuts_jugats() {return minuts_jugats;}
    public float getTirs_anotats() {return tirs_anotats;}
    public float getTirs_tirats() {return tirs_tirats;}
    public float getTir_triples_anotats() {return tir_triples_anotats;}
    public float getTirs_triples_tirats() {return tirs_triples_tirats;}
    public float getTirs_lliures_anotats() {return tirs_lliures_anotats;}
    public float getTir_lliures_tirats() {return tir_lliures_tirats;}
    public float getRebots_ofensius() {return rebots_ofensius;}
    public float getRebots_defensius() {return rebots_defensius;}
    public float getAssistencies() {return assistencies;}
    public float getRobades() {return robades;}
    public float getBloqueigs() {return bloqueigs;}
    public int getEquip_id() {return equip_id;}

    //Setters
    public void setJugador_id(int jugador_id) {this.jugador_id = jugador_id;}
    public void setPartit_id(int equip_id) {this.partit_id = equip_id;}
    public void setPunts(float punts) {this.punts = punts;}
    public void setMinuts_jugats(float minuts_jugats) {this.minuts_jugats = minuts_jugats;}
    public void setTirs_anotats(float tirs_anotats) {this.tirs_anotats = tirs_anotats;}
    public void setTirs_tirats(float tirs_tirats) {this.tirs_tirats = tirs_tirats;}
    public void setTir_triples_anotats(float tir_triples_anotats) {this.tir_triples_anotats = tir_triples_anotats;}
    public void setTirs_triples_tirats(float tirs_triples_tirats) {this.tirs_triples_tirats = tirs_triples_tirats;}
    public void setTirs_lliures_anotats(float tirs_lliures_anotats) {this.tirs_lliures_anotats = tirs_lliures_anotats;}
    public void setTir_lliures_tirats(float tir_lliures_tirats) {this.tir_lliures_tirats = tir_lliures_tirats;}
    public void setRebots_ofensius(float rebots_ofensius) {this.rebots_ofensius = rebots_ofensius;}
    public void setRebots_defensius(float rebots_defensius) {this.rebots_defensius = rebots_defensius;}
    public void setAssistencies(float assistencies) {this.assistencies = assistencies;}
    public void setRobades(float robades) {this.robades = robades;}
    public void setBloqueigs(float bloqueigs) {this.bloqueigs = bloqueigs;}
    public void setEquip_id(int equip_id) {this.equip_id = equip_id;}

    @Override
    public String toString() {
        return "Estadisticas: \n" +
                "\tTirs_anotats: " + tirs_anotats + "\n" +
                "\tTirs_tirats: " + tirs_tirats + "\n" +
                "\tTir_triples_anotats: " + tir_triples_anotats + "\n" +
                "\tTirs_triples_tirats: " + tirs_triples_tirats + "\n" +
                "\tTirs_lliures_anotats: " + tirs_lliures_anotats + "\n" +
                "\tTir_lliures_tirats: " + tir_lliures_tirats + "\n" +
                "\tRebots_ofensius: " + rebots_ofensius + "\n" +
                "\tRebots_defensius: " + rebots_defensius + "\n" +
                "\tAssistencies: " + assistencies + "\n" +
                "\tRobades: " + robades + "\n" +
                "\tBloqueigs: " + bloqueigs + "\n" +
                "\tJugador_id: " + jugador_id + "\n" +
                "\tPartit_id: " + partit_id + "\n" +
                "\tPunts: " + punts + "\n" +
                "\tEquip_id: " + equip_id + "\n" +
                "\tMinuts_jugats: " + minuts_jugats + "\n";
    }
}
