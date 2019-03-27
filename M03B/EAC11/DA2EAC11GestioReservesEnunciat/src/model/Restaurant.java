package model;

import java.io.Serializable;
import java.util.Scanner;
import principal.Element;
import principal.GestioReservesExcepcio;

/*
 * Classe que defineix un restaurant. Un restaurant es defineix per un codi, un 
 * nom i una adreça. A més, contindrà arrays amb cambrers, reserves, taules i menjadors. 
 */
/**
 *
 * @author fta
 */
public class Restaurant implements Serializable{

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

    public Restaurant(String pNom, String pAdreca, int pCodi) {
        codi = pCodi;
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
    
    public void setCodi(int pCodi) {
        codi = pCodi;
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

    public void addCambrer(Cambrer cambrer) throws GestioReservesExcepcio {
    
        if (cambrer == null) {
            cambrer = Cambrer.addCambrer();
        }

        if (selectElement(1, cambrer.getCodi()) == -1) {
            
            elements[posicioElements] = cambrer;
            posicioElements++;
            
        } else {
            throw new GestioReservesExcepcio("3");
        }

    }

    public void addReserva(Reserva reserva) throws GestioReservesExcepcio {

        if (reserva == null) {
            reserva = Reserva.addReserva();
        }

        if (selectElement(1, reserva.getCodi()) == -1) {
            
            elements[posicioElements] = reserva;
            posicioElements++;
            
        } else {
            throw new GestioReservesExcepcio("3");
        }

    }

    public void addTaula(Taula taula) throws GestioReservesExcepcio {

        if (taula == null) {
            taula = Taula.addTaula();
        }

        if (selectElement(1, taula.getCodi()) == -1) {
            
            elements[posicioElements] = taula;
            posicioElements++;
            
        } else {
            throw new GestioReservesExcepcio("3");
        }

    }

    public void addMenjador(Menjador menjador) throws GestioReservesExcepcio {

        if (menjador == null) {
            menjador = Menjador.addMenjador();
        }

        if (selectElement(1, menjador.getCodi()) == -1) {
            
            elements[posicioElements] = menjador;
            posicioElements++;
            
        } else {
            throw new GestioReservesExcepcio("3");
        }

    }

    public int selectElement(int tipusElement, String codiElement) throws GestioReservesExcepcio {

        Scanner dades = new Scanner(System.in);

        int opcio = tipusElement; //Opció a seleccionar per l'usuari del menú següent

        if (codiElement == null) {
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

            codiElement = dades.next(); //Codi del tipus d'element seleccionat
        }

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

    public void addElementTaula(int tipusElementTaula) throws GestioReservesExcepcio {
        Taula taulaSel = null;
        int pos;

        pos = selectElement(3, null); //Seleccionar Taula
        if (pos >= 0) {
            taulaSel = (Taula) getElements()[pos];

            pos = selectElement(tipusElementTaula, null); //Seleccionar element de Taula            
            if (pos >= 0) {
                taulaSel.addElementTaula((ElementTaula) getElements()[pos]);
            } else {
                System.out.println("\nNo existeix aquest element");
            }

        } else {
            System.out.println("\nNo existeix aquesta taula");
        }

    }

    public void addTaulaMenjador() throws GestioReservesExcepcio {
        Menjador menjadorSel = null;
        int pos;

        pos = selectElement(4, null); //Seleccionar Menjador
        if (pos >= 0) {
            menjadorSel = (Menjador) getElements()[pos];

            pos = selectElement(3, null); //Seleccionar taula
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
