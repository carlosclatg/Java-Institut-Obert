

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * classe que representa un hospital    
 * @author professor
 */
public class Hospital {

    private int id;
    private String director;
    private String nom;
    private float pressupost;
    private String adreça;
    private final List<String> especialitats = new ArrayList<>();

    
    public Hospital() {
    }

    public Hospital(int id, String director, String nom, float pressupost, String adreça, String [] especialMedica) {
        this.id = id;
        this.director = director;
        this.nom = nom;
        this.pressupost = pressupost;
        this.adreça = adreça;
        this.especialitats.addAll(Arrays.asList(especialMedica));
    }

    
    /**
     * Obte el valor de la propietat id. El id identifica un hospital dins del sistema de persistencia
     *
     * @return el valor de la propietat id
     */
    public int getId() {
        return id;
    }

    /**
     * Actualitza el valor de la propietat id. El id identifica al un hospital dins del sistema de persistencia
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
     * Obte el valor de la propietat director
     *
     * @return el valor de la propietat director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Actualitza el valor de la propietat director
     *
     * @param nom nou valor de la propietat director
     */
    public void setDirector(String nom) {
        this.director = nom;
    }
    
    /**
     * Obte el valor de la propietat pressupost
     *
     * @return el valor de la propietat pressupost
     */
    public float getpressupost() {
        return pressupost;
    }

    /**
     * Actualitza el valor de la propietat pressupost
     *
     * @param qunatitat nou valor de la propietat pressupost
     */
    public void setpressupost(float qunatitat) {
        this.pressupost = qunatitat;
    }

     /**
     * Obte el valor de la propietat adreça
     *
     * @return el valor de la propietat adreça
     */
    public String getAdreça() {
        return adreça;
    }

    /**
     * Actualitza el valor de la propietat adreça
     *
     * @param adreça nou valor de la propietat adreça
     */
    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    /**
     * Obte el valor de la propietat especialitats
     *
     * @return el valor de la propietat especialitats
     */
    public List<String> getEspecialitats() {
        return especialitats;
    }

    /**
     * Actualitza el valor de la propietat especialitats
     *
     * @param especial nou valor de la propietat especialitats
     */
    public void setEspecialitats(List<String> especial) {
        this.especialitats.clear();
        this.especialitats.addAll(especial);
    
    }






}
