package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author fta
 */
public class CambrerForm {

    private JFrame frame;

    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lCodi;
    private JTextField tCodi;
    private JLabel lNom;
    private JTextField tNom;
    private JLabel lTelefon;
    private JTextField tTelefon;
    private JLabel lTorn;
    private JTextField tTorn;
    private JLabel lActiu;
    private JTextField tActiu;

    private JButton bDesar;
    private JButton bSortir;

    public CambrerForm() {

        //Definició de la finestra del menú
        frame = new JFrame("Formulari Cambrer");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lCodi = new JLabel("Codi");
        tCodi = new JTextField(20);
        lNom = new JLabel("Nom");
        tNom = new JTextField(20);
        lTelefon = new JLabel("Teléfon");
        tTelefon = new JTextField(11);
        lTorn = new JLabel("torn");
        tTorn = new JTextField(20);
        lActiu = new JLabel("Actiu(si -> Actiu, no -> No Actiu)");
        tActiu = new JTextField(11);

        //Creació dels botons del formulari
        bDesar = new JButton("Desar");
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(lCodi);
        frame.add(tCodi);
        frame.add(lNom);
        frame.add(tNom);
        frame.add(lTelefon);
        frame.add(tTelefon);
        frame.add(lTorn);
        frame.add(tTorn);
        frame.add(lActiu);
        frame.add(tActiu);
        frame.add(bDesar);
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public CambrerForm(String codi, String nom, String telefon, String torn, boolean actiu) {
        this();
        tCodi.setText(codi);
        tCodi.setEnabled(false);
        tNom.setText(nom);
        tTelefon.setText(telefon);
        tTorn.setText(torn);
        if (actiu) {
            tActiu.setText("si");
        } else {
            tActiu.setText("no");
        }

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField gettCodi() {
        return tCodi;
    }

    public void settCodi(JTextField tCodi) {
        this.tCodi = tCodi;
    }

    public JTextField gettNom() {
        return tNom;
    }

    public void settNom(JTextField tNom) {
        this.tNom = tNom;
    }

    public JTextField gettTelefon() {
        return tTelefon;
    }

    public void settTelefon(JTextField tTelefon) {
        this.tTelefon = tTelefon;
    }

    public JTextField gettTorn() {
        return tTorn;
    }

    public void settTorn(JTextField tTorn) {
        this.tTorn = tTorn;
    }

    public JTextField gettActiu() {
        return tActiu;
    }

    public void settActiu(JTextField tActiu) {
        this.tActiu = tActiu;
    }

   

    public JButton getbDesar() {
        return bDesar;
    }

    public void setbDesar(JButton bDesar) {
        this.bDesar = bDesar;
    }

    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }

}
