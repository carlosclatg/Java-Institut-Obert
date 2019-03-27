/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m09_eac1p2b3;

import java.util.Random;

public class Caixa {
    
    private static final int MAX_TIME = 1000;
    Node arrel, fons; 
    
    class Node {        
        int client;
        Node sig;    
    }        

    public Caixa() {        
        arrel=null;
        fons=null;    
        }   
    
    public synchronized void esperar (int id_client) throws InterruptedException {             
        Node nou = new Node();
        nou.client = id_client;
        nou.sig = null;
        if(buida()){
            arrel = nou;
            fons = nou;
        } else {
            fons.sig = nou;
            fons = nou;
            imprimir();
            wait();
        }
         
    }
    private synchronized boolean buida (){        
        if (arrel == null)            
            return true;
            else            
            return false;    
    }

    synchronized public void atendre (int pagament,int idclient, int idcaixa) throws InterruptedException    {        
        
        if(arrel == fons){ //Cas que la cua està buida o només hi ha 1.
            arrel = null;
            fons = null;
        } else {
            arrel = arrel.sig;
        }
        Thread.sleep(pagament);
        System.out.println("--->Client " + idclient + " atès en " + (pagament) +" ms a la caixa " + idcaixa + " ");
        Resultats.guanys = Resultats.guanys + pagament;
        Resultats.clients_atessos ++;
        notify(); 
    }

    synchronized public void imprimir() {        
        Node reco=arrel;        
        while (reco!=null) {            
            System.out.print(reco.client+"-");            
            reco=reco.sig;        
            }        
            System.out.println();    
    } 
    
}
