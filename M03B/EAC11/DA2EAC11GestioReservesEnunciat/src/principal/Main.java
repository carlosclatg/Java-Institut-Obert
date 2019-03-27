package principal;

import controlador.ControladorPrincipal;
import javax.swing.SwingUtilities;

/**
 *
 * @author fta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControladorPrincipal controlador = new ControladorPrincipal();
            }
        });
    }
    
}
