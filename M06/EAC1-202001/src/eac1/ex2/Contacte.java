package eac1.ex2;



import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="contacte")
@XmlType(propOrder ={"nom", "telefon", "domicili", "poblacio"})
public class Contacte {

    String nom, telefon, domicili, poblacio;

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String tel) {
        this.telefon = tel;
    }

    public String getDomicili() {
        return domicili;
    }
    public void setDomicili(String adre) {
        this.domicili = adre;
    }

    public String getPoblacio() {
        return poblacio;
    }
    public void setPoblacio(String local) {
        this.poblacio = local;
    }

    @Override
    public String toString() {
        return "Contacte{" +
                "nom='" + nom + '\'' +
                ", telefon='" + telefon + '\'' +
                ", domicili='" + domicili + '\'' +
                ", poblacio='" + poblacio + '\'' +
                '}';
    }
}