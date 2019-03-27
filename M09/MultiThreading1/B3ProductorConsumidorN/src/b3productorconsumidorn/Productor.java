package b3productorconsumidorn;

/*

 * @author Usuari
 */
/**
 * Classe que produeix elements i l'afegeix a la cua
 * @version curs 2018-2019
 */
public class Productor extends Thread {
    private static final int MARCAFI = -1;
    private Cua cua;
    
    
    public Productor (Cua cua){
        
        this.cua = cua;
        
    }
    
    
    @Override
    public void run(){
        
        int i = cua.getNextProduct();
            while (i < cua.getlimit()){
                cua.afegir(i);
                System.out.println("Productor " + this.getName() +" afegeix " + i +"");
                i = cua.getNextProduct();
            }
        cua.afegir(MARCAFI);
        
        System.out.println("Producció acabada");
        
    }
	
}
