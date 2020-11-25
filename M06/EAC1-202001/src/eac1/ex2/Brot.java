package eac1.ex2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "brot")
@XmlType(propOrder ={"ref", "gestor", "pacient", "telefon", "poblacio", "cadena"})
public class Brot {

    int ref;
    String gestor, pacient, telefon, poblacio;

    @XmlElementWrapper(name="cadena")
    @XmlElement(name = "contacte")
    List<Contacte> cadena = new ArrayList<>();

    public int getRef() {
        return ref;
    }
    public void setRef(int codi) {
        this.ref = codi;
    }

    public String getGestor() {
        return gestor;
    }
    public void setGestor(String empleat) {
        this.gestor = empleat;
    }

    public String getPacient() {
        return pacient;
    }
    public void setPacient(String malalt) {
        this.pacient = malalt;
    }

    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String tel) {
        this.telefon = tel;
    }

    public String getPoblacio() {
        return poblacio;
    }
    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public List<Contacte> getCadena() {
        return cadena;
    }

    public void addNewContacte(Contacte contacte){
        cadena.add(contacte);
    }

    @Override
    public String toString() {
        return "Brot{" +
                "ref=" + ref +
                ", gestor='" + gestor + '\'' +
                ", pacient='" + pacient + '\'' +
                ", telefon='" + telefon + '\'' +
                ", poblacio='" + poblacio + '\'' +
                ", cadena=" + cadena +
                '}';
    }
}