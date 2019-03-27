package persistencia;

import principal.GestioReservesExcepcio;
import principal.Restaurant;


/**
 *
 * @author Francesca
 */
public class GestorPersistencia {
    private GestorXML gestor;

    public GestorXML getGestor() {
        return gestor;
    }

    public void setGestor(GestorXML pGestor) {
        gestor = pGestor;
    }

    public void desarRestaurant(String tipusPersistencia, String nomFitxer, Restaurant restaurant) throws GestioReservesExcepcio{
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.desarRestaurant(nomFitxer, restaurant);
        }
    }

    public void carregarRestaurant(String tipusPersistencia, String nomFitxer) throws GestioReservesExcepcio{
       
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.carregarRestaurant(nomFitxer);
        }
    }
 
}