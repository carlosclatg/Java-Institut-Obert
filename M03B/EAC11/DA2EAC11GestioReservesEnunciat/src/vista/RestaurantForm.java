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
public class RestaurantForm {
    
    private JFrame frame;
    
    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lCodi;
    private JTextField tCodi;
    private JLabel lNom;
    private JTextField tNom;
    private JLabel lAdreca;
    private JTextField tAdreca;

    private JButton bDesar;   
    private JButton bSortir;   

    public RestaurantForm() {
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Formulari Restaurant" i layout Grid d'una columna
            Heu de crear els controls del formulari (labels i textFields) 
            Heu de crear els botons del formulari
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada que proposen les propietats d'aquest nom
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
       
        */
        
        //Definició de la finestra del menú
        frame = new JFrame("Formulari Restaurant");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lCodi = new JLabel("Codi");
        tCodi = new JTextField(20);
        lNom = new JLabel("Nom");
        tNom = new JTextField(20);
        lAdreca = new JLabel("Adreça");
        tAdreca = new JTextField(20);

        //Creació dels botons del formulari
        bDesar = new JButton("Desar");
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(lCodi);
        frame.add(tCodi);
        frame.add(lNom);
        frame.add(tNom);
        frame.add(lAdreca);
        frame.add(tAdreca);
        frame.add(bDesar);       
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
    public RestaurantForm(int codi, String nom, String adreca){
        this();
        tCodi.setText(String.valueOf(codi));
        tNom.setText(nom);
        tAdreca.setText(adreca);
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

    public JTextField gettAdreca() {
        return tAdreca;
    }

    public void settAdreça(JTextField tAdreca) {
        this.tAdreca = tAdreca;
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
