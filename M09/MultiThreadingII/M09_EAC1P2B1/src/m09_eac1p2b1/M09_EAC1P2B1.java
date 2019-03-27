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
public class M09_EAC1P2B1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Client client1 = new Client("Client 1", new int[] { 2, 2, 1, 5, 2, 3 }); //6 productes
        Client client2 = new Client("Client 2", new int[] { 1, 3, 5, 1, 1 }); // productes
        Caixera caixera1 = new Caixera("Caixera 1");
        Caixera caixera2 = new Caixera("Caixera 2");
        long tempsInicial = System.currentTimeMillis();
        caixera1.processarCompra(client1, tempsInicial);
        caixera2.processarCompra(client2, tempsInicial);
    }
    
}
