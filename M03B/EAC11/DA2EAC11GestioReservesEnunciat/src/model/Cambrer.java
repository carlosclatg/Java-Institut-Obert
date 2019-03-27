package model;

import java.util.Scanner;

/**
 *
 * @author fta
 */
public class Cambrer extends ElementTaula {

    private String torn;
    private boolean actiu;

    public Cambrer(String pCodi, String pNom, String pTelefon, String pTorn) {
        super(pCodi, pNom, pTelefon);
        torn = pTorn;
        actiu = true;
    }
    
    public Cambrer(String pCodi, String pNom, String pTelefon, String pTorn, boolean pActiu) {
        super(pCodi, pNom, pTelefon);
        torn = pTorn;
        actiu = pActiu;
    }

    /*
     TODO Mètodes accessors    
     */
    public String getTorn() {
        return torn;
    }

    public void setTorn(String pTorn) {
        torn = pTorn;
    }

    public boolean getActiu() {
        return actiu;
    }

    public void setActiu(boolean pActiu) {
        actiu = pActiu;
    }


    public static Cambrer addCambrer() {
        Scanner dades = new Scanner(System.in);
        String codiCambrer;
        String nomCambrer;
        String telefonCambrer;
        String tornCambrer;

        System.out.println("Codi del cambrer:");
        codiCambrer = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("Nom del cambrer:");
        nomCambrer = dades.nextLine();
        System.out.println("Telefon del cambrer:");
        telefonCambrer = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("Torn del cambrer:");
        tornCambrer = dades.nextLine();

        return new Cambrer(codiCambrer, nomCambrer, telefonCambrer, tornCambrer);
    }


    public void updateElement() {
        Scanner dades = new Scanner(System.in);
        boolean valorCorrecte = false;
        String estatCambrer;

        super.updateElement();
        
        System.out.println("\nTorn del cambrer: " + torn);
        System.out.println("\nEntra el nou torn:");
        torn = dades.nextLine();
        
        System.out.print("\nEstat del cambrer: ");
        if (actiu) {
            System.out.println("actiu");
        } else {
            System.out.println("no actiu");
        }
        
        while (!valorCorrecte) {
            System.out.println("\nEntra el nou estat(Escriu 1 o 0):");
            estatCambrer = dades.next();
            
            if (estatCambrer.equals("1")) {
                actiu = true;
                valorCorrecte = true;
            } else if (estatCambrer.equals("0")) {
                actiu = false;
                valorCorrecte = true;
            }            
        }
    }

    public void showElement() {
        
        super.showElement();
        
        System.out.println("\nTorn:" + torn);
        System.out.print("\nEstat: ");
        if (actiu) {
            System.out.println("actiu");
        } else {
            System.out.println("no actiu");
        }
    }
}
