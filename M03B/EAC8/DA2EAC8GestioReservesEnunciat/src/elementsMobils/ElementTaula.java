/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementsMobils;
import java.util.Scanner;
import principal.Element;

/**
 *
 * @author carlo
 */
public abstract class ElementTaula implements Element {
    private String codi;
    private String nom;
    private String telefon;

    public ElementTaula(String codi, String nom, String telefon) {
        this.codi = codi;
        this.nom = nom;
        this.telefon = telefon;
    }

    public String getCodi() {
        return codi;
    }

    public String getNom() {
        return nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    @Override
    public void updateElement(){
        Scanner dades = new Scanner(System.in);
        System.out.println("\nCodi anterior: " + this.getCodi());
        System.out.println("\nEntra el nou codi:");
        this.setCodi(dades.next());
        dades.nextLine(); //Neteja buffer
        System.out.println("\nNom anterior: " + this.getNom());
        System.out.println("\nEntra el nou nom:");
        this.setNom(dades.nextLine());
        System.out.println("\nTelefon anterior: " + this.getTelefon());
        System.out.println("\nEntra el nou telefon:");
        this.setTelefon(dades.next());
    }
    
    @Override
    public void showElement(){
        System.out.println("\nLes dades del codi " + this.getCodi() + " són:");
        System.out.println("\nNom:" + this.getNom());
        System.out.println("\nTelèfon:" + this.getTelefon());
    }
    
}
