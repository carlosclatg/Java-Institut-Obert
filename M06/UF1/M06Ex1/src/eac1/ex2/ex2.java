package eac1.ex2;

import java.io.File;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ex2 {
    public static void main(String[] args) {
        try{
            //Comprovacions i llegir parametres
            if(args.length != 6) throw new Exception ("error code 2 - incorrect number of args");
            int numVenda = Integer.parseInt(args[0]); //throws NumberFormatException (https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html#parseInt(java.lang.String))
            int unitats = Integer.parseInt(args[1]); //throws NumberFormatException (https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html#parseInt(java.lang.String))
            String nomClient = args[2];
            String data = args[3];
            File fitxerOrigen = new File(args[4]);
            File fitxerDesti = new File(args[5]);
            
            if ((!fitxerOrigen.isFile()) || (!fitxerOrigen.canRead())) throw new Exception("error code 2 - no file or no Readable file");

            //Crea nova venda
            Venda venda = new Venda();
            venda.setNumvenda(numVenda);
            venda.setUnitats(unitats);
            venda.setNomclient(nomClient);
            venda.setData(data);
            
            //converteixo el fitxer llegit a un objecte
            JAXBContext jc = JAXBContext.newInstance(VendesArticles.class);
            Unmarshaller um = jc.createUnmarshaller();
            VendesArticles vendesArticles = (VendesArticles) um.unmarshal(fitxerOrigen);
            
            
            //Comprovar que el primer paràmetre no coincideixi amb cap numvenda ja existent a l’objecte contingut al fitxer origen.

            for (Venda v : vendesArticles.getLlista()) { //Suposo que si ja hi és, llença excepció i no l'insereix.
                if(numVenda == v.getNumvenda()) throw new Exception ("Existing sell number ");
            }
            
            
            //afegeix venda si no existeix.
            vendesArticles.getLlista().add(venda);
            

            
            
            /*	Gravar l'objecte sobre el fitxer destí. Es fa cridant al mètode marshall de la classe Marshaller. 
            Cal passar-li la comanda i el fitxer destí. Abans, però, és molt convenient fer la crida 
            setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true) 
            amb el mateix objecte de la classe Marshaller. 
            Aquesta crida cal fer-la per aconseguir una sortida més llegible.**/
            
            
            Marshaller jm = jc.createMarshaller();
            jm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jm.marshal(vendesArticles, fitxerDesti);
            
            
            /*Mostra pel terminal com ha quedat el fitxer XML actualitzat. 
            Caldrà llegir el fitxer resultant i, tal qual, mostrar-lo per pantalla. 
            A l’activitat 2 del material de la unitat podeu trobar un exemple que, 
            a més de fer altres coses, llegeix un fitxer del disc.**/

            
            StringWriter sw = new StringWriter();
            jm.marshal(nomClient, sw);
            System.out.println(sw.toString());
            
            } catch (Exception e){ //Excepcions per consola.
                e.printStackTrace(System.err);
            
        }
        
    }
}
