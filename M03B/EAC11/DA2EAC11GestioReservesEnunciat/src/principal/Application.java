package principal;

import model.Restaurant;
import model.Menjador;
import model.Cambrer;
import model.Reserva;
import model.Taula;
import java.util.InputMismatchException;
import java.util.Scanner;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;

/*
 * Classe Aplicacio per interactuar amb l'usuari, contenir els restaurants i cridar
 * a la resta de classes i mètodes mitjançant uns menús.
 */
/**
 *
 * @author fta
 */
public class Application {

    static private String FITXER = "restaurant";
    static private Restaurant[] restaurants = new Restaurant[4];//Restaurants del grup
    static private int posicioRestaurants = 0; //La propera posició buida del vector restaurants
    static private Restaurant restaurantActual = null; //Restaurant seleccionat
    static private int tipusElement = 0;    
    static private GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) {
        try {
            menuPrincipal();
        } catch (GestioReservesExcepcio e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuPrincipal() throws GestioReservesExcepcio {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            try {
                System.out.println("\nSelecciona una opció");
                System.out.println("\n0. Sortir");
                System.out.println("\n1. Gestió de restaurants");
                System.out.println("\n2. Gestió dels cambrers"); //1
                System.out.println("\n3. Gestió dels reserves"); //2
                System.out.println("\n4. Gestió dels taules"); //3
                System.out.println("\n5. Gestió dels menjadors"); //4
                opcio = dades.nextInt();
                switch (opcio) {
                    case 0:
                        break;
                    case 1:
                        menuRestaurant();
                        break;
                    case 2: //Cambrers
                        if (restaurantActual != null) {
                            tipusElement = 1;
                            menuElements();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    case 3: //Reserves
                        if (restaurantActual != null) {
                            tipusElement = 2;
                            menuElements();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    case 4:  //Taules
                        if (restaurantActual != null) {
                            tipusElement = 3;
                            menuContenidors();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    case 5: //Menjadors
                        if (restaurantActual != null) {
                            tipusElement = 4;
                            menuContenidors();
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar el restaurant en el menú de restaurants");
                        }
                        break;
                    default:
                        System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                        break;
                }
            } catch (InputMismatchException e) {
                GestioReservesExcepcio ee = new GestioReservesExcepcio("1");
                throw ee;
            } catch (ArrayIndexOutOfBoundsException e) {
                GestioReservesExcepcio ee = new GestioReservesExcepcio("2");
                throw ee;
            }
        } while (opcio != 0);
    }

    public static void menuRestaurant() throws GestioReservesExcepcio, InputMismatchException {
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
            System.out.println("\n5. Carrega restaurant");
            System.out.println("\n6. Desa restaurant");
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
                case 5: //Carregar restaurant
                    posicioRestaurants = 0;
                    restaurants = new Restaurant[1];
                    gp.carregarRestaurant("XML", FITXER);
                    restaurants[posicioRestaurants] = ((GestorXML)gp.getGestor()).getRestaurant();
                    posicioRestaurants++;
                    break;
                case 6: //Desar restaurant
                    pos = selectRestaurant();
                    if (pos >= 0) {
                        gp.desarRestaurant("XML", FITXER, restaurants[pos]);
                    } else {
                        System.out.println("\nNo existeix aquest restaurant");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuElements() throws GestioReservesExcepcio, InputMismatchException {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistar");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipusElement) {
                        case 1:
                            restaurantActual.addCambrer(null);
                            break;
                        case 2:
                            restaurantActual.addReserva(null);
                            break;
                    }
                    break;
                case 2:
                    int pos = restaurantActual.selectElement(tipusElement, null);
                    if (pos >= 0) {
                        restaurantActual.getElements()[pos].updateElement();
                    } else {
                        System.out.println("\nNo existeix aquest element");
                    }
                    break;
                case 3:

                    for (int i = 0; i < restaurantActual.getElements().length; i++) {
                        if ((restaurantActual.getElements()[i] instanceof Cambrer && tipusElement == 1) || (restaurantActual.getElements()[i] instanceof Reserva && tipusElement == 2)) {
                            restaurantActual.getElements()[i].showElement();
                        }
                    }

                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuContenidors() throws GestioReservesExcepcio, InputMismatchException {
        int opcio = 0;
        Scanner dades = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Afegir elements");
            System.out.println("\n4. Llistar");
            opcio = dades.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipusElement) {
                        case 3:
                            restaurantActual.addTaula(null);
                            break;
                        case 4:
                            restaurantActual.addMenjador(null);
                            break;
                    }
                    break;
                case 2:
                    int pos = restaurantActual.selectElement(tipusElement, null);
                    if (pos >= 0) {
                        restaurantActual.getElements()[pos].updateElement();
                    } else {
                        System.out.println("\nNo existeix aquest element");
                    }
                    break;
                case 3:
                    switch (tipusElement) {

                        case 3:

                            System.out.println("\nSelecciona una opció");
                            System.out.println("\n1. Afegir Cambrer");
                            System.out.println("\n2. Afegir Reserva");
                            opcio = dades.nextInt();

                            switch (opcio) {
                                case 1:
                                    restaurantActual.addElementTaula(1);
                                    break;
                                case 2:
                                    restaurantActual.addElementTaula(2);
                                    break;
                                default:
                                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                                    break;
                            }

                            break;

                        case 4:
                            restaurantActual.addTaulaMenjador();
                            break;
                    }
                    break;
                case 4:
                    for (int i = 0; i < restaurantActual.getElements().length; i++) {
                        if ((restaurantActual.getElements()[i] instanceof Taula && tipusElement == 3) || (restaurantActual.getElements()[i] instanceof Menjador && tipusElement == 4)) {
                            restaurantActual.getElements()[i].showElement();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectRestaurant() {
        Scanner dades = new Scanner(System.in);
        System.out.println("\nCodi del restaurant?:");
        int codi = dades.nextInt();
        boolean trobat = false;
        int pos = -1;

        for (int i = 0; i < posicioRestaurants && !trobat; i++) {
            if (restaurants[i].getCodi() == codi) {
                pos = i;
                trobat = true;
            }
        }

        return pos;
    }
}
