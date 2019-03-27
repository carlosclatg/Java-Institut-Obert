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
public class M09_EAC1P2B3 {

    private static final int NCAIXES = 3;   
    private static final int NCLIENTS = 20;
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Client[] clients = new Client[NCLIENTS];
            Caixa[] caixes = new Caixa[NCAIXES];
            for(int i = 0; i < caixes.length; i ++){
                caixes[i] = new Caixa();
            }
            for(int i = 0; i < clients.length; i++){
                int j = new Random().nextInt(NCAIXES);
                clients[i] = new Client(i, caixes[j], j);
                clients[i].start();
            }
            
            for (int i = 0; i < clients.length; i++){
                clients[i].join();
            }
            
            System.out.println("Supermercat tancat.");   
            System.out.println("Guanys: " + Resultats.guanys);   
            System.out.println("Tiemps mig d'espera: " +    (Resultats.temps_espera / Resultats.clients_atessos));      
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
}
