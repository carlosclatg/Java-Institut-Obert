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
public class CaixeraThread extends Thread {
    private String nom;
    private Client client;
    private long initialTime;

    // Constructor, getter y setter
    public CaixeraThread (String nombre, Client client, long initialTime){
        this.nom = nombre;
        this.client = client;
        this.initialTime =  initialTime;
    }
    
    public void run(){
        processarCompra(client, initialTime);
    }

    public void processarCompra(Client client, long timeStamp) {
        System.out.println("La Caixera " + this.getNom() + 
				" COMENÇA A PROCESSAR LA COMPRA DEL CLIENT: " + client.getNom() + 
				" EN EL Temps: " + (System.currentTimeMillis() - timeStamp) / 1000	+
				"seg");
        int[] carrocompra = client.getCarroCompra();
        for(int i = 0; i < carrocompra.length; i++){
            esperarXsegons(carrocompra[i]);
            System.out.println("Processat el producte " + (i+1) +" del client " + client.getNom() + " --> Temps: " + (System.currentTimeMillis() - timeStamp) / 1000	+ " seg");
        }
        System.out.println("El caixer " + this.getNom() +" ha processat el client " + client.getNom() +" en el temps " + (System.currentTimeMillis() - timeStamp) / 1000	+ " seg");  
    }


    private void esperarXsegons(int segons) {
            try {
                    Thread.sleep(segons * 1000);
            } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
            }
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
}
