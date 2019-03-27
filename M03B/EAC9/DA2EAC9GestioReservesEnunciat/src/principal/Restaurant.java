package principal;

import elementsMobils.Taula;
import elementsMobils.Reserva;
import elementsMobils.Cambrer;
import elementsMobils.ElementTaula;
import java.util.Scanner;

/*
 * Classe que defineix un restaurant. Un restaurant es defineix per un codi, un 
 * nom i una adreça. A més, contindrà arrays amb cambrers, reserves, taules i menjadors. 
 */
/**
 *
 * @author fta
 */
public class Restaurant {

    
    private int codi;
    static private int properCodi = 1; //El proper codi a assignar
    private String nom;
    private String adreca;
    private Element[] elements = new Element[230];
    private int posicioElements = 0; //Possició actual buida del vector cambrers

    public Restaurant(String pNom, String pAdreca) {
        codi = properCodi;
        properCodi++;
        nom = pNom;
        adreca = pAdreca;
    }

    /*
     TODO Mètodes accessors    
     */
    public int getCodi() {
        return codi;
    }

    public void setCodi() {
        codi = properCodi;
    }

    public static int getProperCodi() {
        return properCodi;
    }

    public static void setProperCodi() {
        properCodi++;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String pNom) {
        nom = pNom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String pAdreca) {
        adreca = pAdreca;
    }

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] pElements) {
        elements = pElements;
    }

    public static Restaurant addRestaurant() {
        Scanner dades = new Scanner(System.in);
        String nomRestaurant;
        String adrecaRestaurant;

        System.out.println("Nom del restaurant:");
        nomRestaurant = dades.nextLine();
        System.out.println("Adreça del restaurant:");
        adrecaRestaurant = dades.nextLine();
        return new Restaurant(nomRestaurant, adrecaRestaurant);
    }

    public void updateRestaurant() {
        Scanner dades = new Scanner(System.in);

        System.out.println("\nNom del restaurant: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = dades.nextLine();
        System.out.println("\nAdreça del restaurant: " + adreca);
        System.out.println("\nEntra la nova adreça:");
        adreca = dades.nextLine();
    }

    public void showRestaurant() {
        System.out.println("\nLes dades del restaurant amb codi " + codi + " són:");
        System.out.println("\nNom:" + nom);
        System.out.println("\nAdreça:" + adreca);
    }

    public void addCambrer() {
        elements[posicioElements] = Cambrer.addCambrer();
        posicioElements++;
    }

    public void addReserva() {
        elements[posicioElements] = Reserva.addReserva();
        posicioElements++;
    }

    public void addTaula() {
        elements[posicioElements] = Taula.addTaula();
        posicioElements++;
    }

    public void addMenjador() {
        elements[posicioElements] = Menjador.addMenjador();
        posicioElements++;
    }

    public Integer selectElement(int tipusElement) {

        Scanner dades = new Scanner(System.in);

        int opcio = tipusElement; //Opció a seleccionar per l'usuari del menú següent

        //Demanem quin tipus d'element vol seleccionar i el seu codi
        switch (opcio) {
            case 0:
                System.out.println("\nQuè vols seleccionar?(introduiex un dels números):");
                System.out.println("1. Cambrer:");
                System.out.println("2. Reserva:");
                System.out.println("3. Taula:");
                System.out.println("4. Menjador:");
                opcio = dades.nextInt();
                break;
            case 1:
                System.out.println("Codi del cambrer?:");
                break;
            case 2:
                System.out.println("Codi de la reserva?:");
                break;

            case 3:
                System.out.println("Codi de la taula?:");
                break;
            case 4:
                System.out.println("Codi del menjador?:");
                break;
        }

        String codiElement = dades.next(); //Codi del tipus d'element seleccionat
        int posElement = -1; //Posició que ocupa l'element seleccionat dins el vector d'elements del restaurant
        boolean trobat = false;

        //Seleccionem la posició que ocupa dins el vector d'elements del restaurant 
        //l'element seleccionat en el menú anterior
        for (int i = 0; i < posicioElements && !trobat; i++) {
            if (elements[i] instanceof Cambrer && opcio == 1) {
                if (((Cambrer) elements[i]).getCodi().equals(codiElement)) {
                    posElement = i;
                    trobat = true;
                }
            } else if (elements[i] instanceof Reserva && opcio == 2) {
                if (((Reserva) elements[i]).getCodi().equals(codiElement)) {
                    posElement = i;
                    trobat = true;
                }
            } else if (elements[i] instanceof Taula && opcio == 3) {
                if (((Taula) elements[i]).getCodi().equals(codiElement)) {
                    posElement = i;
                    trobat = true;
                }
            } else if (elements[i] instanceof Menjador && opcio == 4) {
                if (((Menjador) elements[i]).getCodi().equals(codiElement)) {
                    posElement = i;
                    trobat = true;
                }
            }
        }

        return posElement;
    }

    public void addElementTaula(int tipusElementTaula) {
        Taula taulaSel = null;
        int pos;

        pos = selectElement(3); //Seleccionar Taula
        if (pos >= 0) {
            taulaSel = (Taula) getElements()[pos];

            pos = selectElement(tipusElementTaula); //Seleccionar element de Taula            
            if (pos >= 0) {
                taulaSel.addElementTaula((ElementTaula) getElements()[pos]);
            } else {
                System.out.println("\nNo existeix aquest element");
            }

        } else {
            System.out.println("\nNo existeix aquesta taula");
        }

    }

    public void addTaulaMenjador() {
        Menjador menjadorSel = null;
        int pos;

        pos = selectElement(4); //Seleccionar Menjador
        if (pos >= 0) {
            menjadorSel = (Menjador) getElements()[pos];

            pos = selectElement(3); //Seleccionar taula
            if (pos >= 0) {
                menjadorSel.addTaulaMenjador((Taula) getElements()[pos]);
            } else {
                System.out.println("\nNo existeix aquesta taula");
            }

        } else {
            System.out.println("\nNo existeix aquest menjador");
        }
    }
}
