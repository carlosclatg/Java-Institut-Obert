package principal;

import java.util.Scanner;
import elementsMobils.Cambrer;
import elementsMobils.Reserva;
import elementsMobils.Taula;
/*
 * Classe Application per interactuar amb l'usuari, contenir els restaurants i cridar
 * a la resta de classes i mètodes mitjançant uns menús.
 */

/**
 *
 * @author fta
 */
public class Application {

    static private Restaurant[] restaurants = new Restaurant[4];//Restaurants del grup
    static private int posicioRestaurants = 0; //La propera posició buida del vector restaurants
    static private Restaurant restaurantActual = null; //Restaurant seleccionat
    static private int tipusElement = 0;
    //SELECTORS del mètode de selecció d'elements
    static public final int SELECTCAMBRER = 1;
    static public final int SELECTRESERVA = 2;
    static public final int SELECTTAULA = 3;
    static public final int SELECTMENJADOR = 4;
    
    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Gestió de restaurants");
            System.out.println("\n2. Gestió de cambrers");
            System.out.println("\n3. Gestió de reserves");
            System.out.println("\n4. Gestió de taules");
            System.out.println("\n5. Gestió de menjadors");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    menuRestaurant();
                    break;
                case 2:
                    tipusElement = SELECTCAMBRER;
                    if (restaurantActual != null) {
                        menuElements(tipusElement);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                    }
                    break;
                case 3:
                    tipusElement = SELECTRESERVA;
                    if (restaurantActual != null) {
                        menuElements(tipusElement);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                    }
                    break;
                case 4:
                    tipusElement = SELECTTAULA;
                    if (restaurantActual != null) {
                        menuContenidor(tipusElement);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                    }
                    break;
                case 5:
                    tipusElement = SELECTMENJADOR;
                    if (restaurantActual != null) {
                        menuContenidor(tipusElement);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuRestaurant() { //AIXO IGUAL
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            int pos = -1;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. LListar restaurants");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    restaurants[posicioRestaurants] = Restaurant.addRestaurant();
                    posicioRestaurants++;
                    break;
                case 2:
                    pos = selectRestaurant();
                    if (pos >= 0) {
                        restaurantActual = restaurants[pos];
                    } else {
                        System.out.println("\nNo existeix aquest restaurant");
                    }
                    break;
                case 3:
                    pos = selectRestaurant();
                    if (pos >= 0) {
                        restaurants[pos].updateRestaurant();
                    } else {
                        System.out.println("\nNo existeix aquest restaurant");
                    }
                    break;
                case 4:
                    for (int i = 0; i < posicioRestaurants; i++) {
                        restaurants[i].showRestaurant();
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    /*
     TODO Heu de desenvolupar el menú cambrers amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, la propietat restaurantActual no és null
     
     Opció 0. Sortir -->         Sortim del menú. 
     Opció 1. Alta -->           Crea un cambrer en el restaurant actual. Penseu que Restaurant sap crear cambrers       
     Opció 2. Modificar -->      Permet modificar un cambrer que ha d'existir al restaurant actual
     (per comprovar l'existència tenim un mètode que ens ajuda a la classe Restaurant)
     Opció 3. Llistar cambrers-->Imprimeix les dades dels cambrers del restaurant actual
        
     A més, heu de fer un bucle per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - per a l'entrada de dades, millor fer servir la classe Scanner
     - definiu una variable int opcio;
     Nota important: 
     no controlem que l'usuari introdueixi una opció numèrica, doncs això ho farem amb la
     tècnica de les excepcions que veurem més endavant
     */
    public static void menuElements(int tipus) {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            if (tipus == SELECTCAMBRER){
                System.out.println("\n1. Alta cambrer");
                System.out.println("\n2. Modificar cambrer");
                System.out.println("\n3. Llistar cambrers");
            } else { //SINO SELECTRESERVA
                System.out.println("\n1. Alta reserva");
                System.out.println("\n2. Modificar reserva");
                System.out.println("\n3. Llistar reserva");
            }
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    if (tipus == SELECTCAMBRER){ //CAS CAMBRER
                        restaurantActual.addCambrer();
                    } else { //CAS RESERVA
                        restaurantActual.addReserva();
                    }

                    break;
                case 2:
                    if (tipus == SELECTCAMBRER){//CAS CAMBRER
                        int pos = restaurantActual.selectElement(SELECTCAMBRER);
                        if (pos >= 0) {
                            if(restaurantActual.getElements()[pos] instanceof Cambrer)
                                ((Cambrer)restaurantActual.getElements()[pos]).updateCambrer();
                        } else {
                            System.out.println("\nNo existeix aquest cambrer");
                        } 
                    } else {//CAS RESERVA
                        int pos = restaurantActual.selectElement(SELECTRESERVA);
                        if (pos >= 0) {
                            if(restaurantActual.getElements()[pos] instanceof Reserva)
                                ((Reserva)restaurantActual.getElements()[pos]).updateReserva();
                        } else {
                            System.out.println("\nNo existeix aquesta reserva");
                        } 
                    }
                    break;
                case 3:
                    if (tipus == SELECTCAMBRER){ //CAS CAMBRER
                        for (int i = 0; i < restaurantActual.getPosicioElements(); i++) {
                            if (restaurantActual.getElements()[i] instanceof Cambrer)
                            ((Cambrer)restaurantActual.getElements()[i]).showCambrer();
                        }   
                    } else { // CAS RESERVA
                        for (int i = 0; i < restaurantActual.getPosicioElements(); i++) {
                            if (restaurantActual.getElements()[i] instanceof Reserva)
                            ((Reserva)restaurantActual.getElements()[i]).showReserva();
                        } 
                    }
                    
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuContenidor(int tipus) {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            if (tipus == SELECTTAULA){
                System.out.println("\n1. Alta Taula");
                System.out.println("\n2. Afegir cambrer a la taula");
                System.out.println("\n3. Afegir reserva a la taula");
                System.out.println("\n4. Llistar taules");
            }
            if (tipus == SELECTMENJADOR) {
                System.out.println("\n1. Alta Menjador");
                System.out.println("\n2. Afegir taula menjador");
                System.out.println("\n4. Llistar menjadors");
            }
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1: 
                    if (tipus == SELECTTAULA){ // Alta Taula
                        restaurantActual.addTaula();
                    } else {// Alta Menjador
                        restaurantActual.addMenjador();
                    }
                    break;
                case 2:
                    if (tipus == SELECTTAULA){ //Afegir cambrer a la taula
                        restaurantActual.addElementTaula(SELECTCAMBRER); //1 = CAMBRER
                    } else {  // //Afegir taula menjador
                        restaurantActual.addTaulaMenjador();
                    }
                    break;
                case 3:
                    if (tipus == SELECTTAULA){ // Afegir reserva a la taula
                        restaurantActual.addElementTaula(SELECTRESERVA); // 2 = RESERVA
                    } else {// RES PERQUE NO HI HA RES AL MENU si tipus = 4   
                    }
                    break;
                case 4:
                    if (tipus == SELECTTAULA){ //Llistar taules
                        for (int i = 0; i < restaurantActual.getPosicioElements(); i++) {
                            if (restaurantActual.getElements()[i] instanceof Taula)
                            ((Taula)restaurantActual.getElements()[i]).showTaula();
                        }
                    } else {
                        for (int i = 0; i < restaurantActual.getPosicioElements(); i++) {
                            if (restaurantActual.getElements()[i] instanceof Menjador)
                            ((Menjador)restaurantActual.getElements()[i]).showMenjador();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }
    
    /*
     TODO Heu de desenvolupar el menú menjadors amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, la propietat restaurantActual no és null
     Nota: no farem modificació de menjadors per abast de l'EAC
     
     Opció 0. Sortir -->          Sortim del menú. 
     Opció 1. Alta -->            Crea un menjador en el restaurant actual. Penseu que Restaurant sap crear menjadors       
     Opció 2. Afegir taula -->    Afegeix una taula al vector de taules del menjador, però
     penseu que Restaurant sap fer-ho
     Opció 3. Llistar menjadors-->Imprimeix les dades dels menjadors del restaurant actual
        
     A més, heu de fer un bucle per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - per a l'entrada de dades, millor fer servir la classe Scanner
     - definiu una variable int opcio;
     Nota important: 
     no controlem que l'usuari introdueixi una opció numèrica, doncs això ho farem amb la
     tècnica de les excepcions que veurem més endavant
     */

    public static Integer selectRestaurant() {
        Scanner dades = new Scanner(System.in);
        System.out.println("\nCodi del restaurant?:");
        int codi = dades.nextInt();
        boolean trobat=false;
        int pos = -1;
        
        for (int i = 0; i < posicioRestaurants && !trobat; i++) {
            if (restaurants[i].getCodi() == codi) {
                pos = i;
                trobat=true;
            }
        }
        
        return pos;
    }
}