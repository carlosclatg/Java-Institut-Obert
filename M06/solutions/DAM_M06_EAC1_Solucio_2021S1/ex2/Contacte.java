/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac1.ex2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Contacte controlat
 * @author professor
 */
@XmlType(propOrder = {"nom", "telefon", "domicili", "poblacio"})
public class Contacte {

    String nom, telefon, domicili, poblacio;
    
    @XmlElement
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @XmlElement
    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String tel) {
        this.telefon = tel;
    }
    
    @XmlElement
    public String getDomicili() {
        return domicili;
    }
    public void setDomicili(String adre) {
        this.domicili = adre;
    }
    
    @XmlElement
    public String getPoblacio() {
        return poblacio;
    }
    public void setPoblacio(String local) {
        this.poblacio = local;
    }
    
}
