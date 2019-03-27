package principal;

import elementsMobils.Taula;
import java.util.Scanner;

/**
 *
 * @author fta
 */
public class Menjador extends Contenidor {

    private int placesOcupades;
    private Taula[] taules = new Taula[30];
    private Integer posicioTaules = 0; //Primera posició buida del vector taules    

    public Menjador(String pCodi, int pPlaces) {
        super(pCodi, pPlaces);
        placesOcupades = 0;
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

    public Taula[] getTaules() {
        return taules;
    }

    public void setTaules(Taula[] pTaules) {
        taules = pTaules;
    }

    public Integer getPosicioTaules() {
        return posicioTaules;
    }

    public void setPosicioTaules(int pPosicioTaules) {
        posicioTaules = pPosicioTaules;
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
        for (int i = 0; i < this.getPosicioTaules(); i++) {
            getTaules()[i].showElement();
        }
    }


    public void addTaulaMenjador(Taula taula) {
        if (taula.getPlaces() <= super.getPlaces() - placesOcupades) {
            taules[posicioTaules] = taula;
            posicioTaules++;
            placesOcupades += taula.getPlaces();
        } else {
            System.out.println("\nAquest menjador no té lloc per aquesta taula");
        }
    }
}
