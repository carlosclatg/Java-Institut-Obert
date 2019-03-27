/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b3productorconsumidorn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author carlo
 */
public class B3ProductorConsumidorN {

    	private static final int TAMANY = 1000;
	private static final int NPROD = 5;
        private static final int NCONS = 5;
        
	public static void main(String[] args) {
		
            Cua cua = new Cua(TAMANY);
            
            ExecutorService executorp = Executors.newFixedThreadPool(NPROD);
            ExecutorService executorc = Executors.newFixedThreadPool(NCONS);
            
            for (int i = 0; i < NPROD; i++){
                Productor p = new Productor(cua);
                executorp.execute(p);
            }
            for(int i = 0; i < NCONS; i++){
                Consumidor c = new Consumidor(cua);
                executorc.execute(c);
            }


		
	}
}
