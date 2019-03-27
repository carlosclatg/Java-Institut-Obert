package eac1.ex2;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;


/**
 * Class Abstraction of a sell, setters and getters for sell-code, qty, customer name and date.
 */
@XmlRootElement
@XmlType(propOrder = {"numvenda", "unitats", "nomclient", "data"})
public class Venda {

    int numvenda, unitats;
    String nomclient, data;
    

    public int getNumvenda() {
        return numvenda;
    }

    @XmlElement(name = "numvenda")
    public void setNumvenda(int numvenda) {
        this.numvenda = numvenda;
    }
    
    @XmlElement(name = "unitats")
    public int getUnitats() {
        return unitats;
    }

    @XmlElement(name = "unitats")
    public void setUnitats(int unitats) {
        this.unitats = unitats;
    }
    

    public String getNomclient() {
        return nomclient;
    }

    @XmlElement(name = "nomclient")
    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }
    

    public String getData() {
        return data;
    }

    @XmlElement(name = "data")
    public void setData(String data) {
        this.data = data;
    }
}
