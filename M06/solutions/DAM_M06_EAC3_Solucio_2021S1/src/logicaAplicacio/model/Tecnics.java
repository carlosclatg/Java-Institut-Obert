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
 *
 * @author professor
 */
@Entity
@DiscriminatorValue("T")
@NamedQueries({ @NamedQuery(name="Tecnics.perEspecialitat",query="SELECT a FROM Tecnics a WHERE a.especialitat=:especialitat"),
@NamedQuery(name="Tecnics.tots",query="SELECT d FROM Tecnics d"),
@NamedQuery(name="Tecnics.segonsSou",query="SELECT d FROM Tecnics d WHERE d.salari=:sou"),
@NamedQuery(name="Tecnics.institut",query="SELECT d FROM Tecnics d WHERE d.institut.codi =:codi")
})
public class Tecnics extends Professor {
    
    private String especialitat;
    
    
    /**
     * Constructor per defecte: construeix un professor del cos de tècnics amb els valors per defecte de Java
     */
    public Tecnics() {
    }

    /**
     * Constructor parametritzat: construeix  un professor del cos de tècnics amb els parametres especificats
     * @param codi codi que identifica el tècnic
     * @param nom nom del professor del cos de tècnics
     * @param sou salari actual del professor del cos de tècnics
     * @param formacio especialitat actual del professor del cos de tècnics
     * @param centre institut on està assigant el professor del cos de tècnics
     */
    public Tecnics(int codi, String nom, float sou, String formacio, Institut centre) {
        super(codi, nom, sou, centre);
        this.especialitat = formacio;   
    }

    /**
    * Especialitat del professor del cos de tècnics
    * @return especialitat actual del professor del cos de tècnics
    */  
    @Column(length=50)
    public String getEspecialitat() {
        return especialitat;
    }

  /**
    * Modifica la titulació del professor del cos de tècnics
    * @param espec nova especilaitat del professor del cos de tècnics
    */
    public void setEspecialitat(String espec) {
        this.especialitat = espec;
    }
    
    
}