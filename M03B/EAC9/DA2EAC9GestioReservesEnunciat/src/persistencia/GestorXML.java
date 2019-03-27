package persistencia;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import principal.GestioReservesExcepcio;
import principal.Restaurant;

/**
 *
 * @author fta
 */
public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void desarRestaurant(String nomFitxer, Restaurant restaurant) throws GestioReservesExcepcio {
        construeixModel(restaurant);
        desarModel(nomFitxer);
    }

    @Override
    public void carregarRestaurant(String nomFitxer) throws GestioReservesExcepcio {
        carregarFitxer(nomFitxer);
        fitxerRestaurant();
    }

    /*Paràmetres: Restaurant a partir de la qual volem construir el model
     *
     *Acció: 
     * - Llegir els atributs de l'objecte Restaurant passat per paràmetre 
     *   per construir un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "restaurant". Aquesta arrel, l'heu d'afegir 
     *   a doc. Un cop fet això, heu de recórrer l'ArrayList elements de 
     *   Restaurant i per a cada element, afegir un fill a doc. Cada fill 
     *   tindrà com atributs els atributs de l'objecte (codi, nom, …).
     *
     * - Si es tracta d'una taua, a més, heu d'afegir fills addicionals amb 
     *   els cambrers i reserves assignats a la taula .
     *
     * - Si es tracta d'un menjador, a més, heu d'afegir fills addicionals amb les
     *   seves taules.
     *
     * - En el cas del cambrer, si és actiu, a l'atribut actiu del document XML li
     *   heu d'assignar "actiu", si no, "no actiu"
     *
     *Retorn: cap
     */
    public void construeixModel(Restaurant restaurant) throws GestioReservesExcepcio {
       
    }

    public void desarModel(String nomFitxer) throws GestioReservesExcepcio {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception ex) {
            throw new GestioReservesExcepcio("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestioReservesExcepcio {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (Exception ex) {
            throw new GestioReservesExcepcio("GestorXML.carrega");
        }
    }

    /*Paràmetres: cap
     *
     *Acció: 
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc.
    
     * - Per assignar valors als atributs de Restaurant i la resta d'objectes 
     *   que formen els elements de Restaurant.
    
     * - Primer creeu l'objecte Restaurant a partir de l'arrel del document per després 
     *   recórrer el doc i per cada fill, afegir un objecte a l'atribut elements de 
     *   Restaurant mitjançant el mètode escaient de la classe Restaurant.
    
     * - Si el fill (del doc) que s'ha llegit és una taula o un menjador, recordeu 
     *   que a més d'afegir-los a elements, també haureu d'afegir en la taula 
     *   els seus cambrers i reserves, i en el menjador les seves taules.
     *
     *Retorn: cap
     */
    private void fitxerRestaurant() throws GestioReservesExcepcio {
       
    }
}
