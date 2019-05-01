/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author professor
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * classe que representa un departament
 * @author professor
 */

@XmlRootElement
@XmlType(name="hospital", propOrder = {"id", "nom", "director", "pressupost", "adreca", "especialitats"})
public class Hospital {

    private int id;
    private String nom;
    private String director;    
    private float pressupost;
    private String adreca;
    private final List<String> especialitats = new ArrayList<>();

    
    public Hospital() {
    }

    public Hospital(int codi, String nom, String director,  float pressupost, String adreca, String [] especialitats) {
        this.id = codi;
        this.nom = nom;
        this.director = director;
        this.pressupost=pressupost;
        this.adreca = adreca;
        this.especialitats.addAll(Arrays.asList(especialitats));
    }

    
    /**
     * Obte el valor de la propietat codi. El codi identifica al departament dins del sistema de persistencia
     *
     * @return el valor de la propietat id
     */
    @XmlAttribute
    public int getId() {
        return id;
    }

    /**
     * Actualitza el valor de la propietat codi. El codi identifica el departament dins del sistema de persistencia
     *
     * @param id nou valor de la propietat id
     */
    public void setId(int id) {
        this.id = id;
    }    
    
  
    /**
     * Obte el valor de la propietat nom
     *
     * @return el valor de la propietat nom
     */
    @XmlAttribute
    public String getNom() {
        return nom;
    }

    /**
     * Actualitza el valor de la propietat nom
     *
     * @param nom nou valor de la propietat nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

     /**
     * Obte el valor de la propietat telefon. El telefon del departament dins del sistema de persistencia
     *
     * @return el valor de la propietat director
     */
    @XmlElement
    public String getDirector() {
        return director;
    }

    /**
     * Actualitza el valor de la propietat telefon. El telefon del departament dins del sistema de persistencia
     *
     * @param director nou valor de la propietat director
     */
    public void setDirector(String director) {
        this.director = director;
    }        
    
    /**
     * Obté el valor de la propietat pressupost
     * @return el valor de la propietat pressupost
     */
    @XmlElement
    public float getPressupost() {
        return pressupost;
    }

    /**
     * Actualitza el valor de la propietat pressupost
     * @param pressupost  valor a assignar a la propietat pressupost
     */

    public void setPressupost(float pressupost) {
        this.pressupost = pressupost;
    }

    
    
    /**
     * Obte el valor de la propietat adreca
     *
     * @return el valor de la propietat adreca
     */
    @XmlElement(name="adreca")
    public String getAdreca() {
        return adreca;
    }

    /**
     * Actualitza el valor de la propietat adreca
     *
     * @param adreca nou valor de la propietat adreca
     */
    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }
    
    /**
     * Obte el valor de la propietat especialitats
     *
     * @return el valor de la propietat especialitats
     */
    @XmlElementWrapper(name="especialitats")
    @XmlElement(name="especialitat")     
    public List<String> getEspecialitats() {
        return especialitats;
    }

    /**
     * Actualitza el valor de la propietat especialitats
     *
     * @param especialitats nou valor de la propietat especialitats
     */
    public void setEspecialitats(List<String> especialitats) {
        if(this.especialitats!=especialitats){
            this.especialitats.clear();
            this.especialitats.addAll(especialitats);
        }
    
    }






}
