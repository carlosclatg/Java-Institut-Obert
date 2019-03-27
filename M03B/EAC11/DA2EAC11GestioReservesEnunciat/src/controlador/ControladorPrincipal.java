package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.Restaurant;
import persistencia.GestorPersistencia;
import vista.MenuPrincipalVista;

/**
 *
 * @author fta
 */
public class ControladorPrincipal implements ActionListener {
    static private MenuPrincipalVista menuPrincipalVista;
    static private final int MAXRESTAURANTS = 10;
    static private Restaurant[] restaurants = new Restaurant[MAXRESTAURANTS];
    static private int posicioRestaurants = 0;
    static private Restaurant restaurantActual = null;
    static private int tipusElement = 0;
    static private GestorPersistencia gp = new GestorPersistencia();
    static private final String[] METODESPERSISTENCIA = {"XML","Serial","JDBC","DB4O"}; 
    //static private final String[] METODESPERSISTENCIA = {"XML","Serial"};

    public ControladorPrincipal() {
        /*
        TODO
        
        S'inicialitza la propietat menuPrincipalVista (això mostrarà el menú principal)
        A cada botó del menú, s'afegeix aquest mateix objecte (ControladorPrincipal) com a listener
        
        */
        
        menuPrincipalVista = new MenuPrincipalVista();
        
        //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS
        for (JButton unBoto : menuPrincipalVista.getMenuButtons()) {
            unBoto.addActionListener(this);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO

        S'ha de cridar a bifurcaOpcio segons l'opció premuda. Penseu que l'opció es 
        correspon amb la posició que el botó ocupa a l'array de botons de menuPrincipalVista
        
        */ 
        
        JButton[] elsBotons = menuPrincipalVista.getMenuButtons();
        for (int i = 0; i < elsBotons.length; i++) {
            if (e.getSource() == elsBotons[i]) {
                bifurcaOpcio(i);
            }
        }
        
    }

    private void bifurcaOpcio(int opcio) {
        
        switch (opcio) {
            case 0:
                System.exit(0);
                break;
            case 1:
                menuPrincipalVista.getFrame().setVisible(false);
                ControladorRestaurant controladorRestaurant = new ControladorRestaurant();
                break;
            case 2:
                menuPrincipalVista.getFrame().setVisible(false);
                ControladorCambrer controladorCambrer = new ControladorCambrer();              
                break;
        }
        
    }



    public static MenuPrincipalVista getMenuPrincipalVista() {
        return menuPrincipalVista;
    }

    public static int getMAXRESTAURANTS() {
        return MAXRESTAURANTS;
    }

    
    public static Restaurant[] getRestaurants() {
        return restaurants;
    }

    public static void setRestaurants(Restaurant[] pRestaurants) {
       restaurants = pRestaurants;
    }

    public static int getPosicioRestaurants() {
        return posicioRestaurants;
    }

    public static void setPosicioRestaurants() {
        posicioRestaurants++;
    }

    public static Restaurant getRestaurantActual() {
        return restaurantActual;
    }

    public static void setRestaurantActual(Restaurant pRestaurantActual) {
        restaurantActual = pRestaurantActual;
    }

    public static int getTipusElement() {
        return tipusElement;
    }

    public static void setTipusElement(Integer tipusElement) {
        ControladorPrincipal.tipusElement = tipusElement;
    }

    public static GestorPersistencia getGp() {
        return gp;
    }

    public static void setGp(GestorPersistencia gp) {
        ControladorPrincipal.gp = gp;
    }

    public static String[] getMETODESPERSISTENCIA() {
        return METODESPERSISTENCIA;
    }
}
