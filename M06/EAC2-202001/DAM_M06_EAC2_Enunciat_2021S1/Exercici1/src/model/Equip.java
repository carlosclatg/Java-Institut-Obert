

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Equip representa un equip de competició de MotoGP temporada 2020
 * @author alumne
 */
public class Equip {

    private int id;
    private String nom;
    private String seu;
    private int fundada;
    private String matricula;
    private String nomPilot;
    private int kg;
    private String llocNaixement;
    private final List<String> podis = new ArrayList<>();

    
    public Equip() {
        
    }

    
    public Equip(int id, String nom, String seu, int fundada, String alies, String nomPilot, int kg, String ciutat, String [] llistatPodis) {
        this.id = id;
        this.nom = nom;
        this.seu = seu;
        this.fundada = fundada;
        this.matricula = alies;
        this.nomPilot = nomPilot;
        this.kg = kg;
        this.llocNaixement = ciutat;
        this.podis.addAll(Arrays.asList(llistatPodis));
    }
    
    /**
     * Obte el valor de la propietat id. Aquest identifica una classe tipus equip dins del sistema de persistencia
     *
     * @return el valor de la propietat id
     */
    public int getId() {
        return id;
    }

    /**
     * Actualitza el valor de la propietat id. Aquest identifica al una classe tipus equip dins del sistema de persistencia
     *
     * @param ref nou valor de la propietat id
     */
    public void setId(int ref) {
        this.id = ref;
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
     * Obte el valor de la propietat seu
     *
     * @return el valor de la propietat seu
     */
    public String getSeu() {
        return seu;
    }

    /**
     * Actualitza el valor de la propietat seu
     *
     * @param lloc nou valor de la propietat seu
     */
    public void setSeu(String lloc) {
        this.seu = lloc;
    }
    
    /**
     * Obte el valor de la propietat fundada
     *
     * @return el valor de la propietat fundada
     */
    public int getFundada() {
        return fundada;
    }

    /**
     * Actualitza el valor de la propietat fundada
     *
     * @param any nou valor de la propietat fundada
     */
    public void setFundada(int any) {
        this.fundada = any;
    }
    
     /**
     * Obte el valor de la propietat podis
     *
     * @return el valor de la propietat podis
     */
    public List<String> getPodis() {
        return podis;
    }

    /**
     * Actualitza el valor de la propietat podis
     *
     * @param llistatPodis nou valor de la propietat podis
     */
    public void setPodis(List<String> llistatPodis) {
        this.podis.clear();
        this.podis.addAll(llistatPodis);
    }
    
    /**
     * Obte el valor de la propietat matricula
     *
     * @return el valor de la propietat matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Actualitza el valor de la propietat matricula
     *
     * @param ref nou valor de la propietat matricula
     */
    public void setMatricula(String ref) {
        this.matricula = ref;
    }

    /**
     * Obte el valor de la propietat nomPilot
     *
     * @return el valor de la propietat nomPilot
     */
    public String getNomPilot() {
        return nomPilot;
    }

    /**
     * Actualitza el valor de la propietat nomPilot
     *
     * @param nom nou valor de la propietat nomPilot
     */
    public void setNomPilot(String nom) {
        this.nomPilot = nom;
    }
    
    /**
     * Obte el valor de la propietat kg
     *
     * @return el valor de la propietat kg
     */
    public int getKg() {
        return kg;
    }

    /**
     * Actualitza el valor de la propietat kg
     *
     * @param pes nou valor de la propietat kg
     */
    public void setKg(int pes) {
        this.kg = pes;
    }
    
    /**
     * Obte el valor de la propietat llocNaixement
     *
     * @return el valor de la propietat llocNaixement
     */
    public String getLlocNaixement() {
        return llocNaixement;
    }

    /**
     * Actualitza el valor de la propietat llocNaixement
     *
     * @param ciutat nou valor de la propietat llocNaixement
     */
    public void setLlocNaixement(String ciutat) {
        this.llocNaixement = ciutat;
    }


    @Override
    public String toString() {
        return "Equip{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", seu='" + seu + '\'' +
                ", fundada=" + fundada +
                ", matricula='" + matricula + '\'' +
                ", nomPilot='" + nomPilot + '\'' +
                ", kg=" + kg +
                ", llocNaixement='" + llocNaixement + '\'' +
                ", podis=" + podis +
                '}';
    }
}
