
package eac1.ex2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//args >> "Montse Gustems" 690678342 "C. Lleida, 4" Tarragona /home/profe/brot.xml /home/profe/brot_actual.xml
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author professor
 */
public class Exercici2   {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //Declaració i inicialització de variables
        String nom, telefon, domicili, poblacio;
        
        nom = args[0];
        telefon = args[1];
        domicili = args[2];
        poblacio = args[3];
        
        //Declaració i inicialització de variables
        String fileIn = args[4], fileOut = args[5];
        File fitxerOrigen=new File(fileIn), fitxerDesti=new File(fileOut);
        
        if(args.length!=6){
             acaba("Nombre de paràmetres erroni");
        }
        
        System.out.println(fitxerOrigen.getAbsolutePath());
        if(!fitxerOrigen.isFile() || !fitxerOrigen.canRead()){
            acaba("El cinquè parametre indica un fitxer inaccessible");
        }
         
	try {
            //Es crea el context indicant la classe arrel
            JAXBContext jaxbContext = JAXBContext.newInstance(Brot.class);
            //Es crea un Unmarshaller amb el context de la classe Brot
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //Es fa servir el mètode unmarshal, per a obtenir les dades
            Brot conne = (Brot) jaxbUnmarshaller.unmarshal(fitxerOrigen);
           
            
            List<Contacte> cadena = (List<Contacte>) conne.getCadena();
            
        //Comprovar si hi ha el telefon del contacte recorrent a l'arraylist
            int existeix = 0;
            for (int i = 0; i < cadena.size(); i++) {
		
		if ( cadena.get(i).getTelefon().equals(telefon)) {
			existeix = 1;
			break;
		}
            }
            
        if (existeix == 0) {
            
            //Crear l'objecte tipus Contacte
		Contacte contacte = new Contacte();
		contacte.setNom(nom);
                contacte.setTelefon(telefon);
                contacte.setDomicili(domicili);
		contacte.setPoblacio(poblacio);

	    //S'afegeix el contacte a la llista
		cadena.add(contacte);
	    //Es crear el Marshaller, bolcar la llista al fitxer XML
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		System.out.println("Contacte afegit: " + telefon);
		
	    // Es grava el fitxer desti amb la sortida formatada (aixo ultim s'indica en la instruccio que segueix)
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                jaxbMarshaller.marshal(conne, fitxerDesti);
                
                mostraFitxer(fitxerDesti);
                
                
        } else

		acaba("El contacte amb telèfon: "+telefon+" ja existeix!");
        
		} catch (JAXBException je) {acaba("Error de gravació: "+je.getCause());} 
                  catch (NumberFormatException e) { acaba("Error de format: "+e.getCause());}

    }
    
    private static void acaba(String missatge) {
        System.err.println(missatge);
        System.exit(2);
    }
    
    private static void mostraFitxer(File f){
        FileInputStream fis=null;
        try{
            fis= new FileInputStream(f);
            int caracter=fis.read();

            while(caracter!=(-1)){
                System.out.printf("%c",(char)caracter);
                caracter=fis.read();
            }
            tancar(fis);        
        } catch (FileNotFoundException ex) {
                acaba("Arxiu no trobat: "+ex.getCause());
        } catch (IOException ex) {
                acaba("Error d'entrada/sortida: "+ex.getCause());
        } finally{
            tancar(fis);        
        }
    }

    public static void tancar(Closeable aTancar){
        try {
            if (aTancar != null) {
                aTancar.close();
            }
        } catch (IOException ex) {
                acaba("Error d'entrada/sortida: "+ex.getCause());
    }
}     
    
    
}