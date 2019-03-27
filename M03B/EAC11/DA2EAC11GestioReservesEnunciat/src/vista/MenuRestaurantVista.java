package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author fta
 */
public class MenuRestaurantVista {
    private JFrame frame;

    private JButton[] menuButtons = new JButton[7];

   private final int AMPLADA = 800;
    private final int ALCADA = 600;

    public MenuRestaurantVista() {
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Menú Restaurant" i layout Grid d'una columna
            Heu de crear els botons del formulari. Cada botó serà un element de l'array de botons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Alta Restaurant"
                        "2. Seleccionar Restaurant"
                        "3. Modificar Restaurant"
                        "4. LListar Restaurants"
                        "5. Carregar Restaurant"
                        "6. Desar Restaurant"
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada que proposen les propietats d'aquest nom
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
        */ 
        
        //Definició de la finestra del menú
        frame = new JFrame("Menú Restaurant");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels botons a la llista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta Restaurant");
        menuButtons[2] = new JButton("2. Seleccionar Restaurant");
        menuButtons[3] = new JButton("3. Modificar Restaurant");
        menuButtons[4] = new JButton("4. LListar Restaurants");
        menuButtons[5] = new JButton("5. Carregar Restaurant");
        menuButtons[6] = new JButton("6. Desar Restaurant");

        //Addició dels botons a la finestra
        for (JButton unBoto : menuButtons) {
            frame.add(unBoto);
        }
        
        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }
}
