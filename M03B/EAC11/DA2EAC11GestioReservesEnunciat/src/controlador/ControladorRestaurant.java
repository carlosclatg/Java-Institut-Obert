package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Restaurant;
import persistencia.GestorDB4O;
import persistencia.GestorJDBC;
import persistencia.GestorPersistencia;
import persistencia.GestorSerial;
import persistencia.GestorXML;
import principal.GestioReservesExcepcio;
import vista.MenuRestaurantVista;
import vista.RestaurantForm;
import vista.RestaurantLlista;

/**
 *
 * @author fta
 */
public class ControladorRestaurant implements ActionListener {

    private MenuRestaurantVista menuRestaurantVista;
    private RestaurantForm restaurantForm = null;
    private RestaurantLlista restaurantLlista = null;
    private Integer opcioSelec = 0;

    public ControladorRestaurant() {

        /*
        TODO
        
        S'inicialitza l'atribut menuRestaurantVista (això mostrarà el menú restaurants)
        Es crida a afegirListenersMenu
        
         */
        menuRestaurantVista = new MenuRestaurantVista();
        afegirListenersMenu();

    }

    //El controlador com a listener dels controls de les finestres que gestionen els restaurants
    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL MENU
    private void afegirListenersMenu() {
        /*
        TODO
        
        A cada botó del menú restaurants, s'afegeix aquest mateix objecte (ControladorRestaurant) com a listener
        
         */

        for (JButton unBoto : menuRestaurantVista.getMenuButtons()) {
            unBoto.addActionListener(this);
        }

    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL FORMULARI
    private void afegirListenersForm() {
        /*
        TODO
        
        A cada botó del formulari restaurant, s'afegeix aquest mateix objecte (ControladorRestaurant) com a listener
        
         */

        restaurantForm.getbDesar().addActionListener(this);
        restaurantForm.getbSortir().addActionListener(this);

    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DEL BOTO DE LA LLISTA
    private void afegirListenersLlista() {
        /*
        TODO
        
        Al botó de sortir de la llista restaurants, s'afegeix aquest mateix objecte (ControladorRestaurant) com a listener
        
         */

        restaurantLlista.getbSortir().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO
        
        Nota:
            Com ControladorRestaurant és listener del menú restaurants, del formulari i de la llista, llavors en aquest mètode
            actionPerformed heu de controlar si l'usuari ha premut cap botó de qualsevol dels esmentats frames.
            Ull! En el cas del formulari i de la llista, com provenen del menú (els llança el menú restaurants), heu de verificar
            primer que els objectes restaurantForm o restaurantLlista no són nulls, per tal de saber si podeu comparar amb
            cap botó d'aquests frames
        
        Accions per al menú:
            S'ha de cridar a bifurcaOpcio segons l'opció premuda. Penseu que l'opció es correspon amb
            la posició que el botó ocupa a l'array de botons de menuRestaurantVista
            També, heu d'actualitzar la propietat opcioSelec (amb l'opció que ha premut l'usuari)
        
        Accions per al formulari Restaurant:
            
            ---- DESAR ----
            Si el botó premut per l'usuari és el botó de desar del formulari restaurant, llavors
                Si l'opció seleccionada (al menú restaurants) és 1 (alta), llavors  
                        Es crea un nou objecte Restaurant amb les dades del formulari
                        S'afegeix el restaurant creat a la llista de ControladorPrincipal
                        Es posa aquest restaurant com restaurantActual (de ControladorPrincipal) i es canvia l'atribut
                        opcioSelec a 2
                Si l'opció seleccionada (al menú restaurants) és 3 (moddificació), llavors
                    Nota: no es validen dades amb aquesta opció (per simplificar)
                    Es modifica l'objecte restaurant amb les dades del formulari (penseu que és el restaurantActual de ControladorPrincipal)
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir del formulari restaurant, llavors
                Heu de tornar al menú restaurant (i amagar el formulari)
        
        Accions per a la llista de restaurants:
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir de la llista de restauraants, llavors
                Heu de tornar al menú restaurants (i amagar la llista)
         
         */

        //Accions per al menú
        JButton[] elsBotons = menuRestaurantVista.getMenuButtons();

        for (int i = 0; i < elsBotons.length; i++) {
            if (e.getSource() == elsBotons[i]) {
                menuRestaurantVista.getFrame().setVisible(false);
                opcioSelec = i;
                bifurcaOpcio(i);
            }
        }

        //Accions per al formulari Restaurant
        if (restaurantForm != null) {

            if (e.getSource() == restaurantForm.getbDesar()) {

                if (opcioSelec == 1) {//Nou restaurant

                        Restaurant restaurant = new Restaurant(restaurantForm.gettNom().getText(), restaurantForm.gettAdreca().getText());
                        ControladorPrincipal.getRestaurants()[ControladorPrincipal.getPosicioRestaurants()] = restaurant;
                        ControladorPrincipal.setPosicioRestaurants();
                        restaurantForm.gettCodi().setText(String.valueOf(restaurant.getCodi()));
                        ControladorPrincipal.setRestaurantActual(restaurant);
                        opcioSelec = 2;

                } else if (opcioSelec == 3) {//Modificar restaurant

                    ControladorPrincipal.getRestaurantActual().setNom(restaurantForm.gettNom().getText());
                    ControladorPrincipal.getRestaurantActual().setAdreca(restaurantForm.gettAdreca().getText());

                }

            } else if (e.getSource() == restaurantForm.getbSortir()) { //Sortir

                restaurantForm.getFrame().setVisible(false);
                menuRestaurantVista.getFrame().setVisible(true);

            }

        }

        if (restaurantLlista != null) {

            if (e.getSource() == restaurantLlista.getbSortir()) {

                restaurantLlista.getFrame().setVisible(false);
                menuRestaurantVista.getFrame().setVisible(true);

            }

        }

    }

    private void bifurcaOpcio(Integer opcio) {

        switch (opcio) {

            case 0: //sortir
                ControladorPrincipal.getMenuPrincipalVista().getFrame().setVisible(true);
                break;

            case 1: // alta
                if (ControladorPrincipal.getPosicioRestaurants() < ControladorPrincipal.getMAXRESTAURANTS()) {
                    restaurantForm = new RestaurantForm();
                    restaurantForm.gettCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuRestaurantVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Màxim nombre de resturants assolit.");
                }
                break;

            case 2: //seleccionar
                menuRestaurantVista.getFrame().setVisible(true);
                if (ControladorPrincipal.getRestaurants()[0] != null) {
                    seleccionarRestaurant();
                } else {
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Abans s'ha de crear al menys un restaurant");
                }
                break;

            case 3: //modificar
                if (ControladorPrincipal.getRestaurants()[0] != null) {
                    seleccionarRestaurant();
                    restaurantForm = new RestaurantForm(ControladorPrincipal.getRestaurantActual().getCodi(), ControladorPrincipal.getRestaurantActual().getNom(), ControladorPrincipal.getRestaurantActual().getAdreca());
                    restaurantForm.gettCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuRestaurantVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Abans s'ha de crear al menys un restaurant");
                }
                break;

            case 4: // llistar
                if (ControladorPrincipal.getRestaurants()[0] != null) {
                    restaurantLlista = new RestaurantLlista();
                    afegirListenersLlista();
                } else {
                    menuRestaurantVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Abans s'ha de crear al menys un restaurant");
                }
                break;

            case 5: //carregar
            /*
            TODO
                
            Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega 
            (propietat a Controlador Principal: ara XML i Serial)
            Un cop seleccionat el mètode, amb un altre dialog, es demana el codi del restaurant a carregar 
            (recordeu que el nom del fitxer és el codi del restaurant i l'extensió)
            Un cop l'usuari ha entrat el codi i ha premut OK,
                Es crea un objecte restaurant (nouRestaurant) com retorn de cridar a carregarRestaurant del gestor de persistència. Penseu que la
                carrega pots ser d'un fitxer XML o bé d'un fitxer serial.
                Es comprova si el codi del nouRestaurant ja existeix al vector de restaurants (això donarà la posició on s'ha trobat a la llista). Penseu
                que en aquesta classe teniu un mètode per fer la comprovació.
                Si existeix,
                    es mostra un dialog notificant a l'usuari si vol substituir el restaurant del vector pel que es carregarà des de el fitxer (JOptionPane.showOptionDialog)
                    Si l'usuari cancela, no es fa res.
                    Si l'usuari accepta, llavors es posa el nouRestaurant al vector a la mateixa posició on s'havia trobat aquest codi
                Si no existeix,
                    S'afegeix el nouRestaurant al vector de restaurants a la darrera posició
                    Es mostra un missatge confirmant l'addició (JOptionPane.showMessageDialog)
            
            */
                
                menuRestaurantVista.getFrame().setVisible(true);
                
                int code = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Carregar restaurant", 0, JOptionPane.QUESTION_MESSAGE, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                
                if (code != JOptionPane.CLOSED_OPTION) {
                    
                    GestorPersistencia gestor = new GestorPersistencia();
                    
                    Restaurant restaurant;
                    
                    try {
                        
                        String elCodi = JOptionPane.showInputDialog("Quin és el codi del restaurant que vols carregar?");
                        
                        gestor.carregarRestaurant(ControladorPrincipal.getMETODESPERSISTENCIA()[code], elCodi);
                        
                        if(ControladorPrincipal.getMETODESPERSISTENCIA()[code].equals("XML")){
                            restaurant=((GestorXML)gestor.getGestor()).getRestaurant();
                        }else if(ControladorPrincipal.getMETODESPERSISTENCIA()[code].equals("Serial")) {
                            restaurant=((GestorSerial)gestor.getGestor()).getRestaurant();
                        }else if((ControladorPrincipal.getMETODESPERSISTENCIA()[code].equals("JDBC"))){
                            restaurant=((GestorJDBC)gestor.getGestor()).getRestaurant();
                        }else{
                            restaurant=((GestorDB4O)gestor.getGestor()).getRestaurant();
                        }
                        
                        int pos = comprovarRestaurant(restaurant.getCodi());
                        
                        if (pos >= 0) {
                            
                            Object[] options = {"OK", "Cancel·lar"};                            
                            int i = JOptionPane.showOptionDialog(null, "Premeu OK per substituir-lo.", "Restaurant ja existent",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                    null, options, options[0]);
                            
                            if (i == 0) {
                                ControladorPrincipal.getRestaurants()[pos] = restaurant;
                            }
                            
                        } else {
                            
                            ControladorPrincipal.getRestaurants()[ControladorPrincipal.getPosicioRestaurants()] = restaurant;
                            ControladorPrincipal.setPosicioRestaurants();
                            JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Restaurant afegit correctament");
                        
                        }

                    } catch (GestioReservesExcepcio e) {
                        JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), e.getMessage());
                    }
                }
                
                break;

            case 6: //desar
                /*
                TODO
                
                Es comprova si s'ha seleccionat el restaurant, mostrant, si correspon, missatges d'error (JOptionPane.showMessageDialog)
                Si s'ha sseleccionat el restaurant, 
                    Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega
                    (propietat a Controlador Principal: ara XML i Serial)
                    Un cop escollit el mètode, es desa el restaurant cridant a desarRestaurant del gestor de persistència
                 */
                
                menuRestaurantVista.getFrame().setVisible(true);
                
                if (ControladorPrincipal.getRestaurantActual() != null) {
                    
                    int messageTyped = JOptionPane.QUESTION_MESSAGE;
                    int coded = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Desar restaurant", 0, messageTyped, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                    
                    if (coded != JOptionPane.CLOSED_OPTION) {
                        
                        GestorPersistencia gestor = new GestorPersistencia();
                        
                        try {
                            gestor.desarRestaurant(ControladorPrincipal.getMETODESPERSISTENCIA()[coded], String.valueOf(ControladorPrincipal.getRestaurantActual().getCodi()), ControladorPrincipal.getRestaurantActual());
                        } catch (GestioReservesExcepcio e) {
                            JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), e.getMessage());
                        }
                        
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(menuRestaurantVista.getFrame(), "Abans s'ha de seleccionar un restaurant");
                }
                
                break;

        }

    }

    private void seleccionarRestaurant() {

        String[] nomRestaurant = new String[ControladorPrincipal.getPosicioRestaurants()];

        int i = 0;

        for (Restaurant restaurant : ControladorPrincipal.getRestaurants()) {

            if (restaurant != null) {
                nomRestaurant[i] = restaurant.getNom();
            }

            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona un restaurant", "Selecció de restaurant", 0, messageType, null, nomRestaurant, "A");
        
        if (code != JOptionPane.CLOSED_OPTION) {
            ControladorPrincipal.setRestaurantActual(ControladorPrincipal.getRestaurants()[code]);
        }

    }

    private Integer comprovarRestaurant(int codi) {

        boolean trobat = false;

        int pos = -1;

        for (int i = 0; i < ControladorPrincipal.getRestaurants().length && !trobat; i++) {

            if (ControladorPrincipal.getRestaurants()[i] != null) {
                if (ControladorPrincipal.getRestaurants()[i].getCodi() == codi) {
                    pos = i;
                    trobat = true;
                }
            }
        }

        return pos;
    }

}
