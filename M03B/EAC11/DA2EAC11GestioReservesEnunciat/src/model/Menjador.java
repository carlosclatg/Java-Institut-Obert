package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fta
 */
public class Menjador extends Contenidor {

    private int placesOcupades;
    private List<Taula> taules = new ArrayList();

    public Menjador(String pCodi, int pPlaces) {
        super(pCodi, pPlaces);
        placesOcupades = 0;
    }
    
    public Menjador(String pCodi, int pPlaces, int pPlacesOcupades) {
        super(pCodi, pPlaces);
        placesOcupades = pPlacesOcupades;
    }

    /*
     TODO Mètodes accessors    
     */
    public int getPlacesOcupades() {
        return placesOcupades;
    }

    public void setPlacesOcupades(int pPlacesOcupades) {
        placesOcupades = pPlacesOcupades;
    }

    public List<Taula> getTaules() {
        return taules;
    }

    public void setTaules(List<Taula> pTaules) {
        taules = pTaules;
    }

    public static Menjador addMenjador() {
        Scanner dades = new Scanner(System.in);
        String codiMenjador;
        int placesMenjador;

        System.out.println("\nCodi del menjador:");
        codiMenjador = dades.next();
        System.out.println("\nTotal places del menjador:");
        placesMenjador = dades.nextInt();

        return new Menjador(codiMenjador, placesMenjador);
    }


    public void showElement() {
        super.showElement();
        
        System.out.println("\nPlaces ocupades:" + placesOcupades);

        System.out.println("\nAquest menjador té assignades les taules:");
        for (int i = 0; i < this.getTaules().size(); i++) {
            getTaules().get(i).showElement();
        }
    }


    public void addTaulaMenjador(Taula taula) {
        if (taula.getPlaces() <= super.getPlaces() - placesOcupades) {
            taules.add(taula);
            placesOcupades -= taula.getPlaces();
        } else {
            System.out.println("\nAquest menjador no té lloc per aquesta taula");
        }
    }
}
