package elementsMobils;


import java.util.Scanner;
import principal.Contenidor;
/*
 * Classe que defineix una taula. Una taula es defineix per un codi i total de places.
 * A més, contindrà un vector de reserves i un vector de cambrers.
 */
/**
 *
 * @author fta
 */
public class Taula extends Contenidor {


//    private Cambrer[] cambrers = new Cambrer[10];
//    private Integer posicioCambrers = 0; //Priemra posició buida del vector cambrers
//    private Reserva[] reserves = new Reserva[10];
//    private Integer posicioReserves = 0; //Priemra posició buida del vector reserves

    private ElementTaula[] elementsTaula = new ElementTaula[20];
    private int posicioElementsTaula = 0;
    /*
     TODO CONSTRUCTOR
     Paràmetres: valors pels atributs codi i places
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     */
    public Taula (String pCodi, int places) {
        super(pCodi,places);
    }



    /*
    TODO Mètodes accessors    
     */
    public int getPosicioElementsTaula() {
        return posicioElementsTaula;
    }
    public ElementTaula[] getElementsTaula() {
        return elementsTaula;
    }

    public void setElementsTaula(ElementTaula[] elementsTaula) {
        this.elementsTaula = elementsTaula;
    }

    public void setPosicioElementsTaula(int posicioElementsTaula) {
        this.posicioElementsTaula = posicioElementsTaula;
    }
    
    /*
    TODO
    Paràmetres: cap
    Accions:
    - Demanar a l'usuari les dades per consola per crear una nova Taula. Les dades
    a demanar són les que necessita el constructor.
    Retorn: La nova taula creada.
     */
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

    public void showTaula() {
        System.out.println("\nLes dades de la taula amb codi " + this.getCodi() + " són:");
        System.out.println("\nPlaces:" + this.getPlaces());
    }
    

    /*
    TODO
     Paràmetres: Cambrer
     Accions:
     - afegeix al vector de cambrers de la taula actual el cambrer passat per paràmetre. 
     - actualitza la posició del vector de cambrers.
     Retorneu: cap
     */
    public void addElementTaula(ElementTaula elementTaula) {
        elementsTaula[posicioElementsTaula] = elementTaula;
        posicioElementsTaula++;
    }

//    public void addCambrerTaula(Cambrer cambrer) {
//        cambrers[posicioCambrers] = cambrer;
//        posicioCambrers++;
//    }

    /*
    TODO
     Paràmetres: Reserva
     Accions:
     - afegeix la reserva passada per paràmetre al vector de reserves de la taula actual.
     Heu de tenir en compte que la reserva a afegir no pot ser de més comensals de
     les places que té la taula actual. Si no és així, se li mostrarà a l'usuari 
     el missatge "Aquesta taula no té places per tants comenslas" i no s'afegira 
     la reserva.
     - actualitza la posició del vector de reserves, si s'ha afegit la reserva.
     Retorneu: cap
     */
//    public void addReservaTaula(Reserva reserva) {
//        if (reserva.getComensals() <= this.getPlaces()) {
//            reserves[posicioReserves] = reserva;
//            posicioReserves++;
//        } else {
//            System.out.println("\nAquesta taula no té places per tants comenslas");
//        }
//    }
}

