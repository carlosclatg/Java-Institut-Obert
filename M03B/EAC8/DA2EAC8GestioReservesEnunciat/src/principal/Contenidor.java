/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.Scanner;

/**
 *
 * @author carlo
 */
public abstract class Contenidor implements Element {
    private String codi;
    private int places;

    public Contenidor(String codi, int places) {
        this.codi = codi;
        this.places = places;
    }

    public String getCodi() {
        return codi;
    }

    public int getPlaces() {
        return places;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public void setPlaces(int places) {
        this.places = places;
    } 
    
    @Override
    public void updateElement(){
        Scanner dades = new Scanner(System.in);
        System.out.println("\nCodi anterior: " + this.getCodi());
        System.out.println("\nEntra el nou codi:");
        this.setCodi(dades.next());
        dades.nextLine(); //Neteja buffer
        System.out.println("\nPlaces anteriors: " + this.getPlaces());
        System.out.println("\nEntra el nou nombre de places:");
        this.setPlaces(dades.nextInt());
    }
    
    @Override
    public void showElement(){
        System.out.println("\nLes dades del codi " + this.getCodi() + " són:");
        System.out.println("\nNombre de places:" + this.getPlaces());
    }
}
 