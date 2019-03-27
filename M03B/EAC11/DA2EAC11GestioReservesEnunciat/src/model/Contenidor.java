/*
 * Superclasse abstracta que defineix un contenidor com una agrupació d'elements del 
 * resturant. Els contenidors poden ser les taules o menjadors, ja que les primeres 
 * agrupen cambrers i reserves, i els segons taules.
 * Aquesta classe implementa la interfície Element.
 */
package model;

import java.util.Scanner;
import principal.Element;

/**
 *
 * @author fta
 */
public abstract class Contenidor implements Element {

    //Atributs
    private String codi;
    private int places;

    //Constructor
    public Contenidor(String pCodi, int pPlaces) {
        codi = pCodi;
        places = pPlaces;
    }

    //Accessors
    public String getCodi() {
        return codi;
    }

    public void setCodi(String pCodi) {
        codi = pCodi;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int pPlaces) {
        places = pPlaces;
    }

    //Mètodes de la interfície
    public void updateElement() {
        Scanner dades = new Scanner(System.in);

        System.out.println("\nCodi actual: " + codi);
        System.out.println("\nEntra el nou codi:");
        codi = dades.next();
        System.out.println("\nTotal places atcuals: " + places);
        System.out.println("\nEntra el total de noves places :");
        places = dades.nextInt();
    }

    public void showElement() {
        System.out.println("\nLes dades del contenidor amb codi " + codi + " són:");
        System.out.println("\nPlaces:" + places);
    }

}
