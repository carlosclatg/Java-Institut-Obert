/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m09_eac1p2b3;

import java.util.Random;

/**
 *
 * @author carlo
 */
public class Client extends Thread {
    private static final int MAX_DELAY = 2000; 
    private static final int MAX_COST = 1000; 
    private int id; 
    private Caixa caixa;
    private int idcaixa;

    //constructor
    
    public Client (int i, Caixa caixa, int idcaixa){
        this.id = i;
        this.caixa = caixa;
        this.idcaixa = idcaixa;
    }


    public void run() { 
        //Temps de compra
        try{
            System.out.println("El client " + this.id +" entra al supermercat");
            int tempscompra = new Random().nextInt(2000);
            Thread.sleep(tempscompra);
            System.out.println("El client " + this.id +" acaba d'escollir productes en " + tempscompra +" ms i es dirigeix cap a caixa " + this.idcaixa +".");
            //Es dirigeix cap a caixa
            long timearriba = System.currentTimeMillis();
            caixa.esperar(this.id);
            while(this.caixa.arrel.client != this.id){ //Gestionem mitjançant cua
                wait();
            }
            long timeates= System.currentTimeMillis();
            System.out.println("Client " + this.id +" en cua amb");
            caixa.atendre(new Random().nextInt(MAX_COST),this.id, this.idcaixa);
            Resultats.temps_espera = timeates - timearriba;
        } catch (Exception e){
            e.printStackTrace();
        }
        

    } 
}
