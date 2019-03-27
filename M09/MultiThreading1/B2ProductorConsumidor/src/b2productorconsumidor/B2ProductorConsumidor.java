/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b2productorconsumidor;

/**
 *
 * @author carlo
 */
public class B2ProductorConsumidor {

    private static final int TAMANY = 100;
	
	public static void main(String[] args) {
		
            Cua cua = new Cua(TAMANY);
            Productor p = new Productor(cua);
            Consumidor c = new Consumidor(cua);
            p.start();
            c.start();
		
	}
    
}
