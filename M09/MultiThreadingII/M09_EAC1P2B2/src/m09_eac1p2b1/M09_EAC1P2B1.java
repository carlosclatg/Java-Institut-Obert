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
        long tempsInicial = System.currentTimeMillis();
        Client client1 = new Client("Client 1", new int[] { 2, 2, 1, 5, 2, 3 }); //6 productes
        Client client2 = new Client("Client 2", new int[] { 1, 3, 5, 1, 1 }); // productes
        CaixeraThread caixera1 = new CaixeraThread("Caixera 1", client1, tempsInicial );
        CaixeraThread caixera2 = new CaixeraThread("Caixera 2", client2, tempsInicial );
        caixera1.start();
        caixera2.start();
    }
    
}
