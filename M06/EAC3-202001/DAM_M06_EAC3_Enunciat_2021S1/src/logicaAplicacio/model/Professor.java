/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * import java.io.Serializable;
 * import javax.persistence.Column;
 * import javax.persistence.DiscriminatorColumn;
 * import static javax.persistence.DiscriminatorType.CHAR;
 * import javax.persistence.Entity;
 * import javax.persistence.Id;
 * import javax.persistence.Inheritance;
 * import static javax.persistence.InheritanceType.SINGLE_TABLE;
 * import javax.persistence.ManyToOne;
 * import javax.persistence.NamedQueries;
 * import javax.persistence.NamedQuery;
 */

/**
 * Classe Professor de la jerarquia de lògica de l'aplicació
 * @author alumne
 */

@Entity
@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR, name = "tipusprofessor")
@NamedQueries({
        @NamedQuery(
                name = "Professor.getAll",
                query = "SELECT i FROM Professor i"),
        @NamedQuery(
                name="Professor.deleteById",
                query= "DELETE i FROM Professor i WHERE i.codi = :id"
        ),
        @NamedQuery(
                name="Professor.getById",
                query= "SELECT i FROM Professor i WHERE i.codi = :id"
        )/**
     * Obté una llista amb tots els professors del cos de tècnics que treballen
     * per un institut amb un identificador determinat que es troba a la base de dades
     * @param codiInsti identificador de l'institut
     * @return llistat dels tècnics d'un isntitut determinat de la base de dades
     */
})
public class Professor implements Serializable {

  @Id
  private int idProfe;

  @Column(columnDefinition = "nom", nullable = false)
  private String nom;

  @Column(columnDefinition = "salari", nullable = false)
  private float salari;

  @ManyToOne
  private Institut institut;

   /**
    * Constructor per defecte: construeix un nou professor amb els valors per defecte de Java
    * (es necessari perque pugui funcionar JPA correctament)
    */
   public Professor() {
   }   
   
   
   /**
    * Constructor parametritzat: construeix un nou professor amb els paramtres especificats
    * @param codi identificador del professor
    * @param nom nom del professor
    * @param sou salari assigant al professor
    * @param centre institut on està assigant el professor
    */
   protected Professor(int codi, String nom, float sou, Institut centre) {
        this.idProfe = codi;
        this.nom = nom;
        this.salari = sou;
        this.institut = centre;
   }
   
   /**
    * Obte el codi que identifica el professor
    * @return idProfe que identifica el professor
    */
   public int getIdProfe() {
        return idProfe;
   }

   /**
    * Actualitza el codi que identifica el professor
    * @param codi nou valor per al idProfe que identifica el professor
    */
   public void setIdProfe(int codi) {
        this.idProfe = codi;
   }   

   /**
    * Obte el nom del professor
    * @return nom del professor
    */
   public String getNom() {
        return nom;
   }

   /**
    * Actualitza el nom del professor
    * @param nom nou valor per al nom del professor
    */
   public void setNom(String nom) {
        this.nom = nom;
   }

   /**
    * Obte el salari del professor
    * @return salari del professor
    */
   public float getSalari() {
        return salari;
   }

   /**
    * Actualitza el salari del professor
    * @param sou nou valor per al salari del professor
    */
   public void setSalari(float sou) {
        this.salari = sou;
   }
   
   /**
     * Obté l'institut on treballa el professor
     * @return l'institu on treballa el professor
     */
    public Institut getInstitut() {
        return institut;
    }
    
    /**
    * Actualitza l'institu on treballa el professor
    * @param centre nou valor per a l'institu on treballa el professor
    */
   public void setInstitut(Institut centre) {
        this.institut = centre;
   }
    
}
