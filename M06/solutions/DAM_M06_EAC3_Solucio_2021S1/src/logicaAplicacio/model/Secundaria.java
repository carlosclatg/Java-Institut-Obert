/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicaAplicacio.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Professor que treballa com a professor de secundària a un institut
 * @author professor
 */
@Entity
@DiscriminatorValue("S")
@NamedQueries({ 
@NamedQuery(name="Secundaria.perTitulacio",query="SELECT s FROM Secundaria s WHERE s.titol=:titulacio"),
@NamedQuery(name="Secundaria.tots",query="SELECT b FROM Secundaria b"),
@NamedQuery(name="Secundaria.incrementSouSegonsTitol",query="UPDATE Secundaria a SET a.salari = a.salari * (1 + (:percentatge / 100)) WHERE a.titol=:titulacio")
})
public class Secundaria extends Professor {
    
    private String titol;
       
    
    /**
     * Constructor per defecte: construeix un professor amb els valors per defecte de Java
    */
    public Secundaria() {
    }

    /**
     * Constructor parametritzat: construeix un professor amb els parametres especificats
     * @param codi codi que identifica al professor de secundària
     * @param nom nom del professor de secundària
     * @param sou salari del professor de secundària
     * @param titulacio del professor de secundària
     * @param centre institut on està assigant el professor de secundària
     */
    public Secundaria(int codi, String nom, float sou, String titulacio, Institut centre) {
        super(codi, nom, sou, centre);
        this.titol=titulacio;
    }

    /**
     * Obte la titulacio del professor de secundària
     * @return titulacio del professor de secundària
     */
    @Column(length=70)
    public String getTitol() {
        return titol;
    }

    /**
     * Actualitza la titulacio del professor de secundària
     * @param titulacio nou valor per la titulacio del professor de secundària
     */
    public void setTitol(String titulacio) {
        this.titol = titulacio;
    }

   
}