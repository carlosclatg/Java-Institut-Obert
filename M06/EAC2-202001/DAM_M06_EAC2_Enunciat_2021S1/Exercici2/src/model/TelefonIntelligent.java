/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Classe que representa una fitxa d'un telèfon d'un comerç de telefonia mòbil
 * @author professor
 */


@Entity
public class TelefonIntelligent implements Serializable {

    @Id
    private int ref;

    private String model;
    private float preu;
    private String so;
    private String processador;
    private int memoria;
    private boolean sd;
 
    /**
     * Constructor per defecte Constructors parametritzats
     */
    public TelefonIntelligent() {
        
    }

    /**
     * Constructor parametritzat
     */
    public TelefonIntelligent(int codi, String denominacio, float euros, String sistema, String micro, int gb, boolean targeta) {
        this.ref = codi;
        this.model = denominacio;
        this.preu = euros;
        this.so = sistema;
        this.processador = micro;
        this.memoria = gb;
        this.sd = targeta;
        
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public boolean isSd() {
        return sd;
    }

    public void setSd(boolean sd) {
        this.sd = sd;
    }

    @Override
    public String toString() {
        return "TelefonIntelligent{" +
                "ref=" + ref +
                ", model='" + model + '\'' +
                ", preu=" + preu +
                ", so='" + so + '\'' +
                ", processador='" + processador + '\'' +
                ", memoria=" + memoria +
                ", sd=" + sd +
                '}';
    }
}
