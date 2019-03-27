/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m09_eac1p2b1;

/**
 *
 * @author carlo
 */
public class Client {
    
    private String nom;
    private int[] carroCompra;

    // Constructor, getter y setter
    
    public Client (String nombre, int[] carroCompra){
        this.nom = nombre;
        this.carroCompra = carroCompra;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the carroCompra
     */
    public int[] getCarroCompra() {
        return carroCompra;
    }

    /**
     * @param carroCompra the carroCompra to set
     */
    public void setCarroCompra(int[] carroCompra) {
        this.setCarroCompra(carroCompra);
    }

}
