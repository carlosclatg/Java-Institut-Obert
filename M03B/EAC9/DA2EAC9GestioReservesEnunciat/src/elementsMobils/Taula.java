package elementsMobils;

import java.util.Scanner;
import principal.Contenidor;

/**
 *
 * @author fta
 */
public class Taula extends Contenidor {

    private String codi;
    private int places;
    private ElementTaula[] elementsTaula = new ElementTaula[20];
    private Integer posicioElementsTaula = 0; //Primera posició buida del vector cambrers


    public Taula(String pCodi, int pPlaces) {
        super(pCodi, pPlaces);
    }

    /*
     TODO Mètodes accessors    
     */
    public ElementTaula[] getElementsTaula() {
        return elementsTaula;
    }

    public void setElementsTaula(ElementTaula[] pElementsTaula) {
        elementsTaula = pElementsTaula;
    }

    public int getPosicioElementsTaula() {
        return posicioElementsTaula;
    }

    public void setPosicioElementsTaula(int pPosicioElementsTaula) {
        posicioElementsTaula = pPosicioElementsTaula;
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
        for (int i = 0; i < getPosicioElementsTaula(); i++) {
            getElementsTaula()[i].showElement();
        }
        
    }

    public void addElementTaula(ElementTaula elementTaula) {
        elementsTaula[posicioElementsTaula] = elementTaula;
        posicioElementsTaula++;
    }
}
