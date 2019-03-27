package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Cambrer;
import principal.Element;
import principal.GestioReservesExcepcio;
import vista.CambrerForm;
import vista.CambrerLlista;
import vista.MenuCambrerVista;

/**
 *
 * @author fta
 */
public class ControladorCambrer implements ActionListener {

    private MenuCambrerVista menuCambrerVista;
    private CambrerForm cambrerForm = null;
    private Cambrer cambrer = null;
    private CambrerLlista cambrerLlista = null;
    private Integer opcioSeleccionada = 0;

    public ControladorCambrer() {
        menuCambrerVista = new MenuCambrerVista();
        afegirListenersMenu();
    }

    private void afegirListenersMenu() {
        for (JButton unBoto : menuCambrerVista.getMenuButtons()) {
            unBoto.addActionListener(this);
        }
    }

    private void afegirListenersForm() {
        cambrerForm.getbDesar().addActionListener(this);
        cambrerForm.getbSortir().addActionListener(this);
    }

    private void afegirListenersLlista() {
        cambrerLlista.getbSortir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            //Accions per al menú
            JButton[] elsBotons = menuCambrerVista.getMenuButtons();
            for (Integer i = 0; i < elsBotons.length; i++) {
                if (e.getSource() == elsBotons[i]) {
                    menuCambrerVista.getFrame().setVisible(false);
                    opcioSeleccionada = i;
                    bifurcaOpcio(i);
                }
            }

            //Accions per al formulari Cambrer
            if (cambrerForm != null) {

                boolean actiu = true;

                if (e.getSource() == cambrerForm.getbDesar()) {

                    if (opcioSeleccionada == 1) {

                        if (cambrerForm.gettActiu().getText().equals("no")) {
                            actiu = false;
                        } else if (!(cambrerForm.gettActiu().getText().equals("si")) && !(cambrerForm.gettActiu().getText().equals("no"))) {
                            throw new GestioReservesExcepcio("1");
                        }

                        Cambrer cambrer = new Cambrer(cambrerForm.gettCodi().getText(), cambrerForm.gettNom().getText(), cambrerForm.gettTelefon().getText(), cambrerForm.gettTorn().getText(), actiu);
                        ControladorPrincipal.getRestaurantActual().addCambrer(cambrer);
                        //menuRestaurantVista.getFrame().setVisible(true);

                    } else if (opcioSeleccionada == 2) {

                        cambrer.setNom(cambrerForm.gettNom().getText());
                        cambrer.setTelefon(cambrerForm.gettTelefon().getText());
                        cambrer.setTorn(cambrerForm.gettTorn().getText());

                        if (cambrerForm.gettActiu().getText().equals("si")) {
                            cambrer.setActiu(true);
                        } else {
                            cambrer.setActiu(false);
                        }

                    }

                } else if (e.getSource() == cambrerForm.getbSortir()) {
                    cambrerForm.getFrame().setVisible(false);
                    menuCambrerVista.getFrame().setVisible(true);
                }
            }

            if (cambrerLlista != null) {
                if (e.getSource() == cambrerLlista.getbSortir()) {
                    cambrerLlista.getFrame().setVisible(false);
                    menuCambrerVista.getFrame().setVisible(true);
                }
            }

        } catch (GestioReservesExcepcio ex) {
            JOptionPane.showMessageDialog(menuCambrerVista.getFrame(), ex.getMessage());
        }
    }

    private void bifurcaOpcio(Integer opcio) throws GestioReservesExcepcio {
        switch (opcio) {
            case 0: //sortir
                ControladorPrincipal.getMenuPrincipalVista().getFrame().setVisible(true);
                break;
            case 1: // alta
                cambrerForm = new CambrerForm();
                afegirListenersForm();
                break;
            case 2: // modificar
                int pos = ControladorPrincipal.getRestaurantActual().selectElement(1, seleccionarCambrer());
                cambrer = (Cambrer) ControladorPrincipal.getRestaurantActual().getElements()[pos];
                cambrerForm = new CambrerForm(cambrer.getCodi(), cambrer.getNom(), cambrer.getTelefon(), cambrer.getTorn(), cambrer.getActiu());
                afegirListenersForm();
                break;
            case 3: // llista
                cambrerLlista = new CambrerLlista();
                afegirListenersLlista();
                break;
        }
    }

    private String seleccionarCambrer() {
        
        int i = 0;
        
        for (Element element : ControladorPrincipal.getRestaurantActual().getElements()) {

            if (element instanceof Cambrer) {
               i++;
            }

        }

        String[] codisCambrers = new String[i];
        
        i=0;

        for (Element element : ControladorPrincipal.getRestaurantActual().getElements()) {

            if (element instanceof Cambrer) {
                codisCambrers[i] = ((Cambrer) element).getCodi();
            }
            
            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona un cambrer", "Selecció de cambrer", 0, messageType, null, codisCambrers, null);

        if (code != JOptionPane.CLOSED_OPTION) {
            return codisCambrers[code];
        }

        return null;
    }
}
