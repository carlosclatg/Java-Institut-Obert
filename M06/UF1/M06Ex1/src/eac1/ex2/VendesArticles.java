
package eac1.ex2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Class that manages sell of articles with getters and setter for stocks, names, prices, codes and list.
 */
@XmlRootElement(name="vendesarticles")
@XmlType(propOrder = {"codi", "denominacio", "estoc", "preu", "llista"})
public class VendesArticles {
    int estoc;
    String codi, denominacio;
    float preu;
    List<Venda>llista=new ArrayList<>();
    
    public int getEstoc() {
        return estoc;
    }

    @XmlElement(name = "estoc")
    public void setEstoc(int estoc) {
        this.estoc = estoc;
    }

    public String getCodi() {
        return codi;
    }

    @XmlElement(name = "codi")
    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getDenominacio() {
        return denominacio;
    }

    @XmlElement(name = "denominacio")
    public void setDenominacio(String denominacio) {
        this.denominacio = denominacio;
    }

    public float getPreu() {
        return preu;
    }

    @XmlElement(name = "preu")
    public void setPreu(float preu) {
        this.preu = preu;
    }

   @XmlElementWrapper(name="vendes")
   @XmlElement(name="venda")
    public List<Venda> getLlista() {
        return llista;
    }
}
