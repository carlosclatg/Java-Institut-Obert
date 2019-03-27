package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fta
 */
public class Taula extends Contenidor {

    private List<ElementTaula> elementsTaula = new ArrayList();


    public Taula(String pCodi, int pPlaces) {
        super(pCodi, pPlaces);
    }

    /*
     TODO Mètodes accessors    
     */
    public List<ElementTaula> getElementsTaula() {
        return elementsTaula;
    }

    public void setElementsTaula(List<ElementTaula> pElementsTaula) {
        elementsTaula = pElementsTaula;
    }

    public static Taula addTaula() {
        Scanner dades = new Scanner(System.in);
        String codiTaula;
        int placesTaula;

        System.out.println("\nCodi de la taula:");
        codiTaula = dades.next();
        System.out.println("\nTotal places de la taula:");
        placesTaula = dades.nextInt();

        return new Taula(codiTaula, placesTaula);
    }

    public void showElement() {
        super.showElement();

        System.out.println("\nAquesta taula té assignat:");
        for (int i = 0; i < getElementsTaula().size(); i++) {
            getElementsTaula().get(i).showElement();
        }
        
    }

    public void addElementTaula(ElementTaula elementTaula) {
        elementsTaula.add(elementTaula);
    }
}
