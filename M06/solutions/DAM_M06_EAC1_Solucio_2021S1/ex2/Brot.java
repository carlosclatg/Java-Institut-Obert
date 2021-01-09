/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac1.ex2;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Brot controlat
 * @author professor
 */

@XmlRootElement (name = "brot")
@XmlType(propOrder = {"ref", "gestor", "pacient", "telefon", "poblacio", "cadena"})
public class Brot {
    int ref;
    String gestor, pacient, telefon, poblacio;
    
    List<Contacte> cadena=new ArrayList<>();
    
    @XmlElement
    public int getRef() {
        return ref;
    }
    public void setRef(int codi) {
        this.ref = codi;
    }
    
    @XmlElement
    public String getGestor() {
        return gestor;
    }
    public void setGestor(String empleat) {
        this.gestor = empleat;
    }
    
    @XmlElement
    public String getPacient() {
        return pacient;
    }
    public void setPacient(String malalt) {
        this.pacient = malalt;
    }
    
    @XmlElement
    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String tel) {
        this.telefon = tel;
    }
    
    @XmlElement
    public String getPoblacio() {
        return poblacio;
    }
    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }
    
    @XmlElementWrapper(name="cadena")
    @XmlElement(name="contacte")  
    public List<Contacte> getCadena() {
        return cadena;
    }
    
}
