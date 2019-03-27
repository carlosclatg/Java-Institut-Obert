/*
 * Superclasse abstracta dels elements del restaurant que formen part d'una taula
 * Aquesta classe implementa la interfície Element.
 */
package model;

import java.util.Scanner;
import principal.Element;

/**
 *
 * @author fta
 */
public abstract class ElementTaula implements Element {

    //Atributs
    private String codi;
    private String nom;
    private String telefon;

    //Constructor
    public ElementTaula(String pCodi, String pNom, String pTelefon) {
        codi = pCodi;
        nom = pNom;
        telefon = pTelefon;
    }

    //Accessors
    public String getCodi() {
        return codi;
    }

    public void setCodi(String pCodi) {
        codi = pCodi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String pNom) {
        nom = pNom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String pTelefon) {
        telefon = pTelefon;
    }

     //Implementació mètodes de la inerfície
    @Override
    public void updateElement() {
        Scanner dades = new Scanner(System.in);

        System.out.println("\nCodi actual: " + codi);
        System.out.println("\nEntra el nou codi:");
        codi = dades.next();
        dades.nextLine(); //Neteja buffer
        System.out.println("\nNom atcual: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = dades.nextLine();
        System.out.println("\nTelefon actual: " + telefon);
        System.out.println("\nEntra el nou telefon:");
        telefon = dades.next();
    }

    @Override
    public void showElement() {
        System.out.println("\nLes dades de l'element de la taula amb " + codi + " són:");
        System.out.println("\nNom:" + nom);
        System.out.println("\nTelefon:" + telefon);
    }

}
