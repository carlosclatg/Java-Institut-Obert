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
        @NamedQuery(
                name = "Secundaria.getAll",
                query = "SELECT i FROM Secundaria i"),
        @NamedQuery(
                name="Secundaria.getByTitol",
                query= "SELECT i FROM Secundaria i WHERE i.titol = :titol"
        )

})
public class Secundaria extends Professor {
    @Column(columnDefinition = "titol", nullable = false)
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
