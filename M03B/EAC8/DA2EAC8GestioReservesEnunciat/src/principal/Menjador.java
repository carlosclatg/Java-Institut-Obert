package principal;

import elementsMobils.Taula;
import java.util.Scanner;

/*
 * Classe que defineix un menjador. Un menjador es defineix per un codi, total de places
 * i les places que té ocupades. Cada plaça ocupada d'un menjador correspon a una taula,
 * per tant, contindrà un vector de taules.
 */
/**
 *
 * @author fta
 */
public class Menjador extends Contenidor {


    private int placesOcupades;
    private Taula[] taules = new Taula[30];
    private Integer posicioTaules = 0; //Priemra posició buida del vector taules    

    /*
     TODO CONSTRUCTOR
     Paràmetres: valors pels atributs codi i places
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     - Inicialitzar a 0 les places ocupades, ja que quan es crea un menjador aquest
     no té cap taula assignada.
     */
    public Menjador(String pCodi, int pPlaces) {
        super(pCodi, pPlaces);
        this.placesOcupades = 0;
    }
    

    /*
     TODO Mètodes accessors    
     *///La resta d'accessors els hereta de contenidor

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


    /*
     TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou menjador. Les dades
     a demanar són les que necessita el constructor.
     Retorn: El nou menjador creat.
     */
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


    public void showMenjador() {
        System.out.println("\nLes dades del menjador amb codi " + super.getCodi() + " són:");
        System.out.println("\nPlaces:" + super.getPlaces());
        System.out.println("\nPlaces ocupades:" + placesOcupades);

        System.out.println("\nAquest menjador té assignades les taules:");
        for (int i = 0; i < this.getPosicioTaules(); i++) {
            getTaules()[i].showElement();
        }
    }


    /*
     TODO
     Paràmetres: taula
     Accions:
     - afegeix al vector de taules del menjador actual la taula passada per paràmetre .
     Heu de tenir en compte que la taula a afegir no pot ser de més comensals que
     les places lliures que té el menjador actual. Si no és així, se li mostrarà a l'usuari 
     el missatge "Aquest menjador no té lloc per aquesta taula" i no s'afegirà 
     la taula.
     - si s'ha afegit la taula, actualitza la posició del vector de taules i les taules
       ocupades.
     Retorneu: cap
     */
    public void addTaulaMenjador(Taula taula) {
        if (taula.getPlaces() <= super.getPlaces() - placesOcupades) {
            taules[posicioTaules] = taula;
            posicioTaules++;
            placesOcupades = taula.getPlaces();
        } else {
            System.out.println("\nAquest menjador no té lloc per aquesta taula");
        }
    }
}
