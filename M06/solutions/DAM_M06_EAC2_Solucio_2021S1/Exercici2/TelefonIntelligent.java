/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Classe que representa una fitxa d'un telèfon d'un comerç de telefonia mòbil
 * @author professor
 */
@Entity
public class TelefonIntelligent {

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

    /**
     * Obte el valor de la propietat ref. La variable ref identifica el dispositiu dins del sistema de persistencia
     *
     * @return el valor de la propietat ref
     */
    @Id
    public int getRef() {
        return ref;
    }

    /**
     * Actualitza el valor de la propietat ref. La variable ref identifica el dispositiu dins del sistema de persistencia
     *
     * @param id nou valor de la propietat codi
     */
    public void setRef(int id) {
        this.ref = id;
    }    
    
    /**
     * Obte el valor de la propietat model del telèfon intel·ligent
     *
     * @return el valor de la propietat model
     */
    public String getModel() {
        return model;
    }

    /**
     * Actualitza el valor de la propietat model del telèfon intel·ligent
     *
     * @param nom nou valor de la propietat model
     */
    public void setModel(String nom) {
        this.model = nom;
    }

    /**
     * Obte el valor de la propietat sistema operatiu del telèfon intel·ligent
     *
     * @return el valor de la propietat so
     */
    public String getSo() {
        return so;
    }

    /**
     * Actualitza el valor de la propietat sistema operatiu del telèfon intel·ligent
     *
     * @param sistema nou valor de la propietat so
     */
    public void setSo(String sistema) {
        this.so = sistema;
    }
    
    
    /**
     * Obte el valor de la propietat preu del telèfon intel·ligent
     *
     * @return el valor de la propietat preu
     */
    public float getPreu() {
        return preu;
    }

    /**
     * Actualitza el valor de la propietat preu del telèfon intel·ligent
     *
     * @param euros nou valor de la propietat preu
     */
    public void setPreu(float euros) {
        this.preu = euros;
    }
 /**
     * Obte el valor de la propietat processador del telèfon intel·ligent
     *
     * @return el valor de la propietat processador
     */
    public String getProcessador() {
        return processador;
    }

    /**
     * Actualitza el valor de la propietat processador del telèfon intel·ligent
     *
     * @param sistema nou valor de la propietat processador
     */
    public void setProcessador(String sistema) {
        this.processador = sistema;
    }
    
     /**
     * Obté el valor de la propietat memoria en gigues del telèfon
     *
     * @return el valor de la propietat memoria
     */
    public int getMemoria() {
        return memoria;
    }

    /**
     * Actualitza el valor de la propietat memoria en gigues del telèfon
     *
     * @param gigues nou valor de la propietat memoria
     */
    public void setMemoria(int gigues) {
        this.memoria = gigues;
    }
    
    /**
     * Obte el valor de la propietat SD (Secure Digital) del telèfon
     *
     * @return el valor de la propietat SD (Secure Digital) del telèfon
     */
    public boolean isSd() {
        return sd;
    }

    /**
     * Actualitza el valor de la propietat SD (Secure Digital) del telèfon
     *
     * @param targeta nou valor de la propietat SD (Secure Digital) del telèfon
     */
    public void setSd(boolean targeta) {
        this.sd = targeta;
    }

}
