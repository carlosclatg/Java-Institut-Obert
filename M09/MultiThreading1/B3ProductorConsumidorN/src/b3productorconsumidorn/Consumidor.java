package b3productorconsumidorn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Usuari
 */
/**
 * Classe que consumeix elements eliminant-los de la cua
 * 
 * @version curs 2018-2019
 */
public class Consumidor extends Thread {
        private static final int MARCAFI = -1;
        private Cua cua;
	public Consumidor (Cua cua){
            this.cua = cua;
        }
        
        @Override
        public void run(){
            
            int fi = consumeix();
            while (fi != MARCAFI){
                fi = consumeix();
            }
        }
        
        public int consumeix (){
            int element = cua.eliminar(); 
            if(element != -1){
                System.out.println("Consumidor " + this.getName() +" ha consumit " + element +"");
            } else {
                System.out.println("Consum acabat, programa finalitzat");
            }
            
            
            return element; 
        }
}
