package b2productorconsumidor;


import java.util.LinkedList;

/**
 * Classe que representa una cua de elements
 * Aquest exercici representa un recurs compartit, ja que 2 fils accedeixen de forma concurrent a la cua
 */
public class Cua {

	private int limit;
        private LinkedList<Integer> dades;
        int i = -1;
	
	public Cua(int limit) {
		this.limit = limit;
                dades = new LinkedList<Integer>();
	}

        public synchronized LinkedList<Integer> getDades() {
            return dades;
        }
        
        public int getlimit(){
            return this.limit;
        }
        
	
	/**
	 * Operació que afegeix un element a la cua
	 * El mètode es declara com sincronitzat perquè Java ens asseguri que Java 
         * no executi mai més d'un fil a al mateix temps.
	 * @param element L'element que es vol afegir */
        
        
	public synchronized void afegir(int element) {
                dades.add(element);
                notifyAll();
	}
        
        public synchronized int getNextProduct(){
            i++;
            return i;
        }

	/**
	 * Operació que elimina un element de la cua
	 * El mètode es declara com sincronitzat, perquè Java ens asseguri que mai s'executi més d'un fil al mateix temps
	 * @return El element eliminat
	 */
	public synchronized Integer eliminar() {
            int dadaconsumidor = -1;
            
            try{
                while(dades.size() == 0){
                    //Ha de fer wait mentre la cua estigui buida
                    wait();
                }
                    dadaconsumidor = dades.get(0);
                    dades.remove(0);
                
            } catch (Exception e){
                e.printStackTrace();
            }

            return dadaconsumidor;
	}
        
       
        
}
