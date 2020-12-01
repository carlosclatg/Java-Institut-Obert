/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaAplicacio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;

/**
 * Classe Institut de la jerarquia de lògica de l'aplicació
 * @author professor
 */

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Institut.getAllInstituts",
                query = "SELECT i FROM Institut i"),
        @NamedQuery(
                name="Institut.deleteById",
                query= "DELETE i FROM Institut i WHERE i.codi = :id"
        ),
        @NamedQuery(
                name="Institut.getById",
                query= "SELECT i FROM Institut i WHERE i.codi = :id"
        )
})
public class Institut implements Serializable {

  @Id
  private int codi;
  @Column(columnDefinition = "nom", nullable = false)
  private String nom;
  @Column(columnDefinition = "ciutat", length = 20, nullable = false)
  private String ciutat;

  @OneToMany
  private List<Professor> professors=new ArrayList<>();
  
  /**
    * Constructor per defecte: construeix un nou institut amb els valors per defecte de Java
    * (es necessari perque pugui funcionar JPA correctament)
    */
   public Institut() {
       
   }   
   
   /**
    * Constructor parametritzat: construeix un nou institut amb els paramtres especificats
    * @param codi identificador de l'institut
    * @param nom nom de l'institut
    * @param urbs ciutat on es troba l'institut
    */
   public Institut (int codi, String nom, String urbs) {
        this.codi = codi;
        this.nom = nom;
        this.ciutat = urbs;
   }
   
   /**
    * Obte el codi que identifica l'institut
    * @return codi que identifica l'institut
    */
   public int getCodi() {
        return codi;
   }
   
   /**
    * Actualitza el codi que identifica l'institut
    * @param codi nou valor per al codi que identifica l'institut
    */
   public void setCodi(int codi) {
        this.codi = codi;
   }   

   /**
    * Obte el nom de l'institut
    * @return nom de l'institut
    */
   public String getNom() {
        return nom;
   }

   /**
    * Actualitza el nom de l'institut
    * @param nom nou valor per al nom de l'institut
    */
   public void setNom(String nom) {
        this.nom = nom;
   }
   
   /**
    * Obte la ciutat on es troba l'institut
    * @return nom de l'institut
    */
   public String getCiutat() {
        return ciutat;
   }

   /**
    * Actualitza la ciutat on es troba l'institut
    * @param urbs nou valor per al nom de l'institut
    */
   public void setCiutat(String urbs) {
        this.ciutat = urbs;
   }
   
   /**
    * Llistat dels professors que dependents d'un institut
    * @return Llistat dels professors dependents d'un insitut
    */   
    public List<Professor> getProfessors() {
        return professors;
    }
    
    /**
    * Modifica el llistat dels professors dependents d'un institut
     *@param llistatProfessors nou llistat dels professors dependents d'un institut
    */   
    public void setProfessors(List<Professor> llistatProfessors) {
        this.professors=llistatProfessors;
    }  
    
}
